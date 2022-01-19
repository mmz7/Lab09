package com.zhangmegan.lab09;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class GameOverActivity extends Activity implements SensorEventListener {
    Button replay;
    boolean stepswalked = false;
    SensorManager sensorManager;
    Sensor detector;
    int stepcount;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gameoverlayout);

        textView = findViewById(R.id.steps);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

       // if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null)
        detector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

//        else {
//            textView.setText("Step Detector not found");
//        }

        replay = findViewById(R.id.button);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stepswalked) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null)
            sensorManager.registerListener(this, detector, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null)
            sensorManager.unregisterListener(this, detector);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        stepcount = (int) sensorEvent.values[0];
        textView.setText("Steps: "+stepcount);
        if(stepcount >= 15) {
            stepswalked = true;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
