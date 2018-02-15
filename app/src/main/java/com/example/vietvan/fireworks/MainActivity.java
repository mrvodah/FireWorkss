package com.example.vietvan.fireworks;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
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
import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifTextView;

public class MainActivity extends Activity{

    GifTextView gif;
    RelativeLayout relativeLayout;
    MediaPlayer mediaPlayer, mediaSound;
    int song[] = new int[]{
            R.raw.auld_lang_syne, R.raw.auld_lang_syne, R.raw.mylove
    };
    int currentsong = 0;
    SoundPool soundPool;
    int time = 1, delay = 1001, pos = 0;
    int[][] view = new int[][]{
            {R.drawable.s_red, R.drawable.s_green, R.drawable.s_blue, R.drawable.s_white, R.drawable.s_yellow, R.drawable.s_pink, R.drawable.s_purple},
            {R.drawable.autumn_red, R.drawable.autume_green, R.drawable.autume_blue, R.drawable.autume_white, R.drawable.autumn_yellow, R.drawable.autume_pink, R.drawable.autume_purple},
            {R.drawable.heart_red, R.drawable.heart_green, R.drawable.heart_blue, R.drawable.heart_white, R.drawable.heart_yellow, R.drawable.heart_pink, R.drawable.heart_purple},
            {R.drawable.snow_red, R.drawable.snow_green, R.drawable.snow_blue, R.drawable.snow_white, R.drawable.snow_yellow, R.drawable.snow_pink, R.drawable.snow_purple},
            {R.drawable.light_red, R.drawable.light_green, R.drawable.light_blue, R.drawable.light_white, R.drawable.light_yellow, R.drawable.light_pink, R.drawable.light_purple},
            {R.drawable.moon_red, R.drawable.moon_green, R.drawable.moon_blue, R.drawable.moon_white, R.drawable.moon_yellow, R.drawable.moon_pink, R.drawable.moon_purple},
            {R.drawable.bat_red, R.drawable.bat_green, R.drawable.bat_blue, R.drawable.bat_white, R.drawable.bat_yellow, R.drawable.bat_pink, R.drawable.bat_purple},
    };
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

        gif = findViewById(R.id.gif);
        relativeLayout = (RelativeLayout)findViewById(R.id.relative);
        mediaPlayer = MediaPlayer.create(this,song[currentsong]);
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

        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r= new Random();
                pos = r.nextInt(7);
            }
        });

        randomUI(width, height);
        //randomSnow();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaSound.stop();
        mediaPlayer.stop();
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
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
                if(time > 5){
                    delay = 1200;
                    mediaSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaSound.start();
                        }
                    });
                    ParticleSystem ps = new ParticleSystem(MainActivity.this, 100, R.drawable.autumn_red, 5000);
                    int ab = r.nextInt(7);
                    ps = new ParticleSystem(MainActivity.this, 100, view[pos][ab], 5000);

                    ps.setScaleRange(0.7f, 1.3f);
                    ps.setSpeedRange(0.1f, 0.25f);
                    ps.setAcceleration(0.0001f, 90);
                    ps.setRotationSpeedRange(90, 180);
                    ps.setFadeOut(200, new AccelerateInterpolator());
                    ps.oneShot(tx, 70);

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
