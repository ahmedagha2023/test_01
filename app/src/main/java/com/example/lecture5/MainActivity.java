package com.example.lecture5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    TextView textView;
    TextView textAxis;
    TextView a1;
    TextView a2;
    TextView a3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.info_M);
        textAxis = findViewById(R.id.info_axis);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }else{
            Toast.makeText(this, "The sensor is not found", Toast.LENGTH_SHORT).show();
        }
    }

    

    //Ahmad

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

            int x = Math.abs((int) sensorEvent.values[0]);
            int y = Math.abs((int) sensorEvent.values[1]);
            int z = Math.abs((int) sensorEvent.values[2]);

            textView.setText("X: " + x + "\nY: " + y + "\nZ: " + z + "\nMax Value: " + sensorEvent.sensor.getMaximumRange());

            if (x > y && x > z) {
                textAxis.setText("X");
            } else if (y > x && y > z) {
                textAxis.setText("Y");
            } else if (z > x && z > y) {
                textAxis.setText("Z");
            }



////////////////////////////////////////////////////////////////////
            if (x < 100 || y < 100 || z < 100 ){
                a1.setVisibility(View.VISIBLE);
                a2.setVisibility(View.INVISIBLE);
                a3.setVisibility(View.INVISIBLE);

            } else if (x < 500 || y < 500 || z < 500 ) {
                a2.setVisibility(View.VISIBLE);
                a1.setVisibility(View.INVISIBLE);
                a3.setVisibility(View.INVISIBLE);

            } else if (x < 1000 || y < 1000 || z < 1000 ) {
                a3.setVisibility(View.VISIBLE);
                a1.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.INVISIBLE);
            }


        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}