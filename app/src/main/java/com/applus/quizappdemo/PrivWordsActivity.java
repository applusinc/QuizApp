package com.applus.quizappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PrivWordsActivity extends AppCompatActivity {
    private TextView quesText, countText, levelText, correctText, wrongText, quizInfoText;
    private Button buton1, buton2;
    private LinearLayout quesBack;
    private int originalColor, targetColor;
    private TextView dialogTitleText, dialogText1, dialogText2;
    private Button nextButton, menuButton;
    private ImageView backButton;
    private Boolean sTrue, sFalse, vTrue, vFalse, currentTurn;
    private Vibrator vibrator;
    private TextView undoBtn, saveBtn;
    private int wsayi, csayi;

    private SharedPreferences sharedPreferences;
    private Random random1 = new Random();
    private Random random2 = new Random();
    private Random random3 = new Random();
    private Random random4 = new Random();
    private int ran1, ran2, ran3, ran4;
    private JsonWords jsonWords = new JsonWords();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int currentIndex;
    private int undoIndex;
    private String currentLanguage;
    private Boolean isUndo = false;
    private ImageView deleteText;
    private Boolean isEnIs = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priv_words);
        quesText = findViewById(R.id.questionTextView);
        countText = findViewById(R.id.count);
        levelText = findViewById(R.id.level);
        quizInfoText = findViewById(R.id.quizinfo);
        buton1 = findViewById(R.id.firstButton);
        buton2 = findViewById(R.id.secondButton);
        correctText = findViewById(R.id.correct);
        wrongText = findViewById(R.id.wrong);
        quesBack = findViewById(R.id.quesBack);
        backButton = findViewById(R.id.backbutton);
        undoBtn = findViewById(R.id.undo);
        saveBtn = findViewById(R.id.ayir);
        deleteText = findViewById(R.id.deletebtn);
        wsayi = 0;
        csayi = 0;

        currentLanguage = getIntent().getStringExtra("language");


jsonWords.initJson(getApplicationContext(), currentLanguage);
        undoBtn.setVisibility(View.GONE);

        Drawable standartBack = getResources().getDrawable(R.drawable.rounded_gray_background);
        Drawable successBack = getResources().getDrawable(R.drawable.rounded_success);

        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
try{
    sTrue = sharedPreferences1.getBoolean("sw1", false);
    sFalse = sharedPreferences1.getBoolean("sw2", false);
    vTrue = sharedPreferences1.getBoolean("sw3", false);
    vFalse = sharedPreferences1.getBoolean("sw4", false);
    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
}catch (Exception e){

}
        currentTurn = false;
        undoIndex = 0;


//        quizInfoText.setText(currentLanguage.concat(" Kelime Quizi"));
//        levelText.setText("Level: ".concat(String.valueOf(currentLevel)));

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(getResources().getColor(R.color.buttonRenk));
        gradientDrawable.setCornerRadius(20.0f);
        quesBack.setBackground(gradientDrawable);


