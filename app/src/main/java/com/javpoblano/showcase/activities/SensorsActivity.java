package com.javpoblano.showcase.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.javpoblano.showcase.R;

public class SensorsActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor acelerometro;
    Sensor proximidad;
    Sensor giroscopio;
    long now=0;
    long timeDiff=0;
    long lastUpdate=0;
    float threshold=0.5f;
    float x=0,y=0,z=0;
    float lastX=0,lastY=0,lastZ=0;
    float force=0;
    boolean moving=false;
    int interval = 20;
    int contUpdates = 0;
    MediaPlayer alarmPlayer;
    Button semaforo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        semaforo = (Button)findViewById(R.id.bt_init);
        alarmPlayer = MediaPlayer.create(this,R.raw.alarm);
        alarmPlayer.setVolume(1.0f,1.0f);
        alarmPlayer.setLooping(true);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        {
            acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            //sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_GAME);
            Log.d("ACCEL","MAX:"+acelerometro.getMaximumRange());
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!=null)
        {
            proximidad = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager.registerListener(this,proximidad,SensorManager.SENSOR_DELAY_GAME);
            Log.d("state","init");
        }
        else
        {

        }
        semaforo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alarmPlayer.isPlaying())
                {
                    alarmPlayer.stop();
                }
            }
        });
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            motionDetect(sensorEvent);
        }
        if(sensor.getType()==Sensor.TYPE_LIGHT)
        {
            Log.d("STATE","esta vivo");
            if(sensorEvent.values[0]<proximidad.getMaximumRange())
            {
                Log.d("Estado","No se ve");
            }
            else
            {
                Log.d("Estado","Se ve");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void motionDetect(SensorEvent event)
    {
        now = System.currentTimeMillis();
        x=event.values[0];
        y=event.values[1];
        z=event.values[2];

        timeDiff = now - lastUpdate;

        if(timeDiff>interval)
        {
            force = Math.abs(x+y+z-lastX-lastY-lastZ);

            if(Float.compare(force,threshold)>0)
            {
                contUpdates = 0;

                if(!moving)
                {
                    Log.d("ACCEL","isMoving");
                    alarmPlayer.start();
                    moving=true;
                }
            }
            else
            {
                //Log.d("ACCEL","notMoving");

            }
        }
        else
        {
            contUpdates++;
            if(contUpdates>350)
            {
                if(alarmPlayer.isPlaying())
                {
                    alarmPlayer.stop();
                }
                moving=false;
                Log.d("ACCEL","stopedMoving");

            }

        }

        lastUpdate = now;
        lastX=x;
        lastY=y;
        lastZ=z;
    }
}
