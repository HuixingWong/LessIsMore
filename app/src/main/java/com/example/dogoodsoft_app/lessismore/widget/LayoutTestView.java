package com.example.dogoodsoft_app.lessismore.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LayoutTestView extends View{


    public LayoutTestView(Context context) {
        super(context);
    }

    public LayoutTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //布局一个正方形的View
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();

        if (measuredHeight > measuredWidth){

            measuredHeight = measuredWidth;

        }else {

            measuredWidth = measuredHeight;
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
