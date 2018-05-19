package com.example.dogoodsoft_app.lessismore.mvp;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImplPresent extends Contract_vp.Presenter{

    private Contract_vp.View view;

    String BASE_URL = "https://interface.meiriyiwen.com/";

    public ImplPresent(Contract_vp.View view) {
        super(view);
        this.view = view;
    }

    @Override
    void loadData() {

//        view.update("you are big sb");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//Gson适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rxjva适配器
                .build();

        Api api = retrofit.create(Api.class);

        api.getArticalRX("random")
                .subscribeOn(Schedulers.io())
                .map(article -> article.getData().getContent()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> view.update(s));


    }

}
