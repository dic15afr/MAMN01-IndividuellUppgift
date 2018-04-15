package com.fridh.albin.individuelluppgift;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

//https://developer.android.com/reference/android/hardware/SensorEvent.html
public class Accelerometer extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private TextView xText, yText, zText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        xText = findViewById(R.id.X);
        yText = findViewById(R.id.Y);
        zText = findViewById(R.id.Z);

    }

    public void onSensorChanged(SensorEvent event){
        double x, y, z;

        x = Math.round(event.values[0]*10)/10.0;
        y = Math.round(event.values[1]*10)/10.0;
        z = Math.round(event.values[2]*10)/10.0;

        if(x > 5 || x < -5){
            xText.setTextColor(Color.RED);
        }else{
            xText.setTextColor(Color.BLACK);
        }

        if(y > 5 || y < -5){
            yText.setTextColor(Color.RED);
        }else{
            yText.setTextColor(Color.BLACK);
        }

        if(z > 5 || z < -5){
            zText.setTextColor(Color.RED);
        }else{
            zText.setTextColor(Color.BLACK);
        }

        xText.setText(String.valueOf(x));
        yText.setText(String.valueOf(y));
        zText.setText(String.valueOf(z));
    }

    //https://developer.android.com/reference/android/hardware/SensorManager.html
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}