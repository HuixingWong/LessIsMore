package com.example.dogoodsoft_app.lessismore.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @Headers({
            "Accept-Encoding: application/json",
            "User-Agent: MyRetrofit"})
    @GET("article/random?dev=1")
    Call<Article> getArtical();

    @Headers({
            "Accept-Encoding: application/json",
            "User-Agent: MyRetrofit"})
    @GET("article/day?dev=1")
    Call<Article> getSomeDay(@Query("date") String date);
}