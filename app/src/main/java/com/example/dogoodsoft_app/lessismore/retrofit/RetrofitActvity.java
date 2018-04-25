package com.example.dogoodsoft_app.lessismore.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogoodsoft_app.lessismore.R;
import com.example.dogoodsoft_app.lessismore.utils.DateUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActvity extends AppCompatActivity {

    String mCurr;

    public static final String RANDOM_TYPE = "random";
    public static final String TODAY_TYPE = "today";

    //用来防止内存泄漏什么的，在退出Activity的时候用来切断水管。
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.tv)
    TextView mTv;

    @BindView(R.id.messages)
    TextView mMessage;

    @OnClick(R.id.today)
    public void today() {

        retrofitWithRx(TODAY_TYPE);

    }

    @OnClick(R.id.random)
    public void random() {

        retrofitWithRx(RANDOM_TYPE);

    }

    @OnClick(R.id.after)
    public void after() {

        nextDay();

    }

    @OnClick(R.id.before)
    public void before() {

        beforeDay();

    }

    String BASE_URL = "https://interface.meiriyiwen.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_actvity);

        ButterKnife.bind(this);


        retrofitWithRx(TODAY_TYPE);

        retrofitWithRx2(RANDOM_TYPE);

    }


    public void retrofit1(String type) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        Call<Article> call = api.getArtical(type);

        call.enqueue(new Callback<Article>(

        ) {

            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {


                Article.Data data = response.body().getData();
                mMessage.setText(data.getTitle() + "     " + data.getAuthor() + "," + data.getDate().getCurr());
                mTv.setText(Html.fromHtml(data.getContent()));

                mCurr = data.getDate().getCurr();


            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {


                Toast.makeText(RetrofitActvity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void nextDay() {


        mCurr = DateUtils.getNext(mCurr);


        retrofit2(mCurr);

    }

    private void beforeDay() {


        mCurr = DateUtils.getBefore(mCurr);


        retrofit2(mCurr);

    }


    public void retrofit2(String data) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        Call<Article> call = api.getSomeDay(data);

        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {

                Article.Data data = response.body().getData();
                mMessage.setText(data.getTitle() + "     " + data.getAuthor() + "," + data.getDate().getCurr());
                mTv.setText(Html.fromHtml(data.getContent()));

            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {

            }
        });


    }


    public void retrofitWithRx(String type) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//Gson适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rxjva适配器
                .build();


        Api api = retrofit.create(Api.class);

        api.getArticalRX(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Article>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Article article) {

                        Article.Data data = article.getData();
                        mMessage.setText(data.getTitle() + "     " + data.getAuthor() + "," + data.getDate().getCurr());
                        mTv.setText(Html.fromHtml(data.getContent()));

                        mCurr = data.getDate().getCurr();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void retrofitWithRx2(String type) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//Gson适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rxjva适配器
                .build();


        Api api = retrofit.create(Api.class);

        api.getArticalRX(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Article, String>() {

                    @Override
                    public String apply(Article article) {
                        return article.getData().getContent();
                    }
                }).subscribe(new Consumer<String>() {


            @Override
            public void accept(String s) throws Exception {

                Toast.makeText(RetrofitActvity.this,
                        "the content is :" + s, Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}
