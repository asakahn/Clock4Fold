package com.asakahn.clock4fold;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class clockActivity extends AppCompatActivity {

    private TextView bigClockTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide(); // Hides title bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); // Hides Status bar
        getWindow().getDecorView().setSystemUiVisibility( // Hides Navigation Bar
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // Keeps Screen On so it doesn't sleep

        setContentView(R.layout.activity_clock);

        // ID Clock TextView
        bigClockTxt = findViewById(R.id.bigClockTxt);

        // Get HEX Color from ColorPicker from last Activity
        SharedPreferences sp = getSharedPreferences("AK_APPS", MODE_PRIVATE);
        String hexColor = sp.getString("selectedColor", "#FFFFFFFF");
        bigClockTxt.setTextColor(Color.parseColor(hexColor));

        // Get boolean from chkBoxNoZero from last Activity
        boolean chkBoxNoZero = sp.getBoolean("chkBoxNoZero", false);

        // Get index of selected Radio Button for time format(24hr/12hr)
        Intent intent = getIntent();
        int checkedradioGroupTimeFormat = intent.getIntExtra("checkedradioGroupTimeFormat", 1);

        // If Landscape, Make Text Bigger, Otherwise Portrait make text smaller
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(checkedradioGroupTimeFormat == 0) {
                bigClockTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 269);
            } else if (checkedradioGroupTimeFormat == 1) {
                bigClockTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 220);
            } else {
                bigClockTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 150);
            }
        } else {
            bigClockTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 74);
        }

        // Depending on the Radio button selected, show the different time formats. Defaults to 12hr format if nothing selected.
        // This code has Hide Navigation bar again, because if using button navigation,
        // for whatever reason the nav bar decides to show itself again, so this fixes that.
        if (checkedradioGroupTimeFormat == 0) { // 24 Hour format
            if(chkBoxNoZero == true) {
                final Handler someHandler = new Handler(getMainLooper());
                someHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bigClockTxt.setText(new SimpleDateFormat("H:mm", Locale.US).format(new Date()));
                        getWindow().getDecorView().setSystemUiVisibility( // Hides Navigation Bar
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                        someHandler.postDelayed(this, 720);
                    }
                }, 10);
            } else {
                final Handler someHandler = new Handler(getMainLooper());
                someHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bigClockTxt.setText(new SimpleDateFormat("HH:mm", Locale.US).format(new Date()));
                        getWindow().getDecorView().setSystemUiVisibility( // Hides Navigation Bar
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                        someHandler.postDelayed(this, 720);
                    }
                }, 10);
            }
        } else if (checkedradioGroupTimeFormat == 1) { // 12 hour format
            if (chkBoxNoZero == true) {
                final Handler someHandler = new Handler(getMainLooper());
                someHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bigClockTxt.setText(new SimpleDateFormat("h:mm a", Locale.US).format(new Date()));
                        getWindow().getDecorView().setSystemUiVisibility( // Hides Navigation Bar
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                        someHandler.postDelayed(this, 720);
                    }
                }, 10);
            } else {
                final Handler someHandler = new Handler(getMainLooper());
                someHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bigClockTxt.setText(new SimpleDateFormat("hh:mm a", Locale.US).format(new Date()));
                        getWindow().getDecorView().setSystemUiVisibility( // Hides Navigation Bar
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                        someHandler.postDelayed(this, 720);
                    }
                }, 10);
            }
        } else { // 12 hour format
            if (chkBoxNoZero == true) {
                final Handler someHandler = new Handler(getMainLooper());
                someHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bigClockTxt.setText(new SimpleDateFormat("h:mm a", Locale.US).format(new Date()));
                        getWindow().getDecorView().setSystemUiVisibility( // Hides Navigation Bar
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                        someHandler.postDelayed(this, 720);
                    }
                }, 10);
            } else {
                final Handler someHandler = new Handler(getMainLooper());
                someHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bigClockTxt.setText(new SimpleDateFormat("hh:mm a", Locale.US).format(new Date()));
                        getWindow().getDecorView().setSystemUiVisibility( // Hides Navigation Bar
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                        someHandler.postDelayed(this, 720);
                    }
                }, 10);
            }
        }
        /*
        // ###############################################################################################
        // Create Animation to shift text from left to right
        TranslateAnimation animateText = new TranslateAnimation(
                Animation.ABSOLUTE, -69.0f, Animation.ABSOLUTE, 69.0f,
                Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f );
        animateText.setDuration(65000);
        animateText.setDuration(1000);

        TranslateAnimation animateText2 = new TranslateAnimation(
                Animation.ABSOLUTE, 69.0f, Animation.ABSOLUTE, -69.0f,
                Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f );
        animateText2.setDuration(65000);
        animateText2.setDuration(1000);

        // Start first animation, then let the Listeners below handle the rest.
        int startInitialAnimation = 0;
        if (startInitialAnimation == 0) {
            bigClockTxt.startAnimation(animateText);
            startInitialAnimation = 1;
        }

        // When first animation ends, start second one.
        animateText.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                bigClockTxt.startAnimation(animateText2);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        // When second animation ends, start first one.
        animateText2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                bigClockTxt.startAnimation(animateText);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        // ###############################################################################################


         */ // DON'T TOUCH THIS WORKS!!!!!!

        // ###############################################################################################
        // Create Animation to shift text from left to right
        TranslateAnimation leftToRight = new TranslateAnimation(
                Animation.ABSOLUTE, -69.0f, Animation.ABSOLUTE, 69.0f,
                Animation.ABSOLUTE, -40.0f, Animation.ABSOLUTE, -40.0f );
        leftToRight.setDuration(65000);

        TranslateAnimation topToBottom = new TranslateAnimation(
                Animation.ABSOLUTE, 69.0f, Animation.ABSOLUTE, 69.0f,
                Animation.ABSOLUTE, -40.0f, Animation.ABSOLUTE, 40.0f );
        topToBottom.setDuration(45000);

        TranslateAnimation rightToLeft = new TranslateAnimation(
                Animation.ABSOLUTE, 69.0f, Animation.ABSOLUTE, -69.0f,
                Animation.ABSOLUTE, 40.0f, Animation.ABSOLUTE, 40.0f );
        rightToLeft.setDuration(65000);

        TranslateAnimation bottomToTop = new TranslateAnimation(
                Animation.ABSOLUTE, -69.0f, Animation.ABSOLUTE, -69.0f,
                Animation.ABSOLUTE, 40.0f, Animation.ABSOLUTE, -40.0f );
        bottomToTop.setDuration(45000);

        // Start first animation, then let the Listeners below handle the rest.
        int startInitialAnimation = 0;
        if (startInitialAnimation == 0) {
            bigClockTxt.startAnimation(leftToRight);
            startInitialAnimation = 1;
        }

        leftToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                bigClockTxt.startAnimation(topToBottom);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        topToBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                bigClockTxt.startAnimation(rightToLeft);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        rightToLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                bigClockTxt.startAnimation(bottomToTop);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        bottomToTop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                bigClockTxt.startAnimation(leftToRight);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        // ###############################################################################################


        // These 2 handlers below are to flicker a black screen in hopes of preventing screen burn in
        final Handler blipBlackScreen = new Handler(getMainLooper());
        blipBlackScreen.postDelayed(new Runnable() {
            @Override
            public void run() {
                bigClockTxt.setTextColor(Color.parseColor("#00000000"));
                blipBlackScreen.postDelayed(this, 240000);
            }
        }, 10);

        final Handler blipBlackScreen2 = new Handler(getMainLooper());
        blipBlackScreen2.postDelayed(new Runnable() {
            @Override
            public void run() {
                bigClockTxt.setTextColor(Color.parseColor(hexColor));
                blipBlackScreen2.postDelayed(this, 240200);
            }
        }, 10);

    }

}


