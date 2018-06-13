package com.example.dogoodsoft_app.lessismore;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

import com.example.dogoodsoft_app.lessismore.dagger.DaggerActivity;
import com.example.dogoodsoft_app.lessismore.aboutFile.FileActivity;
import com.example.dogoodsoft_app.lessismore.base.BaseActivity;
import com.example.dogoodsoft_app.lessismore.lifecycle.LifeCycleTestActiity;
import com.example.dogoodsoft_app.lessismore.mvp.RxMvpActivity;
import com.example.dogoodsoft_app.lessismore.retrofit.RetrofitActvity;
import com.example.dogoodsoft_app.lessismore.rxjvaa.RxActivity;
import com.example.dogoodsoft_app.lessismore.test.MainActivity;
import com.example.dogoodsoft_app.lessismore.test.X5WebViewActivity;
import com.example.dogoodsoft_app.lessismore.widget.ColorPickerView;
import com.example.dogoodsoft_app.lessismore.widget.NumberProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tyrantgit.explosionfield.ExplosionField;

public class Main3Activity extends BaseActivity {

    @BindView(R.id.colorview)
    ColorPickerView colorPickerView;

    @BindView(R.id.my_progress)
    NumberProgressBar bar;

    @OnClick(R.id.my_progress)
    public void clickProgress(){

        new Thread(() -> {

            int i =0;

            while (i < 100){

                try {
                    Thread.sleep(100);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int finalI = i;
                runOnUiThread(() -> {

                    bar.setProgress(finalI);

                });

            }




        }).start();

    }

    @OnClick(R.id.jump_1)
    public void jump1(){

        startActivity(new Intent(Main3Activity.this, MainActivity.class));

    }

    @OnClick(R.id.jump2)
    public void jump2(){

        startActivity(new Intent(Main3Activity.this, X5WebViewActivity.class));

    }

    @OnClick(R.id.jump3)
    public void jump3(){


        startActivity(new Intent(Main3Activity.this, RxActivity.class));
    }


    @OnClick(R.id.retrofit)
    public void retrofit(){


        startActivity(new Intent(Main3Activity.this, RetrofitActvity.class));
    }

    @OnClick(R.id.file)
    public void file(){


        startActivity(new Intent(Main3Activity.this, FileActivity.class));
    }

    @BindView(R.id.rounter)
    Button btn;
    @OnClick(R.id.rounter)
    public void rounter(){

        ExplosionField explosionField = new ExplosionField(this);
        explosionField.expandExplosionBound(100,100);
        explosionField.explode(btn);

    }


    @OnClick(R.id.dagger)
    public void dagger(){


        startActivity(new Intent(Main3Activity.this, DaggerActivity.class));
    }

    @OnClick(R.id.mvp)
    public void mvp(){


        startActivity(new Intent(Main3Activity.this, RxMvpActivity.class));
    }


    @OnClick(R.id.lifecycle)
    public void lifeCycle(){


        startActivity(new Intent(Main3Activity.this, LifeCycleTestActiity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ButterKnife.bind(this);


        colorPickerView.setOnColorPickerChangeListener(new ColorPickerView.OnColorPickerChangeListener() {
            @Override
            public void onColorChanged(ColorPickerView picker, int color) {


                getWindow().setBackgroundDrawable(new ColorDrawable(color));

            }

            @Override
            public void onStartTrackingTouch(ColorPickerView picker) {

            }

            @Override
            public void onStopTrackingTouch(ColorPickerView picker) {

            }
        });


    }
}
