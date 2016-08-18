package com.geekband.Test01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by MirQ on 16/7/14.
 */
public class TestRedButton extends View implements View.OnClickListener {

    public final static String TAG = TestRedButton.class.getSimpleName();

    private Paint mPaint;

    private Rect mRect;

    private int mBackgroundColor;

    private int mNumber = 20;
    private int mTextSize;

    public TestRedButton(Context context) {
        this(context, null);
    }

    public TestRedButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestRedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mRect = new Rect();
        this.setOnClickListener(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestRedButton);

        mBackgroundColor = typedArray.getColor(R.styleable.TestRedButton_backgroundColor, Color.RED);
//        float dpValue =  typedArray.getDimension(R.styleable.TestRedButton_textSize, 20.0f);
        float scale = context.getResources().getDisplayMetrics().density;
        Log.i(TAG, "density 为 -> " + context.getResources().getDisplayMetrics());
//        mTextSize = (int)(dpValue * scale + 0.5f);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.TestRedButton_textSize, 20);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 做一个圆形的红色按钮
        // 设置画笔为红色

        /**
         *  画一个红色的圆
         */
//      mPaint.setColor(Color.RED);
        mPaint.setColor(mBackgroundColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);


        // 中间有一个白色的数字
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(mTextSize);


        /**
         * mRect是这个数字的四周的边距
         */
        String text = String.valueOf(mNumber);
        // 得到字符串的边界长度
        mPaint.getTextBounds(text, 0, text.length(), mRect);

        int textWidth = mRect.width();
        int textHeight = mRect.height();

        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, mPaint);
    }

    @Override
    public void onClick(View v) {

        if (mNumber > 0) {
            mNumber--;
        } else {
            mNumber = 20;
        }
        invalidate();
    }
}
