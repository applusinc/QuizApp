package com.applus.quizappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;

public class MenuActivity extends AppCompatActivity {
private ImageView settingsBtn;
private MaterialCardView word, gramer, verb, idiom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        settingsBtn = findViewById(R.id.settings);
        word = findViewById(R.id.cardword);
        gramer = findViewById(R.id.cardgramer);
        verb = findViewById(R.id.cardverbs);
        idiom = findViewById(R.id.cardidioms);
        word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MenuActivity.this, LanguageActivity.class);
                i.putExtra("cat", "word");
                startActivity(i);
            }
        });

        gramer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MenuActivity.this, LanguageActivity.class);
                i.putExtra("cat", "gramers");
                startActivity(i);
            }
        });

        verb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MenuActivity.this, LanguageActivity.class);
                i.putExtra("cat", "verbs");
                startActivity(i);
            }
        });



        idiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MenuActivity.this, LanguageActivity.class);
                i.putExtra("cat", "idioms");
                startActivity(i);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MenuActivity.this, SettingsActivity.class);

                startActivity(i);
            }
        });
    }
}