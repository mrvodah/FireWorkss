package com.example.vietvan.fireworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends Activity {

    TextView tv, tv1, tv2;
    ImageView iv;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tv = findViewById(R.id.tv);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        iv = findViewById(R.id.iv);
        handler = new Handler();

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(3000);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(3000);
        fadeOut.setDuration(3000);

        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        iv.setAnimation(animation);
        tv.setAnimation(animation);
        tv1.setAnimation(animation);
        tv2.setAnimation(animation);

        final Intent intent = new Intent(this, CountScreen.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5900);
                }catch(Exception ex){

                }
                finally{
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }
}
