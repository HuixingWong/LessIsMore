package com.example.dogoodsoft_app.lessismore.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class NumberProgressBar extends View{

    private int mMaxProgress = 100;

    private int mCurrentProgress = 0;

    private int mReachedBarColor;

    private int mUnReachedBarColor;

    private int mTextColor;

    private float mTextSize;

    private float mReachedBarHeight;

    private float mUnreachedBarHeight;

    private String mSuffix = "%";

    private String mPrefix = "";

    private final int default_text_color = Color.rgb(66,145,241);

    private final  int default_reached_color = Color.rgb(66,145 ,241 );

    private final int default_unreached_color = Color.rgb(204,204 ,204 );

//    private final float default_progress_text_offset;





    public NumberProgressBar(Context context) {
        super(context);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public float dp2px(float dp){

        //density 屏幕的逻辑密度
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;

    }

    public float sp2px (float sp){

        //scaledDensity 这个属性是显示字体比例的因子
        final  float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;

    }
}