setPrivQues(10000);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String json = sharedPreferences1.getString(currentLanguage, null);
                if (json == null){
                    Toast.makeText(PrivWordsActivity.this, "Kelime listeniz zaten boş.", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(currentLanguage);
                    editor.apply();
                    Toast.makeText(PrivWordsActivity.this, "Kelime Listesi Sıfırlandı.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void setPrivQues(int _index){

        saveBtn.setText("Kelimeyi Çıkart");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String json = sharedPreferences.getString(currentLanguage, null);
        System.out.println(json);
        ArrayList<HashMap<String, Object>> privList = new Gson().fromJson(json, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
        jsonWords.randomQues(currentLanguage, 1, 10000);
        if (privList == null) {
            privList = new ArrayList<>();
        }
        if (privList.size() == 0){
            Toast.makeText(this, "Kelime Listeniz Boşaldı", Toast.LENGTH_SHORT).show();
            finish();

        }
        ran3 = random3.nextInt(2);
        try {
            ran1 = random1.nextInt(privList.size());
            System.out.println(privList.size());
        }catch (Exception e){

        }


        ran3 = random3.nextInt(2);
        currentIndex = ran1;

        if(_index == 10000){

        }else{
            ran1 = _index;
            currentIndex = _index;
        }
        if(currentTurn){
if (isEnIs){
    quesText.setText(privList.get(ran1).get("Türkçe").toString());
}else{
    quesText.setText(privList.get(ran1).get(currentLanguage).toString());
}

            if(ran3 == 0){
                buton1.setText(privList.get(ran1).get("Türkçe").toString());
                buton2.setText(jsonWords.getWrongAnswer2("Türkçe"));

            }else{
                buton1.setText(jsonWords.getWrongAnswer2("Türkçe"));
                buton2.setText(privList.get(ran1).get("Türkçe").toString());
            }
            currentTurn = false;
     }else {
            System.out.println(privList.get(ran1).get("Türkçe").toString());
            quesText.setText(privList.get(ran1).get("Türkçe").toString());

            if(ran3 == 0){
                buton1.setText(privList.get(ran1).get(currentLanguage).toString());
                buton2.setText(jsonWords.getWrongAnswer2(privList.get(ran1).get("language").toString()));
            }else{
                buton1.setText(jsonWords.getWrongAnswer2(privList.get(ran1).get("language").toString()));
                buton2.setText(privList.get(ran1).get(currentLanguage).toString());
            }
            currentTurn = true;
        }

        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUndo){
                    isUndo = false;
                }else{
                    undoBtn.setVisibility(View.VISIBLE);
                }


                undoIndex = ran1;


                if(ran3 == 0){
                    if (sTrue) {
                        oynatSesEfekti(R.raw.success);
                    }
                    if(vTrue){
                        long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                        vibrator.vibrate(pattern, -1);
                    }
                    incVoid(true);
                }else{
                    if(sFalse) {
                        oynatSesEfekti(R.raw.wrong);
                    }
                    if(vFalse){


                        // Örnek: 1 saniye boyunca titreşim
                        long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                        vibrator.vibrate(pattern, -1);
                    }
                    incVoid(false);
                }


            }
        });

        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isUndo){
                    isUndo = false;
                }else{
                    undoBtn.setVisibility(View.VISIBLE);
                }
                undoIndex = ran1;
                if(ran3 == 0){
                    if(sFalse) {
                        oynatSesEfekti(R.raw.wrong);
                    }
                    if(vFalse){


                        // Örnek: 1 saniye boyunca titreşim
                        long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                        vibrator.vibrate(pattern, -1);
                    }
                    incVoid(false);
                }else {
                    if (sTrue) {
                        oynatSesEfekti(R.raw.success);
                    }
                    if(vTrue){
                        long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                        vibrator.vibrate(pattern, -1);
                    }
                    incVoid(true);
                }

            }
        });
        final int index = ran1;
        final ArrayList<HashMap<String, Object>> pm = privList;
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    System.out.println(pm.get(index).get("Türkçe").toString());
                    pm.remove(index);
                    editor.putString(currentLanguage, new Gson().toJson(pm));
                    editor.apply();
                    System.out.println(pm);
                    saveBtn.setText("Kelime Listeden Çıkartıldı");
                }catch (Exception e){
                    System.out.println(e.toString());
                }


            }
        });
        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUndo = true;
                undoBtn.setVisibility(View.GONE);
                setPrivQues(undoIndex);
            }
        });

    }
    public void incVoid(Boolean _isCorrect){
        if(_isCorrect){
            showIsCorrect(true);
            csayi = csayi + 1;
            correctText.setText(String.valueOf(csayi));
            setPrivQues(10000);

        }else{
            wsayi = wsayi + 1;
            wrongText.setText(String.valueOf(wsayi));
showIsCorrect(false);
setPrivQues(10000);
        }
    }




    public void showIsCorrect(Boolean isCorrect){
        if(isCorrect){


            originalColor = getResources().getColor(R.color.buttonRenk); // Arka plan rengini al
            targetColor = getResources().getColor(R.color.successBtn);

            animateBackgroundColor(targetColor, originalColor);




        }else {
            originalColor = getResources().getColor(R.color.buttonRenk); // Arka plan rengini al
            targetColor = getResources().getColor(R.color.errorBtn);

            animateBackgroundColor(targetColor, originalColor);
        }
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

    public void animLayout(Boolean isCorrect){
        Drawable standartBack = getResources().getDrawable(R.drawable.rounded_gray_background);
        Drawable successBack = getResources().getDrawable(R.drawable.rounded_success);
        if(isCorrect){
            quesBack.setBackground(successBack);
            animatorLayout((float)0.0f, (float)1.0f);


        }
    }

    public void animatorLayout(Float animSt, Float animEnd){
        ValueAnimator animator = ValueAnimator.ofFloat(animSt, animEnd);
        animator.setDuration(500);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float alpha = (float) valueAnimator.getAnimatedValue();
                quesBack.setAlpha(alpha);


            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorLayout((float)1.0f, (float)0.2f);






            }
        });

        animator.start();
    }



    private void animateBackgroundColor(int startColor, int endColor) {


        GradientDrawable gradientDrawable = new GradientDrawable();
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);
        colorAnimator.setDuration(1000);
        colorAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int color = (int) valueAnimator.getAnimatedValue();

                gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                gradientDrawable.setColor(color);
                gradientDrawable.setCornerRadius(20.0f);




                quesBack.setBackground(gradientDrawable);
            }
        });

        colorAnimator.start();
    }
}