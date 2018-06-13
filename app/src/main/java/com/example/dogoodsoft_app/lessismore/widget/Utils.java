package com.example.dogoodsoft_app.lessismore.widget;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;

import com.bumptech.glide.load.engine.Resource;

public class Utils {


    public static float dpToPixel(float dp){

        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return dp * displayMetrics.density;

    }

}
