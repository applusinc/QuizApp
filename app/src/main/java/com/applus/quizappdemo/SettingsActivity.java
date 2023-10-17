package com.applus.quizappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.material.materialswitch.MaterialSwitch;

public class SettingsActivity extends AppCompatActivity {
private ImageView backBtn;
private MaterialSwitch sw1, sw2, sw3, sw4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        backBtn = findViewById(R.id.backbutton);
        sw1 = findViewById(R.id.sw1);
        sw2 = findViewById(R.id.sw2);
        sw3 = findViewById(R.id.sw3);
        sw4 = findViewById(R.id.sw4);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(sharedPreferences.getBoolean("sw1", false)){

            sw1.setChecked(true);

        }else{

            sw1.setChecked(false);

        }

        if(sharedPreferences.getBoolean("sw2", false)){

            sw2.setChecked(true);

        }else{

            sw2.setChecked(false);

        }

        if(sharedPreferences.getBoolean("sw3", false)){

            sw3.setChecked(true);

        }else{

            sw3.setChecked(false);

        }

        if(sharedPreferences.getBoolean("sw4", false)){

            sw4.setChecked(true);

        }else{

            sw4.setChecked(false);

        }


        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw1.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("sw1", true);
                    editor.commit();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("sw1", false);
                    editor.commit();
                }
            }
        });

        sw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw2.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("sw2", true);
                    editor.commit();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("sw2", false);
                    editor.commit();
                }
            }
        });


        sw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw3.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("sw3", true);
                    editor.commit();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("sw3", false);
                    editor.commit();
                }
            }
        });

        sw4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw4.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("sw4", true);
                    editor.commit();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("sw4", false);
                    editor.commit();
                }
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });








    }
}