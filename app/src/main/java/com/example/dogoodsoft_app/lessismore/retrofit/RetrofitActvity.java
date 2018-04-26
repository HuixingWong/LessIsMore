package com.example.dogoodsoft_app.lessismore.retrofit;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogoodsoft_app.lessismore.R;
import com.example.dogoodsoft_app.lessismore.utils.DateUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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

    private static final String FIRST_DAY = "20110306";
    private static final String BEFORE_FIRST = "20110305";
    private static final String EMPTY_DAY = "20110307";
    private static final String EMPTY_NEXT = "20110308";


    private static final int BEFORE = 0;
    private static final int NEXT = 1;
    private static final int SOMEDAY = 2;

    public static final String TAG = "FUCK";


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

    @OnClick(R.id.first)
    public void first() {

        mCurr = FIRST_DAY;
        retrofit2(mCurr, 2);
    }

    String BASE_URL = "https://interface.meiriyiwen.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_actvity);

        ButterKnife.bind(this);


        retrofitWithRx(TODAY_TYPE);

        retrofitWithRx3(RANDOM_TYPE);

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

        if (mCurr.equals(EMPTY_DAY)) {
            mCurr = EMPTY_NEXT;
        }

        retrofit2(mCurr, 1);

    }

    private void beforeDay() {


        mCurr = DateUtils.getBefore(mCurr);

        if (mCurr.equals(BEFORE_FIRST)) {
            mCurr = FIRST_DAY;
        }


        retrofit2(mCurr, 0);

    }


    public void retrofit2(String data, final int type) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        Call<Article> call = api.getSomeDay(data);

        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {

                if (response.body() == null) {
                    if (type == BEFORE) {
                        before();
                        return;
                    }

                    if (type == NEXT) {
                        nextDay();
                        return;
                    }
                }
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
                .map(new Function<Article, String>() {

                    @Override
                    public String apply(Article article) {
                        return article.getData().getContent();
                    }
                }).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {

                        Toast.makeText(RetrofitActvity.this,
                                "the content is :" + s, Toast.LENGTH_LONG).show();

                    }
                });

    }


    /**
     * subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.
     * <p>
     * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
     * <p>
     * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
     *
     * @param type
     */
    public void retrofitWithRx3(String type) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//Gson适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rxjva适配器
                .build();


        Api api = retrofit.create(Api.class);

        api.getArticalRX(type)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Article, ObservableSource<Article.Data>>() {
                    @Override
                    public ObservableSource<Article.Data> apply(Article article) {

                        Log.e(TAG, Thread.currentThread().getName());
                        return Observable.fromArray(article.getData());

                    }
                }).subscribeOn(Schedulers.io())//第二次又subscribeon不管用
                .map(new Function<Article.Data, String>() {
                    @Override
                    public String apply(Article.Data data) throws Exception {
                        Log.e(TAG, Thread.currentThread().getName());
                        return data.getContent();
                    }
                }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {

                return s.length() < 10;//过滤字符串的长度

            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, Thread.currentThread().getName());
                        Toast.makeText(RetrofitActvity.this, "" + s, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}
