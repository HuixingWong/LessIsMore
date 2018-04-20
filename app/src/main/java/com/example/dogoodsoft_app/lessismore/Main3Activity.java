package com.example.dogoodsoft_app.lessismore;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dogoodsoft_app.lessismore.base.BaseActivity;
import com.example.dogoodsoft_app.lessismore.test.MainActivity;
import com.example.dogoodsoft_app.lessismore.widget.ColorPickerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skin.support.SkinCompatManager;

public class Main3Activity extends BaseActivity {

    @BindView(R.id.colorview)
    ColorPickerView colorPickerView;

    @OnClick(R.id.jump_1)
    public void jump1(){

        startActivity(new Intent(Main3Activity.this, MainActivity.class));

    }

    @OnClick(R.id.jump2)
    public void jump2(){



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
