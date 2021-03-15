package com.devgd.chumma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private   SensorManager mSensormanager;
    private Sensor mSensorLight;
    private LinearLayout Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensormanager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorLight=mSensormanager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Layout=findViewById(R.id.cd);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mSensorLight!=null){
            mSensormanager.registerListener(this,mSensorLight,mSensormanager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensormanager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int grayShade = (int) event.values[0];
        if (grayShade > 255) grayShade = 255;
        Layout.setBackgroundColor(Color.rgb(grayShade, grayShade, grayShade));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}