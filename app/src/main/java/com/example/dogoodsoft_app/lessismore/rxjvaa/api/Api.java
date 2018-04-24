package com.example.dogoodsoft_app.lessismore.rxjvaa.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface Api {
    @GET()
    Observable<Articledata> getCall();

}

