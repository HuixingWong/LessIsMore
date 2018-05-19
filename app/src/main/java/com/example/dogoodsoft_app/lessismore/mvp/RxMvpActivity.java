package com.example.dogoodsoft_app.lessismore.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.example.dogoodsoft_app.lessismore.R;

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



    }

    @Override
    public void update(String str) {

        textView.setText(Html.fromHtml(str));

    }
}
