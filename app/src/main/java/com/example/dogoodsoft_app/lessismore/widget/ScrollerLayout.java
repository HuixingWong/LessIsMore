package com.example.dogoodsoft_app.lessismore.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class ScrollerLayout extends ViewGroup {


    //用于移动的scroller
    private Scroller mScroller;

    //最小移动
    private int mTouchSlop;

    //左边界
    private int leftBorder;

    //右边界
    private int rightBorder;


    private float mXDown;
    private float mXmove;

    private float mXLastMove;

    //滑动速度捕获
    private VelocityTracker mVelocityTracker;


    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);

        mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();

        mVelocityTracker = VelocityTracker.obtain();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {

            View childAt = getChildAt(i);

            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);


        }

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {


        if (b) {

            int childCount = getChildCount();
            for (int j = 0; j < childCount; j++) {

                View childAt = getChildAt(j);

                childAt.layout(j * childAt.getMeasuredWidth(), 0,
                        (j + 1) * childAt.getMeasuredWidth(), childAt.getMeasuredHeight());


            }

            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(getChildCount() - 1).getRight();

        }


    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:

                mXDown = ev.getRawX();
                mXLastMove = ev.getRawX();

                break;

            case MotionEvent.ACTION_MOVE:

                mXmove = ev.getRawX();
                float diff = Math.abs(mXmove - mXDown);

                mXLastMove = mXmove;

                if (diff > mTouchSlop) {

                    return true;

                }

                break;


        }


        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mVelocityTracker.addMovement(event);

        switch (event.getAction()) {


            case MotionEvent.ACTION_MOVE:

                mXmove = event.getRawX();
                int scrolleX = (int) (mXLastMove - mXmove);

                if (getScrollX() + scrolleX < leftBorder) {

                    scrollTo(leftBorder, 0);
                    return true;

                } else if (getScrollX() + getWidth() + scrolleX > rightBorder) {

                    scrollTo(rightBorder - getWidth(), 0);

                    return true;

                }

                scrollBy(scrolleX, 0);

                mXLastMove = mXmove;


                break;


            case MotionEvent.ACTION_UP:

                mVelocityTracker.computeCurrentVelocity(1000);

                float xVelocity = mVelocityTracker.getXVelocity();

                int targetIndex;

                if (xVelocity >= 100) {

                    targetIndex = getScrollX() / getWidth();

                } else if (xVelocity <= -100) {

                    targetIndex = getScrollX() / getWidth() + 1;
                    if (targetIndex > getChildCount() - 1) {
                        targetIndex--;
                    }
                } else {

                    targetIndex = (getScrollX() + getWidth() / 2) / getWidth();

                }


                int dx = targetIndex * getWidth() - getScrollX();

                mScroller.startScroll(getScrollX(), 0, dx, 0);

                invalidate();

                break;


        }


        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {


            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();

        }

    }
}
