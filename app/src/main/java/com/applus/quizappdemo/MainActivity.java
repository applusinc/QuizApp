package com.applus.quizappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private TimerTask time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = new TimerTask() {
            @Override
            public void run(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent();
                        i.setClass(MainActivity.this, MenuActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }

        };
        _timer.schedule(time, (int)(3500));


    }
}