package com.example.dogoodsoft_app.lessismore.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalScrollViewEx extends ViewGroup{


    private static final String TAG = "HorizontalScrollViewEx";

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;

    //分别记录上次的华东的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    //分别记录上次滑动的坐标（onintercepttouchevent）
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mScroler;
    private VelocityTracker mVelocityTracker;


    public HorizontalScrollViewEx(Context context) {
        super(context);
    }




    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }


    private void init(){

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void smoothScrollBy(int dx,int dy){

    }


    @Override
    public void computeScroll() {
        super.computeScroll();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
