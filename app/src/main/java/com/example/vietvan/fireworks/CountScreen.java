package com.example.vietvan.fireworks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import java.util.Random;

public class CountScreen extends Activity {

    TextView text, happynewyear, tx3, tx4, tx5, tx6, tx7;
    int number = 7;
    MediaPlayer mediaPlayer;
    int timer = 0;
    boolean x = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_screen);

        happynewyear = findViewById(R.id.happynewyear);
        text = findViewById(R.id.textview);
        tx3 = findViewById(R.id.textView3);
        tx4 = findViewById(R.id.textView4);
        tx5 = findViewById(R.id.textView5);
        tx6 = findViewById(R.id.textView6);
        tx7 = findViewById(R.id.textView7);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/From_Cartoon_Blocks.ttf");
        text.setTypeface(type);
        Typeface type2 = Typeface.createFromAsset(getAssets(), "fonts/UTM_Dai_Co_Viet.ttf");
        happynewyear.setTypeface(type2);
        mediaPlayer = MediaPlayer.create(this, R.raw.countdowntimer);
        mediaPlayer.start();

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(number == -1){
                    Intent intent = new Intent(CountScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    if(number >= 0)
                        text.setText(number + "");
                }
                number--;
                handler.postDelayed(this, 1000);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}
