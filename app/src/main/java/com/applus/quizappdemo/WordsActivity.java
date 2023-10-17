package com.applus.quizappdemo;

import android.content.Context;
import   androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.applus.quizappdemo.JsonWords;
import com.google.gson.Gson;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ArgbEvaluator;
import android.graphics.drawable.ColorDrawable;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import android.os.Vibrator;
import com.google.gson.reflect.TypeToken;

public class WordsActivity extends AppCompatActivity {
    private TextView quesText, countText, levelText, correctText, wrongText, quizInfoText;
    private Button buton1, buton2;
    private String currentLanguage, currentLevel;
    private int currentCount, correctNum, wrongNum;
    private LinearLayout quesBack;
    private int originalColor, currentIndex;
    private int targetColor;
    private TextView dialogTitleText, dialogText1, dialogText2;
    private Button nextButton, menuButton;
    private AlertDialog dialog;
    private ImageView backButton;
    private Boolean isInit = false;
    private List<Integer> randomMap;
    private MediaPlayer mediaPlayer;
    private Boolean sTrue, sFalse, vTrue, vFalse;
    private Vibrator vibrator;
    private JsonWords jsonWords = new JsonWords();
    private TextView undoBtn, saveBtn;
    private int undoIndex, listsz;
    private Boolean isUndo = false;
    private String ydla, trla;
    private ArrayList<HashMap<String, Object>> privList = new ArrayList<>();
    private int currentNum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        //xml tanımlamaları
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

        //Değişken tanımlamaları
        undoIndex = 0;
        undoBtn.setVisibility(View.GONE);
        currentLanguage = getIntent().getStringExtra("lan");
        currentLevel = getIntent().getStringExtra("level");
        currentIndex = 0;
        Drawable standartBack = getResources().getDrawable(R.drawable.rounded_gray_background);
        Drawable successBack = getResources().getDrawable(R.drawable.rounded_success);

        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sTrue = sharedPreferences1.getBoolean("sw1", false);
        sFalse = sharedPreferences1.getBoolean("sw2", false);
        vTrue = sharedPreferences1.getBoolean("sw3", false);
        vFalse = sharedPreferences1.getBoolean("sw4", false);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        jsonWords.initJson(getApplicationContext(), currentLanguage);



        currentCount = 1;
        correctNum = 0;
        wrongNum = 0;
        //Quiz bilgisi
        quizInfoText.setText(currentLanguage.concat(" Kelime Quizi"));
        levelText.setText("Level: ".concat(String.valueOf(currentLevel)));

        //Başlatılıyor



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(getResources().getColor(R.color.buttonRenk));
        gradientDrawable.setCornerRadius(20.0f);

        //jsonWords.listmap.get(randomMap.get(currentIndex)).get(currentLanguage).toString())
        //jsonWords.listmap.get(randomMap.get(currentIndex)).get("Türkçe").toString()
//        jsonWords.listmap.get(wrng).get("Türkçe").toString()
        setQuestion(10000);






