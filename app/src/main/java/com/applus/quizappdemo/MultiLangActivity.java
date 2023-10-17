package com.applus.quizappdemo;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class MultiLangActivity extends AppCompatActivity {
    private ImageView backBtn;
    private LinearLayout quesBack;
    private TextView quesText;
    private Button btn1, btn2;
    private ArrayList<HashMap<String, Object>> privList = new ArrayList<>();
    private TextView quizInfo, pageTitle;
    private String currentLevel;
    private int currentIndex;
    private Boolean sTrue, sFalse, vTrue, vFalse;
    private JsonWords jsonWords = new JsonWords();
    private Vibrator vibrator;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int undoIndex;
    private Boolean isUndo = false;
    private Boolean isPriv;
    private ImageView savebtn;
    private String currentKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_lang);
        backBtn = findViewById(R.id.backbutton);
        quesBack = findViewById(R.id.quesBack);
        quesText = findViewById(R.id.questionTextView);
        btn1 = findViewById(R.id.firstButton);
        quizInfo = findViewById(R.id.quizinfo);
        pageTitle = findViewById(R.id.level);
        btn2 = findViewById(R.id.secondbutton);
        savebtn = findViewById(R.id.savebtn);




        Drawable standartBack = getResources().getDrawable(R.drawable.rounded_gray_background);
        Drawable successBack = getResources().getDrawable(R.drawable.rounded_success);
        currentLevel = getIntent().getStringExtra("level");
        isPriv = Boolean.valueOf(getIntent().getStringExtra("ispriv"));

        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sTrue = sharedPreferences1.getBoolean("sw1", false);
        sFalse = sharedPreferences1.getBoolean("sw2", false);
        vTrue = sharedPreferences1.getBoolean("sw3", false);
        vFalse = sharedPreferences1.getBoolean("sw4", false);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(getResources().getColor(R.color.buttonRenk));
        gradientDrawable.setCornerRadius(20.0f);


        jsonWords.initJson(getApplicationContext(), null);
        btn1.setVisibility(View.GONE);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (!isPriv){
            currentKey = "all";
        } else {
            currentKey = "en_is";
        }
        setQues(10000);
    }
    public void setQues(int _currentIndex){
        if(_currentIndex == 10000){
            jsonWords.randomQues("Türkçe", Integer.parseInt(currentLevel), 10000);
        }else {
            jsonWords.randomQues("Türkçe", Integer.parseInt(currentLevel), _currentIndex);
        }

        quesText.setText(jsonWords._tr);
        currentIndex = 0;
        btn2.setText("Çevir");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex == 0){
                    if (isPriv){
quesText.setText("Türkçe: ".concat(jsonWords._tr).concat("\nİngilizce: ").concat(jsonWords._en).concat("\nİspanyolca: ").concat(jsonWords._is));
                    }else{
                    quesText.setText("Türkçe: ".concat(jsonWords._tr).concat("\nİngilizce: ").concat(jsonWords._en).concat("\nİspanyolca: ").concat(jsonWords._is).concat("\nİtalyanca: ").concat(jsonWords._it).concat("\nFransızca: ").concat(jsonWords._fr).concat("\nAlmanca: ").concat(jsonWords._al).concat("\nLatince: ").concat(jsonWords._la).concat("\nRusça: ").concat(jsonWords._ru).concat("\nJaponca: ").concat(jsonWords._ja).concat("\nÇince: ").concat(jsonWords._ch).concat("\nNorveççe: ").concat(jsonWords._no).concat("\nFlemenkçe: ").concat(jsonWords._fl).concat("\nİbranice: ").concat(jsonWords._ib).concat("\nFarsça").concat(jsonWords._fa));
                    }
                    btn2.setText("İlerle");
                    currentIndex = 1;
                }else{
                    currentIndex = 0;
                    undoIndex = jsonWords.pos;
                    btn2.setText("Çevir");
                    if (sTrue) {
                        oynatSesEfekti(R.raw.success);
                    }
                    if(vTrue){
                        long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                        vibrator.vibrate(pattern, -1);
                    }
                    if(isUndo){
                        isUndo = false;
                    }else{
                        btn1.setVisibility(View.VISIBLE);
                    }
                    setQues(10000);

                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setVisibility(View.VISIBLE);
                isUndo = true;
                setQues(undoIndex);
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
saveWord(jsonWords);
            }
        });

    }


    private void oynatSesEfekti(int sesKaynagi) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, sesKaynagi);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float volume = (float) currentVolume / maxVolume;
            mediaPlayer.setVolume(volume, volume);
        }

        mediaPlayer.start();
    }
    public void saveWord(JsonWords _json){
        try {


            SharedPreferences sharedPreferencess = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            privList = new Gson().fromJson(sharedPreferencess.getString(currentKey, null), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

            if (privList == null) {
                privList = new ArrayList<>();
            }


            boolean isAlreadyAdded = false;
//            for (HashMap<String, Object> item : privList) {
//                if ( item.containsKey("Türkçe")) {
//                    Object ydlanValue = item.get(currentLanguage);
//                    Object trlanValue = item.get("Türkçe");
//
//                    // Eğer aynı veriler zaten ekli ise, eklemeyi engelle
//                    if (_ydLan.equals(ydlanValue) && _trLan.equals(trlanValue)) {
//                        isAlreadyAdded = true ;
//                        break;
//                    }
//                }
//            }

            if (!isAlreadyAdded) {
                HashMap<String, Object> _item = new HashMap<>();
                if (isPriv) {
                    _item.put("İspanyolca", _json._is);
                    _item.put("İngilizce", _json._en);
                    _item.put("Türkçe", _json._tr);
                }else {
                    _item.put("İspanyolca", _json._is);
                    _item.put("İngilizce", _json._en);
                    _item.put("Türkçe", _json._tr);
                    _item.put("İtalyanca", _json._tr);
                    _item.put("Fransızca", _json._fr);
                    _item.put("Almanca", _json._al);
                    _item.put("Latince", _json._la);
                    _item.put("Rusça", _json._ru);
                    _item.put("Japonca", _json._ja);
                    _item.put("Çince", _json._ch);
                    _item.put("Norveççe", _json._no);
                    _item.put("Flemenkçe", _json._fl);
                    _item.put("İbranice", _json._ib);
                    _item.put("Farsça", _json._fa);
                }
                System.out.println(_item);

                privList.add(_item);

                SharedPreferences.Editor editor = sharedPreferencess.edit();
                editor.putString(currentKey, new Gson().toJson(privList));
                editor.apply(); // commit yerine apply kullanmanız daha iyi olabilir
                tost("Kelime Eklendi");

            } else {
                long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                vibrator.vibrate(pattern, -1);
                tost("Bu kelime zaten ekli!");
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }



    }
    private void tost(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}