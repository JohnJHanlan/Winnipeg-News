package com.johnhanlan.assignment7b;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    private Switch sTextSwitch;
    private Switch sBackground;
    private Switch sPubDate;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sTextSwitch = findViewById(R.id.switch_text_size);
        sBackground = findViewById(R.id.change_background);
        sPubDate = findViewById(R.id.hide_pub_date);

        sharedPreferences = getSharedPreferences("general", 0);

        if(sharedPreferences.getInt("text_size", 18) == 24) {
            sTextSwitch.setChecked(true);
        }

        if(sharedPreferences.getString("background_colour", "blank").equals("white")) {
            sBackground.setChecked(true);
            Helper.setBackgroundLinear(sharedPreferences, (LinearLayout) findViewById(R.id.settings_background));
        }

        if(sharedPreferences.getBoolean("hide_pub_date", true)) {
            sPubDate.setChecked(true);
        }

        final SharedPreferences.Editor editor = sharedPreferences.edit();

        sTextSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    editor.putInt("text_size", 24);
                } else {
                    editor.putInt("text_size", 18);
                }

                boolean success = editor.commit();
            }
        });

        sBackground.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    editor.putString("background_colour", "white");
                } else {
                    editor.putString("background_colour", "gray");
                }

                boolean success = editor.commit();

                Helper.setBackgroundLinear(sharedPreferences, (LinearLayout) findViewById(R.id.settings_background));
            }
        });

        sPubDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    editor.putBoolean("hide_pub_date", true);
                } else {
                    editor.putBoolean("hide_pub_date", false);
                }

                boolean success = editor.commit();
            }
        });

    }
}