        quesBack.setBackground(gradientDrawable);


    }

    public int getLevelInfo(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int shLevel = sharedPreferences.getInt(currentLanguage, -1);
        if(shLevel == -1){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int levelToSave = 1;
            editor.putInt(currentLanguage, levelToSave);
            editor.commit();
            return levelToSave;
        }else {
            return shLevel;
        }

    }
    public void incLevel(){
        if(true){
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        int shLevel = sharedPreferences.getInt(currentLanguage, -1);
//        if(shLevel == -1){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            int levelToSave = 1;
//            editor.putInt(currentLanguage, levelToSave);
//            editor.commit();
//            levelText.setText("Level: ".concat(String.valueOf(Integer.parseInt(currentLevel))));
//            startDialog(Integer.parseInt(currentLevel) + 1, correctNum, wrongNum);

        }else {
//            int levelToSave = shLevel + 1;
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt(currentLanguage, levelToSave);
//            editor.commit();
//            levelText.setText("Level: ".concat(String.valueOf(Integer.parseInt(currentLevel))));
//
//            startDialog(Integer.parseInt(currentLevel) + 1, correctNum, wrongNum);
        }
    }








    public void incCount(Boolean isCorrect){
        if(isCorrect){
            correctNum = correctNum + 1;
            showIsCorrect(true);

        }else {
            wrongNum = wrongNum + 1;
            showIsCorrect(false);
        }
        correctText.setText(String.valueOf(correctNum));
        wrongText.setText(String.valueOf(wrongNum));

        if(currentCount == 20){

//            countText.setText("Sorular: ".concat(String.valueOf(currentCount)).concat("/20"));

//            incLevel();
            setQuestion(10000);

        } else {
            currentCount = currentCount + 1;
//            countText.setText("Sorular: ".concat(String.valueOf(currentCount)).concat("/20"));
setQuestion(10000);



        }
        if ((correctNum + wrongNum) > 159){
            correctNum = 0;
            wrongNum = 0;
            correctText.setText(String.valueOf(correctNum));
            wrongText.setText(String.valueOf(wrongNum));
        }
    }
    public void setQuestion(int _pos){
        saveBtn.setText("Kelimeyi Ayır");


        undoIndex = jsonWords.pos;

        jsonWords.randomQues(currentLanguage, Integer.parseInt(currentLevel), _pos);
        trla = jsonWords._tr;
        ydla = jsonWords._yd;
        quesText.setText(jsonWords.question);


        if(jsonWords.yon == 0){
            buton1.setText(jsonWords.answer);
            buton2.setText(jsonWords.wrongAnswer);
        }else {
            buton2.setText(jsonWords.answer);
            buton1.setText(jsonWords.wrongAnswer);
        }
        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isUndo){
                    isUndo = false;
                }else{
                    undoBtn.setVisibility(View.VISIBLE);
                }
                if(jsonWords.yon == 0){


                    if (sTrue) {
                        oynatSesEfekti(R.raw.success);


                    }
                    if(vTrue){


                        // Örnek: 1 saniye boyunca titreşim
                        long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                        vibrator.vibrate(pattern, -1);
                    }
                    incCount(true);

                }else{
                    if(sFalse) {
                        oynatSesEfekti(R.raw.wrong);
                    }
                    if(vFalse){


                        // Örnek: 1 saniye boyunca titreşim
                        long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                        vibrator.vibrate(pattern, -1);
                    }
                    incCount(false);
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
                if(jsonWords.yon == 0){


                    if(sFalse) {
                        oynatSesEfekti(R.raw.wrong);
                    }
                    if(vFalse){


                        // Örnek: 1 saniye boyunca titreşim
                        long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                        vibrator.vibrate(pattern, -1);
                    }
                    incCount(false);
                }else{


                    if (sTrue) {
                        oynatSesEfekti(R.raw.success);

                    }
                    if(vTrue){


                        // Örnek: 1 saniye boyunca titreşim
                        long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                        vibrator.vibrate(pattern, -1);
                    }
                    incCount(true);
                }

            }
        });


        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuestion(undoIndex);
                isUndo = true;
                undoBtn.setVisibility(View.GONE);
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveWord(ydla, trla);

            }
        });







    }

    public void saveWord(String _ydLan, String _trLan){
        try {
            SharedPreferences sharedPreferencess = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            privList = new Gson().fromJson(sharedPreferencess.getString(currentLanguage, null), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

            if (privList == null) {
                privList = new ArrayList<>();
            }

// _ydLan ve _trLan değişkenlerini içeren bir HashMap aranıyor
            boolean isAlreadyAdded = false;
            for (HashMap<String, Object> item : privList) {
                if (item.containsKey(currentLanguage) || item.containsKey("Türkçe")) {
                    Object ydlanValue = item.get(currentLanguage);
                    Object trlanValue = item.get("Türkçe");

                    // Eğer aynı veriler zaten ekli ise, eklemeyi engelle
                    if (_ydLan.equals(ydlanValue) && _trLan.equals(trlanValue)) {
                        isAlreadyAdded = true ;
                        break;
                    }
                }
            }

            if (!isAlreadyAdded) {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put(currentLanguage, _ydLan);
                _item.put("Türkçe", _trLan);
                _item.put("language", currentLanguage);

                privList.add(_item);

                SharedPreferences.Editor editor = sharedPreferencess.edit();
                editor.putString(currentLanguage, new Gson().toJson(privList));
                editor.apply(); // commit yerine apply kullanmanız daha iyi olabilir
                saveBtn.setText("Kelime Eklendi");
            } else {
                long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                vibrator.vibrate(pattern, -1);
                saveBtn.setText("Bu kelime zaten ekli!");
            }
        }catch (Exception e){
            System.out.println(e.toString());
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
    public void startDialog(int _level, int _correct, int _wrong){
        int currentlevel = _level - 1;

        int dogruCevapSayisi = _correct;
        int yanlisCevapSayisi = _wrong;
        int toplamSoruSayisi = 20;

        double basariYuzdesi = ((double)dogruCevapSayisi / toplamSoruSayisi) * 100;


        // Yüzdeyi x.xx formatında biçimlendirmek için DecimalFormat kullanın



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View customDialogView = inflater.inflate(R.layout.wordlevelup, null);
        dialogTitleText = customDialogView.findViewById(R.id.dialogTitle);
        dialogText1 = customDialogView.findViewById(R.id.dialogText1);

        nextButton = customDialogView.findViewById(R.id.btnLevel2);

        dialogTitleText.setText(String.valueOf(currentlevel).concat(". Level Tamamlandı!"));
        dialogText1.setText("Başarı Yüzdesi: ".concat(String.valueOf(basariYuzdesi)));

        nextButton.setText("Diğer Leveller");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        builder.setView(customDialogView);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
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

}