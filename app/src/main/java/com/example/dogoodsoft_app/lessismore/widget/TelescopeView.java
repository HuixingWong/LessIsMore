package com.example.dogoodsoft_app.lessismore.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.graphics.BitmapCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.dogoodsoft_app.lessismore.R;

public class TelescopeView extends View{

    private Paint mPaint;
    private Bitmap mBitmap,mBitmpBG;
    private int mDx = -1,mDy = -1;


    public TelescopeView(Context context) {
        super(context);
        init();
    }



    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    private void init() {

        mPaint = new Paint();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.swz_pic);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){


            case MotionEvent.ACTION_DOWN:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                postInvalidate();
                return  true;

            case MotionEvent.ACTION_MOVE:

                mDx = (int) event.getX();
                mDy = (int) event.getY();
                break;

            case MotionEvent.ACTION_UP:
                mDx = -1;
                mDy = -1;
                break;



        }

        postInvalidate();

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmpBG == null){

            mBitmpBG = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);

            Canvas bgCavans = new Canvas(mBitmpBG);

            bgCavans.drawBitmap(mBitmap,null,new Rect(0,0,getWidth(),getHeight()),mPaint);


        }


        if (mDx != -1 && mDy != -1) {
            mPaint.setShader(new BitmapShader(mBitmpBG, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            canvas.drawCircle(mDx, mDy, 150, mPaint);
        }



    }
}
