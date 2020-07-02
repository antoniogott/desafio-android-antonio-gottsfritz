package com.antoniogottsfritz.desafio_android.Service;

import android.util.Log;

import com.antoniogottsfritz.desafio_android.BuildConfig;
import com.antoniogottsfritz.desafio_android.Model.API.ResultContainer;
import com.antoniogottsfritz.desafio_android.Model.API.ResultWrapper;
import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelService {
    private static final String API_URL = "https://gateway.marvel.com/";

    private static MarvelService _marvelService;

    private MarvelApi _api;
    private int charPage = 0;

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


    public Map<String, String> createAuth() {
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
            return apiGetList(call);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <T> ResultContainer<T> apiGetList(Call<ResultWrapper<T>> call) throws Exception {
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
            Log.e("Execute error", e.getMessage(), e);
            throw new Exception("Erro ao executar chamada: " + e.getMessage());
        }
    }
}
