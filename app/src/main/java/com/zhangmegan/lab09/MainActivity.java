package com.zhangmegan.lab09;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
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

//        up.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Sprite s = drawView.sprite;
//                s.setdY(-10);
//                s.setdX(0);
//                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    flag = true;
//                }
//                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    flag = false;
//                }
//                if(flag) {
//                    s.update();
//                }
//                return false;
//            }
//        });
//        listener = new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Sprite s = drawView.sprite;
//                if(view.getId() == R.id.buttonup) {
//                    if(s.getdY() != 0)
//                        s.setdY(-Math.abs(s.getdY()));
//                    else {
//                        s.setdY(-Math.abs(s.getdX()));
//                        s.setdX(0);
//                    }
//                }
//                if(view.getId() == R.id.down) {
//                    if(s.getdY() != 0) {
//                        s.setdY(Math.abs(s.getdY()));
//                    }
//                    else {
//                        s.setdY(Math.abs(s.getdX()));
//                        s.setdX(0);
//                    }
//                }
//                if(view.getId() == R.id.right) {
//                    if(s.getdX() != 0)
//                        s.setdX(Math.abs(s.getdX()));
//                    else {
//                        s.setdX(Math.abs(s.getdY()));
//                        s.setdY(0);
//                    }
//                }
//                if(view.getId() == R.id.left) {
//                    if(s.getdX() != 0)
//                        s.setdX(-Math.abs(s.getdX()));
//                    else {
//                        s.setdX(-Math.abs(s.getdY()));
//                        s.setdY(0);
//                    }
//                }
//                if(!s.intersect(drawView.lWall) && !s.intersect(drawView.rWall) &&
//                    !s.intersect(drawView.tWall) && !s.intersect(drawView.bWall)) {
//                    s.update();
//                }
//                else
//                    collide = true;
//                if(collide) {
//                    s.offset(-s.getdX(), -s.getdY());
//                    collide = false;
//                }
//            }
//        };

        listener = new View.OnTouchListener() {
            Sprite s = drawView.sprite;
            private Handler handler;
            @Override public boolean onTouch(View v, MotionEvent motionEvent) {
                s.setdY(-10);
                s.setdX(0);
                if(v.getId() == R.id.buttonup) {
                    if(s.getdY() != 0)
                        s.setdY(-Math.abs(s.getdY()));
                    else {
                        s.setdY(-Math.abs(s.getdX()));
                        s.setdX(0);
                    }
                }
                if(v.getId() == R.id.down) {
                    if(s.getdY() != 0) {
                        s.setdY(Math.abs(s.getdY()));
                    }
                    else {
                        s.setdY(Math.abs(s.getdX()));
                        s.setdX(0);
                    }
                }
                if(v.getId() == R.id.right) {
                    if(s.getdX() != 0)
                        s.setdX(Math.abs(s.getdX()));
                    else {
                        s.setdX(Math.abs(s.getdY()));
                        s.setdY(0);
                    }
                }
                if(v.getId() == R.id.left) {
                    if(s.getdX() != 0)
                        s.setdX(-Math.abs(s.getdX()));
                    else {
                        s.setdX(-Math.abs(s.getdY()));
                        s.setdY(0);
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
                    drawView.sprite.update();
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