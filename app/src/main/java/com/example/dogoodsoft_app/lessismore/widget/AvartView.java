package com.example.dogoodsoft_app.lessismore.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.dogoodsoft_app.lessismore.R;

public class AvartView extends View {


    private Paint mPaint;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;

    private int mEnumFormat = 0,mRadius = 5;



    public AvartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }




    public AvartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AvartView);

        int resourceId = typedArray.getResourceId(R.styleable.AvartView_src, -1);
        if (resourceId == -1){
            throw new RuntimeException("Avator need src attribute,and must is picture");
        }

        mBitmap = BitmapFactory.decodeResource(getResources(),resourceId);
        mEnumFormat = typedArray.getInt(R.styleable.AvartView_format,0);
        if (mEnumFormat == 1) {

            mRadius = typedArray.getInt(R.styleable.AvartView_radius,5);

        }

        typedArray.recycle();

        mPaint = new Paint();
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    }





}
