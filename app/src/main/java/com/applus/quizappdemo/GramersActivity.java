package com.applus.quizappdemo;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GramersActivity extends AppCompatActivity {
    private TextView quesText, quizInfoText, quizInfoText2, title;
    private LinearLayout quesBack;
    private Button buton1, undoBtn;
    private ImageView backButton;
    private int originalColor, targetColor, step;
    private String currentLanguage, currentType;
    private EditText edit;
    private Boolean sTrue, sFalse, vTrue, vFalse;
    private Vibrator vibrator;
    private MediaPlayer mediaPlayer;
    private Boolean currentTurn;
    JsonGramers jsonGramers = new JsonGramers();
    private Boolean isUndo = false;
    private int undoIndex;
    private ImageView pas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gramers);
        quesText = findViewById(R.id.questionTextView);
        quizInfoText = findViewById(R.id.quizinfo);
        quizInfoText2 = findViewById(R.id.questionTextView2);
        buton1 = findViewById(R.id.firstButton);
        undoBtn = findViewById(R.id.undobtn);
        edit = findViewById(R.id.edittext);
        title = findViewById(R.id.level);
        pas = findViewById(R.id.next);
        currentTurn = false;

        quesBack = findViewById(R.id.quesBack);
        backButton = findViewById(R.id.backbutton);
        currentLanguage = getIntent().getStringExtra("lan");
        currentType = getIntent().getStringExtra("type");
        quizInfoText.setText(currentLanguage.concat(" ").concat(currentType).concat(" quizi"));
        title.setText(basHarfiBuyut(currentType).concat(" Quizi"));
        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sTrue = sharedPreferences1.getBoolean("sw1", false);
        sFalse = sharedPreferences1.getBoolean("sw2", false);
        vTrue = sharedPreferences1.getBoolean("sw3", false);
        vFalse = sharedPreferences1.getBoolean("sw4", false);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        jsonGramers.initJson(getApplicationContext(), currentType);

        Drawable standartBack = getResources().getDrawable(R.drawable.rounded_gray_background);
        Drawable successBack = getResources().getDrawable(R.drawable.rounded_success);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(getResources().getColor(R.color.buttonRenk));
        gradientDrawable.setCornerRadius(20.0f);
        quesBack.setBackground(gradientDrawable);
        undoBtn.setVisibility(View.GONE);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setQuestion(10000);
        pas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuestion(10000);
            }
        });
    }



    public void setQuestion(int _index){
quizInfoText2.setText("");
quizInfoText2.setVisibility(View.GONE);
if(_index == 10000){
    jsonGramers.randomQues(currentLanguage, 10000);
}else{
    jsonGramers.randomQues(currentLanguage, _index);
}

        if(currentTurn){
            quesText.setText(jsonGramers.tr);
        }else {
            quesText.setText(jsonGramers.en);
        }

        step = 0;
        buton1.setText("Çevir");
        edit.setVisibility(View.GONE);
        edit.setText("");



        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (step){
                    case 0:
                        quizInfoText2.setVisibility(View.VISIBLE);
                        if(currentTurn){
                            quizInfoText2.setText(jsonGramers.en);
                        }else {
                            quizInfoText2.setText(jsonGramers.tr);
                        }

                        buton1.setText("Tekrar et");
                        step = 1;
                        break;
                    case 1:
                        buton1.setText("Doğrula");
                        step = 2;
                        edit.setVisibility(View.VISIBLE);
                        break;
                    case 2:

                        if(edit.getText().toString().trim().toLowerCase().equals(jsonGramers.en.toLowerCase()) || edit.getText().toString().trim().toLowerCase().equals("aynen")){
                            if (sTrue) {
                                oynatSesEfekti(R.raw.success);
                                currentTurn = !currentTurn;
                            }
                            if(vTrue){


                                // Örnek: 1 saniye boyunca titreşim
                                long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                                vibrator.vibrate(pattern, -1);
                            }
                            showIsCorrect(true);
                            undoIndex = jsonGramers.num;
                            if(isUndo){
                                isUndo = false;
                            }else{
                                undoBtn.setVisibility(View.VISIBLE);
                            }
                            setQuestion(10000);


                        }else{
                            if(sFalse) {
                                oynatSesEfekti(R.raw.wrong);
                                currentTurn = !currentTurn;
                            }
                            if(vFalse){


                                // Örnek: 1 saniye boyunca titreşim
                                long[] pattern = {0, 100}; // 0ms bekleyin, ardından 1000ms (1 saniye) titreşim
                                vibrator.vibrate(pattern, -1);
                            }
                            showIsCorrect(false);
                            edit.setText("");
                        }
                }

            }
        });

        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUndo = true;
                undoBtn.setVisibility(View.GONE);
                setQuestion(undoIndex);
            }
        });






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

    private void showIsCorrect(Boolean isCorrect){
        if(isCorrect){


            originalColor = getResources().getColor(R.color.buttonRenk);
            targetColor = getResources().getColor(R.color.successBtn);

            animateBackgroundColor(targetColor, originalColor);




        }else {
            originalColor = getResources().getColor(R.color.buttonRenk);
            targetColor = getResources().getColor(R.color.errorBtn);

            animateBackgroundColor(targetColor, originalColor);
        }
    }
    private String basHarfiBuyut(String kelime) {
        if (kelime == null || kelime.isEmpty()) {
            return kelime;
        }

        char ilkHarf = kelime.charAt(0);
        char buyukIlkHarf = Character.toUpperCase(ilkHarf);

        return buyukIlkHarf + kelime.substring(1);
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