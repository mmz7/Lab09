package com.zhangmegan.lab09;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite extends RectF {
    private int dX, dY, color;
    private Bitmap bitmap;
    private int BM_COLUMNS;
    private int BM_ROWS, frameX;
    private int DOWN = 0, LEFT = 1, RIGHT = 2, UP = 3;
    private int width, height;
    public Sprite(float left, float top, float right, float bottom, int dX, int dY, int color) {
        super(left, top, right, bottom);
        this.dX=dX;
        this.dY=dY;
        this.color = color;
    }

    public Sprite(float left, float top, float right, float bottom) {
        this(left, top, right, bottom, 1,2, Color.MAGENTA);
    }

    public Sprite(float left, float top, float right, float bottom, int dX, int dY) {
        this(left,top,right,bottom, dX, dY, Color.MAGENTA);
    }

    public Sprite() {
        this(2, 2, 50, 50,2,3);
    }

    public void update() {
        offset(dX, dY);
        frameX = ++frameX % BM_COLUMNS;
    }

    public void draw(Canvas canvas) {
        if(bitmap == null) {
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawCircle(centerX(), centerY(), width() / 2, paint);
        }
        else {
            width = bitmap.getWidth() / BM_COLUMNS;
            height = bitmap.getHeight() / BM_ROWS;
            int x = frameX * width;
            int y = getAnimRow() * height;
            Rect src = new Rect(x, y, x+width, y+height);
            canvas.drawBitmap(bitmap, src, this, null);
        }
    }

    public int getdX() {
        return dX;
    }

    public int getdY() {
        return dY;
    }

    public int getColor() {
        return color;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setBitmapDim(int row, int col, int srcx) {
        BM_COLUMNS = col;
        BM_ROWS = row;
        frameX = srcx;
    }

    public int getAnimRow() {
        if(Math.abs(dX) > Math.abs(dY)) {
            if(dX == Math.abs(dX))
                return RIGHT;
            else return LEFT;
        }
        else {
            if(dY == Math.abs(dY))
                return DOWN;
            return UP;
        }
    }


}
