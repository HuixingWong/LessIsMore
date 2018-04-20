package com.example.dogoodsoft_app.lessismore.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dogoodsoft-app on 2018/3/28.
 */

public class BaseActivity extends AppCompatActivity {

    public static int color = Color.BLUE;

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setBackgroundDrawable(new ColorDrawable(color));
    }
}
