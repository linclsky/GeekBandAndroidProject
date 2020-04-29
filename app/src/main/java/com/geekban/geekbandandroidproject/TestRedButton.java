package com.geekban.geekbandandroidproject;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2020/2/25.
 * 做一个圆形的红色按钮
 Ø 中间有一个白色的数字
 Ø 数字起始为20
 Ø 每点击一次减少1
 */

public class TestRedButton extends View implements View.OnClickListener{

    private Paint mPaint;
    private Rect mRect;
    private int mNumber = 20;
    private int mBackGroundColor;
    private int mTextSize;


    public TestRedButton(Context context) {
        this(context,null);

    }



    public TestRedButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public TestRedButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    private void init(Context context,AttributeSet attrs) {
        //创建一个画笔对象
        mPaint = new Paint();
        mRect = new Rect();
        this.setOnClickListener(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TestRedButton);
        mBackGroundColor = typedArray.getColor(R.styleable.TestRedButton_backgroundColor,Color.BLUE);
        // getDimension得到的是px值，需要转换为sp值
        mTextSize = Utils.px2sp(context, typedArray.getDimension(R.styleable.TestRedButton_textSize, 18));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //做一个圆形的红包按钮
        //设置画布为红色
        //mPaint.setColor(Color.RED);//设置红色画布
        mPaint.setColor(mBackGroundColor);

        canvas.drawCircle(getWidth() / 2,getHeight() / 2,getWidth() / 2,mPaint);
        //中间有一个白色的数字
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(mTextSize);
        //mRect是这个数字四周的边距
        String text = String.valueOf(mNumber);
        mPaint.getTextBounds(text,0,text.length(),mRect);
        int textWidth = mRect.width();
        int textHeight = mRect.height();
        canvas.drawText(text,getWidth() / 2 - textWidth / 2,getHeight()/2 + textHeight/2,mPaint);





    }

    @Override
    public void onClick(View v) {
        if (mNumber > 0) {
            mNumber--;

        }else {
            mNumber = 20;
        }
        invalidate();
    }
}
