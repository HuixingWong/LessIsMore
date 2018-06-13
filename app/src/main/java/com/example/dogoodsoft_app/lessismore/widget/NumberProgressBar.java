package com.example.dogoodsoft_app.lessismore.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;

import com.example.dogoodsoft_app.lessismore.R;

import java.security.PublicKey;

public class NumberProgressBar extends View {

    private final int default_text_color = Color.rgb(66, 145, 241);
    private final int default_reached_color = Color.rgb(66, 145, 241);
    private final int default_unreached_color = Color.rgb(204, 204, 204);
    private final float default_progress_text_offset;
    private final float default_text_size;
    private final float default_reached_bar_height;
    private final float default_unreached_bar_height;
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


    public enum ProgresstextVisibility{

        Visible,Invisible

    }

    //=================================================
    private float mDrawTextWidth;

    private float mDrawTextStart;

    private float mDrawTextEnd;

    private String mCurrentDrawText;

    private Paint mReacheadBarPaint;

    private Paint mUnReacheadbarPaint;

    private Paint mTextPaint;

    private RectF mUnreacheadRectF = new RectF(0, 0, 0, 0);

    private RectF mReachedF = new RectF(0, 0, 0, 0);

    private float mOffset;

    private boolean mDrawUnreachedBar = true;

    private boolean mDrawReacheadBar = true;

    private boolean mIFDrawText = true;

    private OnProgressBarListener mListener;


    public NumberProgressBar(Context context) {
        this(context, null);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        default_reached_bar_height = dp2px(1.5f);
        default_unreached_bar_height = dp2px(1.0f);
        default_text_size = sp2px(10);
        default_progress_text_offset = dp2px(3.0f);


        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NumberProgressBar, defStyleAttr, 0);

        mReachedBarColor = attributes.getColor(R.styleable.NumberProgressBar_progress_reached_color, default_reached_color);
        mUnReachedBarColor = attributes.getColor(R.styleable.NumberProgressBar_progress_unreached_color, default_unreached_color);
        mTextColor = attributes.getColor(R.styleable.NumberProgressBar_progress_text_color, default_text_color);
        mTextSize = attributes.getDimension(R.styleable.NumberProgressBar_progress_text_size, default_text_size);

        mReachedBarHeight = attributes.getDimension(R.styleable.NumberProgressBar_progress_reached_bar_height, default_reached_bar_height);
        mUnreachedBarHeight = attributes.getDimension(R.styleable.NumberProgressBar_progress_unreached_bar_height, default_unreached_bar_height);
        mOffset = attributes.getDimension(R.styleable.NumberProgressBar_progress_text_offset, default_progress_text_offset);


        attributes.recycle();


