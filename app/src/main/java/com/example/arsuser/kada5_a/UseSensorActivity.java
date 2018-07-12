package com.example.arsuser.kada5_a;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class UseSensorActivity extends AppCompatActivity implements SensorEventListener {


    Thread thread;

    //センサーを使う
    SensorManager manager;
    Sensor s;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_sensor);


        TextView Name = findViewById(R.id.textView_Name);
        TextView Type = findViewById(R.id.textView_Type);

        Intent intent = getIntent();
        Name.setText(intent.getStringExtra("Name"));
        Type.setText(intent.getStringExtra("Type"));

        manager = (SensorManager)getSystemService(SENSOR_SERVICE);

            List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ORIENTATION);
            if (sensors.size() > 0) {
                s = sensors.get(0);
            }

            manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);

            thread = new Thread("");
            thread.start();




    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //センサーの情報が変化したとき
        TextView Param1 = findViewById(R.id.textView_Param1);
        Param1.setText("");
        TextView Param2 = findViewById(R.id.textView_Param2);
        Param2.setText("");
        TextView Param3 = findViewById(R.id.textView_Param3);
        Param3.setText("");

        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            Param1.setText("方位角"+event.values[0]);
            Param2.setText("傾斜角"+event.values[1]);
            Param3.setText("回転角"+event.values[2]);
            //event.values[]配列がセンサーの検知結果が代入されている
            //センサーの種類によって配列の何番目にどんな結果が入っているか異なるため、利用するセンサー毎に調査が必要
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
