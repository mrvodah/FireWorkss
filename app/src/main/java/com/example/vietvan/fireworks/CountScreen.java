package com.example.vietvan.fireworks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CountScreen extends Activity {

    TextView text;
    int number = 7;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_screen);

        text = findViewById(R.id.textview);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/From_Cartoon_Blocks.ttf");
        text.setTypeface(type);
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
