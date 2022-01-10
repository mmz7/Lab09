package com.zhangmegan.lab09;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DrawView drawView;
    Button up, down, left, right;
    boolean flag = true;
    boolean collide = false;
    View.OnTouchListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = findViewById(R.id.drawView);
        up = findViewById(R.id.buttonup);
        down = findViewById(R.id.down);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);

        listener = new View.OnTouchListener() {
            private Handler handler;
            @Override public boolean onTouch(View v, MotionEvent motionEvent) {
                if(v.getId() == R.id.buttonup) {
                    if(drawView.sprite.getdY() != 0)
                        drawView.sprite.setdY(-Math.abs(drawView.sprite.getdY()));
                    else {
                        drawView.sprite.setdY(-Math.abs(drawView.sprite.getdX()));
                        drawView.sprite.setdX(0);
                    }
                }
                if(v.getId() == R.id.down) {
                    if(drawView.sprite.getdY() != 0) {
                        drawView.sprite.setdY(Math.abs(drawView.sprite.getdY()));
                    }
                    else {
                        drawView.sprite.setdY(Math.abs(drawView.sprite.getdX()));
                        drawView.sprite.setdX(0);
                    }
                }
                if(v.getId() == R.id.right) {
                    if(drawView.sprite.getdX() != 0)
                        drawView.sprite.setdX(Math.abs(drawView.sprite.getdX()));
                    else {
                        drawView.sprite.setdX(Math.abs(drawView.sprite.getdY()));
                        drawView.sprite.setdY(0);
                    }
                }
                if(v.getId() == R.id.left) {
                    if(drawView.sprite.getdX() != 0)
                        drawView.sprite.setdX(-Math.abs(drawView.sprite.getdX()));
                    else {
                        drawView.sprite.setdX(-Math.abs(drawView.sprite.getdY()));
                        drawView.sprite.setdY(0);
                    }
                }
               if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                   if (handler != null)
                       return true;
                   handler = new Handler();
                   handler.postDelayed(runnable, 100);
               }
               else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                   if(handler == null)
                       return true;
                   handler.removeCallbacks(runnable);
                   handler = null;
               }
               return false;
            }

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if(RectF.intersects(drawView.sprite, drawView.bWall)) {
                        drawView.sprite.offset(0, -drawView.moveNum);
                    }
                    else if(RectF.intersects(drawView.sprite, drawView.tWall))
                        drawView.sprite.offset(0, drawView.moveNum);
                    else if(RectF.intersects(drawView.sprite, drawView.lWall)) {
                        drawView.sprite.offset(drawView.moveNum, 0);
                    }
                    else if(RectF.intersects(drawView.sprite, drawView.rWall))
                        drawView.sprite.offset(-drawView.moveNum, 0);
                    else {
                        drawView.sprite.update();
                    }
                    handler.postDelayed(this, 100);
                }
            };

        };

        up.setOnTouchListener(listener);
        down.setOnTouchListener(listener);
        left.setOnTouchListener(listener);
        right.setOnTouchListener(listener);
    }
}