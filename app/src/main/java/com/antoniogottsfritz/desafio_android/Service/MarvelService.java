package com.antoniogottsfritz.desafio_android.Service;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.antoniogottsfritz.desafio_android.BuildConfig;
import com.antoniogottsfritz.desafio_android.Model.API.ResultContainer;
import com.antoniogottsfritz.desafio_android.Model.API.ResultWrapper;
import com.antoniogottsfritz.desafio_android.Model.Comic;
import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class MarvelService {
    private static final String API_URL = "https://gateway.marvel.com/";
    private static final int API_LIMIT = 100;

    private static MarvelService _marvelService;

    private MarvelApi _api;

    public MutableLiveData<Exception> Error = new MutableLiveData<>();

    private MarvelService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        _api = retrofit.create(MarvelApi.class);
    }

    public static synchronized MarvelService getInstance() {
        if (_marvelService == null) {
            _marvelService = new MarvelService();
        }
        return _marvelService;
    }


    private Map<String, String> createAuth() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String toHash = timestamp + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY;
        String md5 = Util.md5(toHash);

        Map<String, String> auth = new HashMap<>();
        auth.put("ts", timestamp);
        auth.put("apikey", BuildConfig.MARVEL_PUBLIC_KEY);
        auth.put("hash", md5);

        return auth;
    }

    public ResultContainer<MarvelCharacter> getCharacters(int offset, int limit) {
        Map<String, String> auth = createAuth();

        Map<String, String> pagination = new HashMap<>();
        pagination.put("offset", String.valueOf(offset));
        pagination.put("limit", String.valueOf(limit));

        Call<ResultWrapper<MarvelCharacter>> call = _api.getCharacters(auth, pagination);
        try {
            return apiGetListSync(call);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LiveData<Comic> getMostExpensiveComic(int characterId) {
        MutableLiveData<Comic> liveComic = new MutableLiveData<>();

        AsyncTask.execute(() -> {
            int offset = 0;
            int total = Integer.MAX_VALUE;

            Comic mostExpensiveComic = null;
            float highestPrice = 0;

            while (offset < total) {
                ResultContainer<Comic> container;
                try {
                    container = getComicsSync(characterId, offset, API_LIMIT);
                    total = container.getTotal();
                    offset += container.getCount();

                    for (Comic comic : container.getResults()) {
                        for (Comic.ComicPrice price : comic.getPrices()) {
                            if (price.getPrice() > highestPrice) {
                                mostExpensiveComic = comic;
                                highestPrice = price.getPrice();
                            }
                        }
                    }
                } catch (Exception e) {
                    Error.postValue(e);
                }
            }

            if (mostExpensiveComic != null) {
                mostExpensiveComic.setHighestPrice(highestPrice);
            }
            liveComic.postValue(mostExpensiveComic);
        });

        return liveComic;
    }

    public LiveData<ResultContainer<Comic>> getComics(int characterId, int offset, int limit) {
        Map<String, String> auth = createAuth();

        Map<String, String> pagination = new HashMap<>();
        pagination.put("offset", String.valueOf(offset));
        pagination.put("limit", String.valueOf(limit));

        Call<ResultWrapper<Comic>> call = _api.getCharacterComics(characterId, auth, pagination);
        return apiGetList(call);
    }

    public ResultContainer<Comic> getComicsSync(int characterId, int offset, int limit) throws Exception {
        Map<String, String> auth = createAuth();

        Map<String, String> pagination = new HashMap<>();
        pagination.put("offset", String.valueOf(offset));
        pagination.put("limit", String.valueOf(limit));

        Call<ResultWrapper<Comic>> call = _api.getCharacterComics(characterId, auth, pagination);
        return apiGetListSync(call);
    }


    protected <T> LiveData<ResultContainer<T>> apiGetList(Call<ResultWrapper<T>> call) {
        MutableLiveData<ResultContainer<T>> data = new MutableLiveData<>();

        call.enqueue(new Callback<ResultWrapper<T>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ResultWrapper<T>> call, Response<ResultWrapper<T>> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.e("Response error", "response code: " + response.code());
                    try {
                        Log.e("Response error body", response.errorBody() != null
                                ? response.errorBody().string() : response.message());
                    } catch (IOException e) {
                        Log.e("Response error body",
                                "Error getting response.errorBody()");
                    }
                    Error.postValue(new Exception(String.valueOf(response.code())));
                }

                if (response.body() != null) {
                    data.postValue(response.body().getData());
                } else {
                    String msg = "Response body is null";
                    Log.e("Response error", msg);
                    Error.postValue(new Exception(msg));
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ResultWrapper<T>> call, Throwable t) {
                Log.e("Retrofit error", t.getMessage(), t);
                Error.postValue(new Exception(t.getMessage()));
            }
        });

        return data;
    }

    protected <T> ResultContainer<T> apiGetListSync(Call<ResultWrapper<T>> call) throws Exception {
        try {
            Response<ResultWrapper<T>> response = call.execute();
            if (!response.isSuccessful() || response.body() == null) {
                Log.e("Response error", "response code: " + response.code());
                Log.e("Response error body", response.errorBody() != null
                        ? response.errorBody().string() : response.message());
                throw new Exception("Erro na resposta: " + response.code());
            }

            ResultWrapper<T> result = response.body();
            if (result.getCode() != 200) {
                Log.e("Result error", "result code: " + result.getStatus());
                throw new Exception("Erro no resultado: " + result.getStatus());
            }

            return result.getData();

        } catch (IOException e) {
            Log.e("Retrofit error", e.getMessage(), e);
            throw new Exception("Erro ao executar chamada: " + e.getMessage());
        }
    }
}
