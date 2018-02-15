package com.example.vietvan.fireworks;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity{

    RelativeLayout relativeLayout;
    MediaPlayer mediaPlayer, mediaSound;
    SoundPool soundPool;
    int time = 1, delay = 1001;
    Handler handler;
    Distich[] array = new Distich[]{
            new Distich("Tân niên tân phúc tân tri ký", "Vạn lộc vạn tài vạn công danh"),
            new Distich("Xuân an khang đức tài như ý", "Niên thịnh vượng phúc thọ vô biên"),
            new Distich("Tết đến gia đình vui sum họp", "Xuân về con cháu hưởng bình an"),
            new Distich("Thịt mỡ, dưa hành, câu đối đỏ", "Cây nêu, tràng pháo, bánh chưng xanh"),
            new Distich("Trời thêm tuổi mới, người thêm thọ", "Xuân khắp mọi nơi, phúc khắp nhà"),
            new Distich("Lộc biếc, mai vàng, xuân hạnh phúc", "Đời vui, sức khoẻ, tết an khang"),
            new Distich("Tiễn gà đi chúc xuân vui hạnh phúc", "Đón chó về mừng Tết đạt thành công"),
    };
    List<Distich> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout)findViewById(R.id.relative);
        mediaPlayer = MediaPlayer.create(this,R.raw.happy_new_year_abba);
        mediaSound = MediaPlayer.create(this,R.raw.sound);
        mediaPlayer.start();
        mediaSound.start();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        handler = new Handler();
        list = Arrays.asList(array);
        Thread thread = new Thread() {
            @Override
            public void run() {
                final TextView sec1, sec2;
                sec1 = findViewById(R.id.text1);
                sec2 = findViewById(R.id.text2);
                Typeface type = Typeface.createFromAsset(getAssets(), "fonts/UTMTimesBold_Italic.ttf");
                sec1.setTypeface(type);
                sec2.setTypeface(type);
                while(true){
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Random r = new Random();
                                int x = r.nextInt(7) + 0;
                                Distich dis = list.get(x);
                                sec1.setText(dis.getFirst().replace(" ", "\n"));
                                sec2.setText(dis.getSecond().replace(" ", "\n"));
                            }
                        });
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        randomUI(width, height);
        //randomSnow();
    }

    private void randomUI(final int width, final int height) {
        final Random r = new Random();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int x, y;
                x = r.nextInt(width);
                y = r.nextInt(height);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                params.leftMargin = x;
                params.topMargin = y;
                TextView tx = new TextView(MainActivity.this);
                tx.setTop((int)y * 2);
                tx.setLeft((int)x * 2);
                relativeLayout.addView(tx, params);
                if(time > 45){
                    delay = 1300;
                    mediaSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaSound.start();
                        }
                    });
                    ParticleSystem ps = new ParticleSystem(MainActivity.this, 100, R.drawable.autumn_red, 5000);
                    int ab = r.nextInt(7) + 1;
                    if(ab == 1){
                        ps = new ParticleSystem(MainActivity.this, 100, R.drawable.s_red, 5000);
                    }
                    else if(ab == 2){
                        ps = new ParticleSystem(MainActivity.this, 100, R.drawable.s_blue, 5000);
                    }
                    else if(ab == 3){
                        ps = new ParticleSystem(MainActivity.this, 100, R.drawable.s_green, 5000);
                    }
                    else if(ab == 4){
                        ps = new ParticleSystem(MainActivity.this, 100, R.drawable.s_white, 5000);
                    }
                    else if(ab == 5){
                        ps = new ParticleSystem(MainActivity.this, 100, R.drawable.s_yellow, 5000);
                    }
                    else if(ab == 6){
                        ps = new ParticleSystem(MainActivity.this, 100, R.drawable.s_purple, 5000);
                    }
                    else if(ab == 7){
                        ps = new ParticleSystem(MainActivity.this, 100, R.drawable.s_pink, 5000);
                    }

                    ps.setScaleRange(0.7f, 1.3f);
                    ps.setSpeedRange(0.1f, 0.25f);
                    ps.setAcceleration(0.0001f, 90);
                    ps.setRotationSpeedRange(90, 180);
                    ps.setFadeOut(200, new AccelerateInterpolator());
                    ps.oneShot(tx, 100);


                }
                else if(time > 15){
                    delay = 700;
                    ParticleSystem ps = new ParticleSystem(MainActivity.this, 100, R.drawable.star_pink, 800);
                    ps.setScaleRange(0.7f, 1.3f);
                    ps.setSpeedRange(0.1f, 0.25f);
                    ps.setRotationSpeedRange(90, 180);
                    ps.setFadeOut(200, new AccelerateInterpolator());
                    ps.oneShot(tx, 70);

                    ParticleSystem ps2 = new ParticleSystem(MainActivity.this, 100, R.drawable.star_white, 800);
                    ps2.setScaleRange(0.7f, 1.3f);
                    ps2.setSpeedRange(0.1f, 0.25f);
                    ps.setRotationSpeedRange(90, 180);
                    ps2.setFadeOut(200, new AccelerateInterpolator());
                    ps2.oneShot(tx, 70);
                }
                else{
                    new ParticleSystem(MainActivity.this, 100, R.drawable.star_pink, 800)
                            .setSpeedRange(0.1f, 0.25f)
                            .oneShot(tx, 100);
                }
                relativeLayout.removeView(tx);
                time++;
                handler.postDelayed(this, delay);
            }
        });
    }

}
