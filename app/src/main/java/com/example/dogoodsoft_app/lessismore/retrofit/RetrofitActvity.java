package com.example.dogoodsoft_app.lessismore.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogoodsoft_app.lessismore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActvity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView mTv;

    @BindView(R.id.messages)
    TextView mMessage;


    String BASE_URL = "https://interface.meiriyiwen.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_actvity);

        ButterKnife.bind(this);


        retrofit1();



    }


    public void retrofit1(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        Call<Article> call = api.getArtical();

        call.enqueue(new Callback<Article>(

        ) {

            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {

                if (response == null){
                    Toast.makeText(RetrofitActvity.this, "response is null", Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(RetrofitActvity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                }

//                mTv.setText(response.body().getData().getContent());

                Article.Data data = response.body().getData();
                mMessage.setText(data.getTitle()+"     "+data.getAuthor()+","+data.getDate().getCurr());
                mTv.setText(Html.fromHtml(data.getContent()));

                String curr = data.getDate().getCurr();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = Integer.parseInt(curr);

                retrofit2((i+1)+"");


            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {



                Toast.makeText(RetrofitActvity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }
    public void retrofit2(String data){

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
                mMessage.setText(data.getTitle()+"     "+data.getAuthor()+","+data.getDate().getCurr());
                mTv.setText(Html.fromHtml(data.getContent()));

            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {

            }
        });


    }
}
