package com.zhangmegan.lab09;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DrawView drawView;
    Button up, down, left, right;
    View.OnClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = findViewById(R.id.drawView);
        up = findViewById(R.id.buttonup);
        down = findViewById(R.id.down);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sprite s = drawView.sprite;
                if(s.getdY() != 0)
                    s.setdY(-Math.abs(s.getdY()));
                else {
                    s.setdY(-Math.abs(s.getdX()));
                    s.setdX(0);
                }
                s.update();
            }
        });

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Sprite s = drawView.sprite;
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    // if(s.getdY() != 0)
                    s.setdY(-10);

                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    s.setdY(0);
                    s.setdX(0);
                }
                s.update();
                return false;
            }
        });
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
//                s.update();
//            }
//        };
//
//        up.setOnClickListener(listener);
//        down.setOnClickListener(listener);
//        left.setOnClickListener(listener);
//        right.setOnClickListener(listener);
    }
}