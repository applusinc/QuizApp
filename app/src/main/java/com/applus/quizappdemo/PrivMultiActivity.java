package com.applus.quizappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class PrivMultiActivity extends AppCompatActivity {
private String currentKey;
    private ImageView backBtn;
    private LinearLayout quesBack;
    private TextView quesText;
    private Button btn1, btn2;
    private ArrayList<HashMap<String, Object>> privList = new ArrayList<>();
    private TextView quizInfo, pageTitle;
    private ImageView savebtn;
    private JsonWords jsonWords = new JsonWords();
    private int sayac = 0;
    private Boolean isInit = true;
    private int currentStep;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priv_multi);
        currentKey = getIntent().getStringExtra("key");
        jsonWords.initJson(getApplicationContext(), currentKey);
        backBtn = findViewById(R.id.backbutton);
        quesBack = findViewById(R.id.quesBack);
        quesText = findViewById(R.id.questionTextView);
        btn1 = findViewById(R.id.firstButton);
        quizInfo = findViewById(R.id.quizinfo);
        pageTitle = findViewById(R.id.level);
        btn2 = findViewById(R.id.secondbutton);
        savebtn = findViewById(R.id.savebtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setQues(10000);
    }
        public void setQues(int _currentIndex){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String json = sharedPreferences.getString(currentKey, null);
             privList = new Gson().fromJson(json, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
            if (privList == null) {
                privList = new ArrayList<>();
            }
            if (privList.size() == 0){
                Toast.makeText(this, "Kelime Listeniz Boşaldı", Toast.LENGTH_SHORT).show();
                finish();

            }

            if(_currentIndex == 10000){
                if (isInit){
                    isInit = false;
                }else{
                    if ((sayac + 1) == privList.size()){
                        sayac = 0;
                    }else {
                        sayac++;
                    }

                }
            }else {
                sayac--;
            }

            quesText.setText(privList.get(sayac).get("Türkçe").toString());
            currentStep = 0;
            btn2.setText("Çevir");
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentStep == 0){
                        setQuesTextFunc(currentKey);

                    }else {
                        currentStep = 0;
                        btn2.setText("Çevir");
                        setQues(10000);
                    }

                }
            });
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
if (!(sayac == 0)){
    setQues(1);
}else {
    Toast.makeText(PrivMultiActivity.this, "Geri Sarılamıyor", Toast.LENGTH_SHORT).show();
}

                }
            });
            savebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveWord();
                }
            });
        }
        private void setQuesTextFunc(String _key){
        switch (_key){
            case "all":
                quesText.setText("Türkçe: ".concat(privList.get(sayac).get("Türkçe").toString()).concat("\nİngilizce: ").concat(privList.get(sayac).get("İngilizce").toString()).concat("\nİspanyolca: ").concat(privList.get(sayac).get("İspanyolca").toString()).concat("\nİtalyanca: ").concat(privList.get(sayac).get("İtalyanca").toString()).concat("\nFransızca: ").concat(privList.get(sayac).get("Fransızca").toString()).concat("\nAlmanca: ").concat(privList.get(sayac).get("Almanca").toString()).concat("\nLatince: ").concat(privList.get(sayac).get("Latince").toString()).concat("\nRusça: ").concat(privList.get(sayac).get("Rusça").toString()).concat("\nJaponca: ").concat(privList.get(sayac).get("Japonca").toString()).concat("\nÇince: ").concat(privList.get(sayac).get("Çince").toString()).concat("\nNorveççe: ").concat(privList.get(sayac).get("Norveççe").toString()).concat("\nFlemenkçe: ").concat(privList.get(sayac).get("Flemenkçe").toString()).concat("\nİbranice: ").concat(privList.get(sayac).get("İbranice").toString()).concat("\nFarsça").concat(privList.get(sayac).get("Farsça").toString()));
                btn2.setText("İlerle");
                currentStep = 1;
                break;
            case "en_is":
                quesText.setText("Türkçe: ".concat(privList.get(sayac).get("Türkçe").toString()).concat("\nİngilizce: ").concat(privList.get(sayac).get("İngilizce").toString()).concat("\nİspanyolca: ").concat(privList.get(sayac).get("İspanyolca").toString()));
                btn2.setText("İlerle");
                currentStep = 1;
                break;
        }
        }
        private void saveWord(){
            SharedPreferences sharedPreferencess = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            privList = new Gson().fromJson(sharedPreferencess.getString(currentKey, null), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

            if (privList == null) {
                privList = new ArrayList<>();
            }



                privList.remove(sayac);

                SharedPreferences.Editor editor = sharedPreferencess.edit();
                editor.putString(currentKey, new Gson().toJson(privList));
                editor.apply(); // commit yerine apply kullanmanız daha iyi olabilir
            Toast.makeText(this, "Çıkartıldı", Toast.LENGTH_SHORT).show();


        }
    }
