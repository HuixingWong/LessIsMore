package com.example.dogoodsoft_app.lessismore.test;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dogoodsoft_app.lessismore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dogoodsoft-app on 2018/3/26.
 */

public class MainActivity extends AppCompatActivity{

    private NameViewModel mModel;
    @BindView(R.id.name_tv) TextView nameTv;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mModel = ViewModelProviders.of(this).get(NameViewModel.class);

        final  Observer<String> nameObserver = new Observer<String>(){


            @Override
            public void onChanged(@Nullable String s) {

                nameTv.setText(s);

            }
        };

        mModel.getCurrentName().observe(this,nameObserver);

    }


    @OnClick(R.id.btn) void changeText(View view) {

        String newName = "da Sb";

        mModel.getCurrentName().setValue(newName);
    }
}
