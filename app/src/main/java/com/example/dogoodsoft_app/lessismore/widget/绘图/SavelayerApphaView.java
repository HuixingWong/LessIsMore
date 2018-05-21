package com.example.dogoodsoft_app.lessismore.widget.绘图;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SavelayerApphaView extends View{

    private Paint mPaint;
    public SavelayerApphaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(100,100,300,300,mPaint);

        @SuppressLint("WrongConstant") int layerID = canvas.saveLayerAlpha(0,0,600,600,0x88,Canvas.CLIP_SAVE_FLAG);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(200,200,400,400,mPaint);
        canvas.restoreToCount(layerID);

    }
}


