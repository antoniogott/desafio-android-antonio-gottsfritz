package com.antoniogottsfritz.desafio_android.Service;

import android.util.Log;

import com.antoniogottsfritz.desafio_android.BuildConfig;
import com.antoniogottsfritz.desafio_android.Model.API.ResultContainer;
import com.antoniogottsfritz.desafio_android.Model.API.ResultWrapper;
import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;
import com.antoniogottsfritz.desafio_android.Model.MarvelImage;
import com.antoniogottsfritz.desafio_android.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelService {
    private static final int PAGE_SIZE = 20;
    private static final String API_URL = "https://gateway.marvel.com/";

    private MarvelApi _api;

    public MarvelService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        _api = retrofit.create(MarvelApi.class);
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

    public List<MarvelCharacter> getCharacters() {
        Map<String, String> auth = createAuth();
        Map<String, String> pagination = new HashMap<>(); //TODO pagination map;

        try {
            Response<ResultWrapper<MarvelCharacter>> response = _api.getCharacters(auth, pagination).execute();
            if (response.isSuccessful()) {
                ResultWrapper<MarvelCharacter> result = response.body();
                if (result.getCode() == 200) {
                    ResultContainer<MarvelCharacter> container = result.getData();
                    return container.getResults();
                } else {
                    Log.e("result error", "result code: " + result.getStatus());
                }
            } else {
                Log.e("response error", "response code: " + response.code());
                Log.e("response error body", response.errorBody().string());
            }
        } catch (IOException e) {
            Log.e("execute error", e.getMessage(), e);
        }


        ArrayList<MarvelCharacter> list = new ArrayList<>();

        MarvelCharacter ch = new MarvelCharacter();
        ch.setName("Abyss");
        ch.setId(123);
        MarvelImage img = new MarvelImage();
        img.setExtension("jpg");
        img.setPath("http://i.annihil.us/u/prod/marvel/i/mg/9/30/535feab462a64");
        ch.setThumbnail(img);
        list.add(ch);

        MarvelCharacter ch2 = new MarvelCharacter();
        ch2.setName("3-D Man");
        ch2.setId(8957);
        MarvelImage img2 = new MarvelImage();
        img2.setExtension("jpg");
        img2.setPath("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784");
        ch2.setThumbnail(img2);
        list.add(ch2);

        MarvelCharacter ch3 = new MarvelCharacter();
        ch3.setName("A-Bomb (HAS)");
        ch3.setId(6516);
        ch3.setDescription("Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ");
        MarvelImage img3 = new MarvelImage();
        img3.setExtension("jpg");
        img3.setPath("http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16");
        ch3.setThumbnail(img3);
        list.add(ch3);

//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);
//
//        list.add(ch);
//        list.add(ch2);
//        list.add(ch3);


        return list;
    }
}
