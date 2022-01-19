package com.zhangmegan.lab09;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite, monster;
    RectF lWall, rWall, tWall, bWall, scoreDisplay;
    final int moveNum = 15;
    Bitmap main, monsterBm, strawberry;
    Rect src;
    Sprite[] monsters = new Sprite[7];
    int slowcount = 0;
    float centerx, centery;
    int score = 0, prevScore = -1, mCount = 0;
    Paint text = new Paint();
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        main = BitmapFactory.decodeResource(getResources(), R.drawable.wingedgirl);
        sprite = new Sprite(getWidth()/2-50, this.getHeight()/2-58,
                getWidth()/2+50, this.getHeight()/2+58, 0, moveNum);
        sprite.setBitmapDim(4, 3, 1);
        sprite.setBitmap(main);

        strawberry = BitmapFactory.decodeResource(getResources(), R.drawable.strawberry4);
        src = new Rect(0, 0, strawberry.getWidth(), strawberry.getHeight());

        monsterBm = BitmapFactory.decodeResource(getResources(), R.drawable.monstersprite);
        for(int i = 0; i < monsters.length; i++) {
            float cx = (float) (Math.random()*(getWidth()-130-131)+130);
            float cy = (float) (Math.random()*(getHeight()-158-159)+158);
            if(Math.random() < .5)
                monsters[i] = new Sprite(cx-50, cy-58, cx+50, cy+58, 0, 10);
            else
                monsters[i] = new Sprite(cx-50, cy-58, cx+50, cy+58, 10, 0);
            monsters[i].setBitmapDim(4, 3, 1);
            monsters[i].setBitmap(monsterBm);
        }
        centerx = (float) (Math.random()*(getWidth()-120-121)+120);
        centery = (float) (Math.random()*(getHeight()-140-141)+140);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF temp = new RectF(centerx-30, centery-30, centerx+30, centery+30);
        canvas.drawBitmap(strawberry, src, temp, null);
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

        scoreDisplay = new RectF(90, this.getHeight()-90, 170, this.getHeight()-10);
        canvas.drawBitmap(strawberry, src, scoreDisplay, null);
        text.setColor(Color.WHITE);
        text.setTextSize(80);
        String scoreText = "x"+score;
        canvas.drawText(scoreText, 180, this.getHeight()-20, text);

        if(sprite.contains(temp)) {
            centerx = (float) (Math.random()*(getWidth()-120-121)+120);
            centery = (float) (Math.random()*(getHeight()-140-141)+140);
            prevScore = score;
            score++;
        }

        if((score > prevScore && score % 4 == 0) && mCount < monsters.length) {
            mCount++;
            if(RectF.intersects(monsters[mCount-1], sprite)) {
                float cx = (float) (Math.random()*(getWidth()-130-131)+130);
                float cy = (float) (Math.random()*(getHeight()-158-159)+158);
                if(Math.random() < .5)
                    monsters[mCount-1] = new Sprite(cx-50, cy-58, cx+50, cy+58, 0, 10);
                else
                    monsters[mCount-1] = new Sprite(cx-50, cy-58, cx+50, cy+58, 10, 0);
            }
            prevScore = score;
        }

        for (int i = 0; i < mCount; i++) {
            monsters[i].draw(canvas);
            if(RectF.intersects(monsters[i], tWall) || RectF.intersects(monsters[i], bWall)) {
                monsters[i].offset(0, -monsters[i].getdY());
                monsters[i].setdY(-monsters[i].getdY());
            }
            if(RectF.intersects(monsters[i], rWall) || RectF.intersects(monsters[i], lWall)) {
                monsters[i].offset(-monsters[i].getdX(), 0);
                monsters[i].setdX(-monsters[i].getdX());
            }
        }

        slowcount++;
        if(slowcount == 10) {
            for(int i = 0; i < mCount; i++) {
                monsters[i].update();
            }
            slowcount = 0;
        }

        invalidate();
    }
}
