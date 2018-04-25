package com.example.dogoodsoft_app.lessismore.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Api {

    @Headers({
            "Accept-Encoding: application/json",
            "User-Agent: MoonRetrofit"})
    @GET("article/today?dev=1")
    Call<Article> getArtical();
}