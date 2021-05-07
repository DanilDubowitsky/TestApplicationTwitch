package com.example.testapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolder {
    @GET("kraken/games/top")
    Call<GamesModel>getTopGames(@Query("limit")Integer limit,@Header("Accept") String accept, @Header("Client-ID") String clientId);

}