        initializePainters();

    }

    public float dp2px(float dp) {

        //density 屏幕的逻辑密度
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;

    }

    public float sp2px(float sp) {

        //scaledDensity 这个属性是显示字体比例的因子
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;

    }

    private void initializePainters() {

        mReacheadBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mReacheadBarPaint.setColor(mReachedBarColor);

        mUnReacheadbarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUnReacheadbarPaint.setColor(mUnReachedBarColor);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));

    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) Math.max(mTextSize, Math.max(mReachedBarHeight, mUnreachedBarHeight));
    }

    @Override
    public int getMinimumWidth() {

        return (int) mTextSize;

    }

    private int measure(int measureSpec, boolean isWidth) {


        int result;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();

        //EXACTLY 对应的情况是 MATCH_partent和制定确定的大小的时候
        if (mode == MeasureSpec.EXACTLY) {

            result = size;

        } else {

            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;

            //AT_MOST 对应的情况是用户设置为wrap_content时候
            if (mode == MeasureSpec.AT_MOST) {

                if (isWidth) {
                    result = Math.max(result, size);
                } else {
                    result = Math.min(result, size);
                }
            }
        }
        return result;
    }

    private void calculateDrawRectFWithoutProgressText(){

        mReachedF.left = getPaddingLeft();
        //整个view的高度/2 再减去 bar的高度的二分之一
        mReachedF.top = getHeight()/2.0f - mReachedBarHeight/2.0f;
        //使用全部的宽度减去左右边距，再除以一共多少份 ，再乘以当前多少份，再加上左边距，就是已经到达的距离的右顶点了
        mReachedF.right = (getWidth() - getPaddingLeft() - getPaddingRight()) / (getmax()*1.0f) * getprogress() + getPaddingLeft();
        mReachedF.bottom = getHeight() / 2.0f + mReachedBarHeight/2.0f;

        mUnreacheadRectF.left = mReachedF.right;
        mUnreacheadRectF.right = getWidth() - getPaddingRight();
        mUnreacheadRectF.top = getHeight() / 2.0f - mUnreachedBarHeight / 2.0f;
        mUnreacheadRectF.bottom = getHeight() / 2.0f + mUnreachedBarHeight / 2.0f;

    }



    private void  calculateDrawRectF(){


        mCurrentDrawText = String.format("%d",getprogress() * 100 / getmax());
        //要绘制的文字加上前缀和后缀后缀默认是百分号
        mCurrentDrawText = mPrefix + mCurrentDrawText + mSuffix;
        //测量字体的宽度
        mDrawTextWidth = mTextPaint.measureText(mCurrentDrawText);

        if (getprogress() == 0){

            mDrawReacheadBar = false;
            mDrawTextStart = getPaddingLeft();

        }else {

            mDrawReacheadBar = true;
            mReachedF.left = getPaddingLeft();
            mReachedF.top = getHeight()/ 2.0f - mReachedBarHeight /2.0f;
            mReachedF.right = (getWidth() - getPaddingLeft() - getPaddingRight()) / getmax() * getprogress() + getPaddingLeft() - mOffset;
            mReachedF.bottom = getHeight() / 2.0f + mReachedBarHeight/2.0f;

            mDrawTextStart = mReachedF.right + mOffset;
        }

        //ascent 是常见字符的最高点，descent 是常见字符的最低点，它们的平均值就是常见字符的中点喽,这个位置为什么要用view高度的一半减去中间点
        mDrawTextEnd = getHeight() / 2.0f - (mTextPaint.descent() + mTextPaint.ascent())/2.0f;

        if ((mDrawTextStart + mDrawTextWidth) >= getWidth() -getPaddingRight()){

            mDrawTextStart = getWidth() - getPaddingRight() - mDrawTextWidth;

            mReachedF.right = mDrawTextStart - mOffset;


        }

        float unReachedBarStart = mDrawTextStart + mDrawTextWidth + mOffset;

        if (unReachedBarStart >= getWidth() - getPaddingRight()){

            mDrawUnreachedBar = false;

        }else {


            mDrawUnreachedBar = true;
            mUnreacheadRectF.left = unReachedBarStart;
            mUnreacheadRectF.right = getWidth() - getPaddingRight();
            mUnreacheadRectF.top = getHeight()/ 2.0f - mUnreachedBarHeight /2.0f;
            mUnreacheadRectF.bottom = getHeight()/2.0f + mUnreachedBarHeight/2.0f;

        }




    }



    public int getTextColor(){

        return  mTextColor;
    }

    public int getmax (){

        return mMaxProgress;

    }

    public int getprogress(){

        return mCurrentProgress;

    }

    public float getProgressTextSize(){

        return  mTextSize;

    }

    public int getUnreachedBarColor(){

        return mUnReachedBarColor;
    }

    public int getReachedbarColor(){


        return mReachedBarColor;

    }

    public float getmReachedBarHeight(){

        return mReachedBarHeight;

    }

    public float getmUnreachedBarHeight(){

        return mUnreachedBarHeight;
    }

    public void setProgressTextSize(float textSize){


        this.mTextSize = textSize;
        mTextPaint.setTextSize(mTextSize);
        invalidate();

    }


    public void  setProgresstextColor(int textColor){

        this.mTextColor = textColor;
        mTextPaint.setColor(textColor);
        invalidate();

    }

    public void setUnreachedbarColor(int barColor){

        this.mUnReachedBarColor = barColor;
        mUnReacheadbarPaint.setColor(mUnReachedBarColor);
        invalidate();
    }

    public void setReachedbarColor(int progressColor){

        this.mReachedBarColor = progressColor;
        mReacheadBarPaint.setColor(mReachedBarColor);
        invalidate();

    }

    public void setReachedbarheight(float height){

        mReachedBarHeight = height;


    }

    public void setUnreachedBarheight(float height){

        mUnreachedBarHeight = height;
    }

    public void setMax(int maxProgress){

        if (maxProgress > 0){
            this.mMaxProgress = maxProgress;
            invalidate();
        }

    }

    public void setSuffix(String suffix){

        if (suffix == null){
            mSuffix = "";
        }else {
            mSuffix = suffix;
        }

    }


    public String getmSuffix(){

        return mSuffix;
    }

    public void setmPrefix(String prefix){

        if (prefix == null){

            mPrefix = "";

        }else {

            mPrefix = prefix;


        }

    }


    public void  incrementProgressBy(int by){


        if (by > 0){

            setProgress(getprogress() + by);

        }

        if (mListener != null){

            mListener.onProgressChage(getprogress(),getmax() );

        }

    }

    public void setProgress(int progress) {

        if (progress <= getmax() && progress >= 0){

            this.mCurrentProgress = progress;
            invalidate();

        }


    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (mIFDrawText){
            calculateDrawRectF();
        }else {
            calculateDrawRectFWithoutProgressText();
        }

        if (mDrawReacheadBar){
            canvas.drawRect(mReachedF,mReacheadBarPaint );
        }

        if (mDrawUnreachedBar){
            canvas.drawRect(mUnreacheadRectF,mUnReacheadbarPaint );
        }

        if (mIFDrawText){
            canvas.drawText(mCurrentDrawText,mDrawTextStart ,mDrawTextEnd ,mTextPaint );
        }



    }


    public void setProgressTextVisible(ProgresstextVisibility visibility){

        mIFDrawText = visibility == ProgresstextVisibility.Visible;
        invalidate();


    }

    public void setOnProgressBarlistener(OnProgressBarListener listener){
        mListener = listener;
    }







}
