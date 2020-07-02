package com.antoniogottsfritz.desafio_android.Service;

import com.antoniogottsfritz.desafio_android.Model.API.ResultWrapper;
import com.antoniogottsfritz.desafio_android.Model.Comic;
import com.antoniogottsfritz.desafio_android.Model.MarvelCharacter;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MarvelApi {
    @GET("v1/public/characters")
    Call<ResultWrapper<MarvelCharacter>> getCharacters(@QueryMap Map<String, String> auth,
                                                       @QueryMap Map<String, String> pagination);

    @GET("v1/public/characters/{id}/comics")
    Call<ResultWrapper<Comic>> getCharacterComics(@QueryMap Map<String, String> auth,
                                                  @Path("id") int characterId);
}
