package com.liyulong.circleprogresslib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/12/25.
 */
public class CircleProgressView extends View {
    private static final String TAG = "CircleProgressBar";

    private int mMaxProgress = 100;

    private int mProgress = 0;
    private int currentprogress = 0;
    private int mCircleLineStrokeWidth = 30;

    private final int mTxtStrokeWidth = 2;

    private final int speed=1;

    private boolean isAnime = false;

    // 画圆所在的距形区域
    private final RectF mRectF;

    private final Paint mPaint;

    private final Context mContext;

    private String mTxtHint1;

    private String mTxtHint2;
    private int rgb;


    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        mContext = context;
        mRectF = new RectF();
        mPaint = new Paint();
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorCircleView);
        rgb = ta.getColor(R.styleable.ColorCircleView_circle_color, Color.RED);
        mProgress = ta.getInt(R.styleable.ColorCircleView_circle_progress, 0);
//        mCircleLineStrokeWidth = Dp2Px(context, ta.getInt(R.styleable.ColorCircleView_circle_stoken, 10));
        mCircleLineStrokeWidth = (int) ta.getDimension(R.styleable.ColorCircleView_circle_stoken, 10);
        isAnime = ta.getBoolean(R.styleable.ColorCircleView_circle_isAnime, false);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }

        // 设置画笔相关属性
        mPaint.setAntiAlias(true);

        mPaint.setColor(Color.rgb(0xe9, 0xe9, 0xe9));
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        // 位置
        mRectF.left = mCircleLineStrokeWidth / 2; // 左上角x
        mRectF.top = mCircleLineStrokeWidth / 2; // 左上角y
        mRectF.right = width - mCircleLineStrokeWidth / 2; // 左下角x
        mRectF.bottom = height - mCircleLineStrokeWidth / 2; // 右下角y

        // 绘制圆圈，进度条背景
        canvas.drawArc(mRectF, -90, 360, false, mPaint);

        mPaint.setColor(rgb);
        if (isAnime) {
            if (currentprogress <= mProgress) {
                canvas.drawArc(mRectF, -90, ((float) currentprogress / mMaxProgress) * 360, false, mPaint);
                currentprogress += speed;
                invalidate();
            } else {
                canvas.drawArc(mRectF, -90, ((float) mProgress / mMaxProgress) * 360, false, mPaint);
            }
        } else {
            canvas.drawArc(mRectF, -90, ((float) mProgress / mMaxProgress) * 360, false, mPaint);
        }
//
    }

    public int getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        this.invalidate();
    }

    public void setProgressNotInUiThread(int progress) {
        this.mProgress = progress;
        this.postInvalidate();
    }

    public  void setAnime(boolean anime){
        this.isAnime=anime;
        this.invalidate();
    }

    public  void setstoken(int stoken){
        mCircleLineStrokeWidth=stoken;
        this.invalidate();
    }

    public void setColor(int rbg) {
        this.rgb = rbg;
    }



    public String getmTxtHint1() {
        return mTxtHint1;
    }

    public void setmTxtHint1(String mTxtHint1) {
        this.mTxtHint1 = mTxtHint1;
    }

    public String getmTxtHint2() {
        return mTxtHint2;
    }

    public void setmTxtHint2(String mTxtHint2) {
        this.mTxtHint2 = mTxtHint2;
    }

    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
