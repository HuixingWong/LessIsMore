package com.example.dogoodsoft_app.lessismore.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogoodsoft_app.lessismore.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class RxMvpActivity extends AppCompatActivity implements Contract_vp.View{

    private Contract_vp.Presenter presenter;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_mvp);
        textView =findViewById(R.id.textView);

        presenter = new ImplPresent(this);

        findViewById(R.id.button).setOnClickListener(view ->
                presenter.loadData());


        Throwable a = new Throwable("what a fuck");


        RxView.clicks(textView)
                .throttleFirst(10, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                        textView.setText(a.getMessage());

                        Toast.makeText(RxMvpActivity.this, "what a fuck", Toast.LENGTH_SHORT).show();

                    }
                });



    }

    @Override
    public void update(String str) {

        textView.setText(Html.fromHtml(str));



    }
}
