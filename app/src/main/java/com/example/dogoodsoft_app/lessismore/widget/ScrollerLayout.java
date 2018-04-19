package com.example.dogoodsoft_app.lessismore.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class ScrollerLayout extends ViewGroup{


    private Scroller mScroller;

    private int mTouchSlop;

    private int leftBorder;

    private int rightBorder;




    private float mXDown;
    private float mXmove;

    private float mXLastMove;





    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);

       mTouchSlop =  viewConfiguration.getScaledPagingTouchSlop();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i <childCount ; i++) {

            View childAt = getChildAt(i);

            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);


        }

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {


        if (b){

            int childCount = getChildCount();
            for (int j = 0; j < childCount; j++) {

                View childAt = getChildAt(j);

                childAt.layout(j*childAt.getMeasuredWidth(),0,
                        (j+1)*childAt.getMeasuredWidth(),childAt.getMeasuredHeight());



            }

            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(getChildCount()-1).getRight();

        }


    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:

                mXDown = ev.getRawX();
                mXLastMove = ev.getRawX();

                break;

            case MotionEvent.ACTION_MOVE:

                mXmove = ev.getRawX();
                float diff = Math.abs(mXmove - mXDown);

                mXLastMove = mXmove;

                if (diff > mTouchSlop){

                    return true;

                }

                break;




        }


        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){


            case MotionEvent.ACTION_MOVE:

                mXmove = event.getRawX();
                int scrolleX = (int) (mXLastMove - mXmove);

                if (getScrollX() + scrolleX < leftBorder){

                    scrollTo(leftBorder,0);
                    return true;

                }else if (getScrollX() + getWidth() + scrolleX > rightBorder){

                    scrollTo(rightBorder - getWidth() ,0);

                    return true;

                }

                scrollBy(scrolleX,0);

                mXLastMove = mXmove;


                break;



            case MotionEvent.ACTION_UP:

                int targetIndex = (getScrollX()+getWidth()/2)/getWidth();

                int dx = targetIndex * getWidth() - getScrollX();

                mScroller.startScroll(getScrollX(),0,dx,0);

                invalidate();

                break;



        }


        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){


            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();

        }

    }
}
