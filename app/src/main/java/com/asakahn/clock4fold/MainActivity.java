/*
This app uses the font called "Digital-7 Font Family" by Style-7
which can be accessed at https://www.1001fonts.com/digital-7-font.html#license

This app also uses a Color Picker library by skydoves
which can be accessed at https://github.com/skydoves/ColorPickerView

 */

package com.asakahn.clock4fold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;

public class MainActivity extends AppCompatActivity {

    private ColorPickerView colorPickerView;
    private Button boxChosenColor, btnStartClock;
    private RadioButton radioBtn24, radioBtn12;
    private RadioGroup radioGroupTimeFormat;
    private CheckBox chkBoxNoZero;
    int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));

        // Initialize all elements
        colorPickerView = findViewById(R.id.colorPickerView);
        boxChosenColor = findViewById(R.id.boxChosenColor);
        radioBtn24 = findViewById(R.id.radioBtn24);
        radioBtn12 = findViewById(R.id.radioBtn12);
        btnStartClock = findViewById(R.id.btnStartClock);
        radioGroupTimeFormat = findViewById(R.id.radioGroupTimeFormat);
        chkBoxNoZero = findViewById(R.id.chkBoxNoZero);

        // Update last selected button on start up
        radioBtn24.setChecked(UpdateRadioState("Button_24"));
        radioBtn12.setChecked(UpdateRadioState("Button_12"));
        if(UpdateRadioState("Button_24") == true) {
            idx = 0;
        } else {
            idx = 1;
        }

        // Update last selected color on start up
        SharedPreferences sp = getSharedPreferences("AK_APPS", MODE_PRIVATE);
        String hexColor = sp.getString("selectedColor", "#FFFFFFFF");
        colorPickerView.setInitialColor(Color.parseColor(hexColor));

        // Update chkBoxNoZero (true/false) state on start up
        boolean chkBoxNoZeroBool = sp.getBoolean("chkBoxNoZero", false);
        chkBoxNoZero.setChecked(chkBoxNoZeroBool);

        // Checks whether the checkbox is checked and returns true/false
        if(chkBoxNoZero.isChecked()) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("chkBoxNoZero", true);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("chkBoxNoZero", false);
            editor.apply();
        }

        chkBoxNoZero.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Time will show as 1:11 instead of 01:11", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("AK_APPS", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("chkBoxNoZero", true);
                    editor.apply();
                } else {
                    Toast.makeText(MainActivity.this, "Time will show as 01:11 instead of 1:11", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("AK_APPS", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("chkBoxNoZero", false);
                    editor.apply();
                }
            }
        });

        // Change color of boxChosenColor, and save chosen HEX color
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                String tempHexColor = "#" + Integer.toHexString(color);
                boxChosenColor.setBackgroundColor(Color.parseColor(tempHexColor));

                // Save Color State
                SharedPreferences sp = getSharedPreferences("AK_APPS", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("selectedColor", tempHexColor);
                editor.apply();
            }
        });

        // Update idx from RadioGroup
        radioGroupTimeFormat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioBtn24:
                        idx = 0;
                        SaveRadioState("Button_24", true);
                        SaveRadioState("Button_12", false);
                        break;
                    case R.id.radioBtn12:
                        idx = 1;
                        SaveRadioState("Button_12", true);
                        SaveRadioState("Button_24", false);
                        break;
                    default:
                        idx = 1;
                        break;
                }
            }
        });

        // If click "Start clock" button, then move to clockActivity
        btnStartClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initClock(idx);
            }
        });
    } // End onCreate()

    // Switches activity and also passes the index of radio button
    private void initClock(int idx){
        Intent intent = new Intent(MainActivity.this, clockActivity.class);
        intent.putExtra("checkedradioGroupTimeFormat", idx);
        this.startActivity(intent);
    }

    // Saves the selected Radio State so it persists when open the app again.
    private void SaveRadioState(String key, boolean value){
        SharedPreferences sp = getSharedPreferences("AK_APPS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    // Updates the saved selected Radio state on app startup
    private boolean UpdateRadioState(String key){
        SharedPreferences sp = getSharedPreferences("AK_APPS", MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
}