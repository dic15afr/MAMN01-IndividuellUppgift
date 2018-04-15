package com.fridh.albin.individuelluppgift;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


//https://www.wlsdevelop.com/index.php/en/enblog?option=com_content&view=article&id=38
public class Compass extends AppCompatActivity implements SensorEventListener {
    private ImageView compassImage;
    private TextView compassText, direction;
    private float[] rMat = new float[9];
    private float[] orientation = new float[3];
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private boolean isVibrating = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        compassImage = findViewById(R.id.image);
        compassText = findViewById(R.id.text);
        direction = findViewById(R.id.direction);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onSensorChanged(SensorEvent event) {
        int azimuth;

        SensorManager.getRotationMatrixFromVector(rMat, event.values);
        azimuth = (int) (Math.toDegrees(SensorManager.getOrientation(rMat, orientation)[0]) + 360) % 360;

        azimuth = Math.round(azimuth);
        compassImage.setRotation(-azimuth);

        String where = "";

        if (azimuth >= 350 || azimuth <= 10)
            where = "NORR";
        if (azimuth < 350 && azimuth > 280)
            where = "NORDVÄST";
        if (azimuth <= 280 && azimuth > 260)
            where = "VÄSTER";
        if (azimuth <= 260 && azimuth > 190)
            where = "SYDVÄST";
        if (azimuth <= 190 && azimuth > 170)
            where = "SÖDER";
        if (azimuth <= 170 && azimuth > 100)
            where = "SYDOST";
        if (azimuth <= 100 && azimuth > 80)
            where = "ÖSTER";
        if (azimuth <= 80 && azimuth > 10)
            where = "NORDOST";

        String temp = azimuth + "°";
        compassText.setText(temp);
        direction.setText(where);

        // https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
        if ((azimuth >= 345 || azimuth <= 15)) {
            if (!isVibrating) {
                long[] pattern = {0, 100, 200};
                vibrator.vibrate(pattern, 0);
                isVibrating = true;
            }
        } else if (isVibrating) {
            vibrator.cancel();
            isVibrating = false;
        }
    }

    //https://developer.android.com/reference/android/hardware/SensorManager.html
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        vibrator.cancel();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
