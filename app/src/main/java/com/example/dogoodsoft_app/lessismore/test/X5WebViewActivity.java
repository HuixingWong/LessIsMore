package com.example.dogoodsoft_app.lessismore.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dogoodsoft_app.lessismore.R;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class X5WebViewActivity extends AppCompatActivity {

    @BindView(R.id.x5test)
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x5_web_view);

        ButterKnife.bind(this);

        webView.loadUrl("https://www.baidu.com");

    }
}
