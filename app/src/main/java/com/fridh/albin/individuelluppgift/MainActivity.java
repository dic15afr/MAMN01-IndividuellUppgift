package com.fridh.albin.individuelluppgift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //https://developer.android.com/training/basics/firstapp/starting-activity.html#java
    public void compass(View v){
        startActivity(new Intent(this, Compass.class));
    }

    public void accelerometer(View v){
        startActivity(new Intent(this, Accelerometer.class));
    }
}
