package com.zhangmegan.lab09;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite = new Sprite();
    Bitmap main;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        main = BitmapFactory.decodeResource(getResources(), R.drawable.wingedgirl);
        sprite = new Sprite(getWidth()/2-60, this.getHeight()/2-70,
                getWidth()/2+60, this.getHeight()/2+70, 0, 5);
        sprite.setBitmapDim(4, 3, 1);
        sprite.setBitmap(main);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        sprite.draw(canvas);
        invalidate();
    }
}
