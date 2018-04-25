package com.example.dogoodsoft_app.lessismore.retrofit;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @Headers({
            "Accept-Encoding: application/json",
            "User-Agent: MyRetrofit"})
    @GET("article/{type}?dev=1")
    Call<Article> getArtical(@Path("type") String path);


    @Headers({
            "Accept-Encoding: application/json",
            "User-Agent: MyRetrofit"})
    @GET("article/day?dev=1")
    Call<Article> getSomeDay(@Query("date") String date);


    @Headers({
            "Accept-Encoding: application/json",
            "User-Agent: MyRetrofit"})
    @GET("article/{type}?dev=1")
    Observable<Article> getArticalRX(@Path("type") String path);
}