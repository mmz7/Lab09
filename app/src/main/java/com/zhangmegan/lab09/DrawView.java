package com.zhangmegan.lab09;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite = new Sprite();
    RectF lWall, rWall, tWall, bWall;
    final int moveNum = 13;
    Bitmap main;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        main = BitmapFactory.decodeResource(getResources(), R.drawable.wingedgirl);
        sprite = new Sprite(getWidth()/2-50, this.getHeight()/2-58,
                getWidth()/2+50, this.getHeight()/2+58, 0, moveNum);
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
        lWall = new RectF(0, 0, 80, this.getHeight());
        canvas.drawRect(lWall, new Paint());
        rWall = new RectF(getWidth()-80, 0, getWidth(), this.getHeight());
        canvas.drawRect(rWall, new Paint());
        tWall = new RectF(0, 0, getWidth(), 100);
        canvas.drawRect(tWall, new Paint());
        bWall = new RectF(0, this.getHeight()-100, getWidth(),
                this.getHeight());
        canvas.drawRect(bWall, new Paint());



        invalidate();
    }
}
