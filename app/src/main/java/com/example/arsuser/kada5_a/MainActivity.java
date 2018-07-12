package com.example.arsuser.kada5_a;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener  {

    private SensorManager sensorManager;
    private List<Sensor> sensorList;
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        final ListView list = findViewById(R.id.List1);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);

        for (int i=0; i<sensorList.size(); i++){
            Sensor s = sensorList.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(s.getStringType()).append(": ").append(s.getName());
            adapter.add(sb.toString());
            sensorManager.registerListener(this,s, SensorManager.SENSOR_DELAY_NORMAL);
        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,UseSensorActivity.class);
                Sensor s = sensorList.get(i);//Name
                intent.putExtra("Name", (s.getName()));
                intent.putExtra("Type",s.getStringType());
                intent.putExtra("param1",s.toString());
                startActivity(intent);
            }
        });









    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
