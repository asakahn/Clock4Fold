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

        // Get index of selected Radio Button for time format(24hr/12hr)
        Intent intent = getIntent();
        int checkedradioGroupTimeFormat = intent.getIntExtra("checkedradioGroupTimeFormat", 0);

        // If Landscape, Make Text Bigger, Otherwise Portrait make text smaller
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(checkedradioGroupTimeFormat == 0) {
                bigClockTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 169);
            } else if (checkedradioGroupTimeFormat == 1) {
                bigClockTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 150);
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
        } else if (checkedradioGroupTimeFormat == 1) { // 12 hour format
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
        } else { // 12 hour format
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

        // These 2 handlers below are to flicker a black screen in hopes of preventing screen burn in
        final Handler blipBlackScreen = new Handler(getMainLooper());
        blipBlackScreen.postDelayed(new Runnable() {
            @Override
            public void run() {
                bigClockTxt.setVisibility(View.INVISIBLE);
                blipBlackScreen.postDelayed(this, 240000);
            }
        }, 10);

        final Handler blipBlackScreen2 = new Handler(getMainLooper());
        blipBlackScreen2.postDelayed(new Runnable() {
            @Override
            public void run() {
                bigClockTxt.setVisibility(View.VISIBLE);
                blipBlackScreen2.postDelayed(this, 240200);
            }
        }, 10);

    }

}


