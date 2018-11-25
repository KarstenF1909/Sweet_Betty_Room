package com.karstenfischer.room.roomdatabasegithubtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EinstellungenTheme extends AppCompatActivity {

RadioGroup radioGroup;
RadioButton radioButton;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
private String radioButtonPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Wichtig zum Reden!!!
        TTS.init(getApplicationContext());


        WelchesTheme();


        sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //assert theme != null;
        radioButtonPressed=sharedPreferences.getString("radioButtonPressed","duestertheme");


        if(radioButtonPressed.equals("grün")){
            setTheme(R.style.greentheme);
        }
        if(radioButtonPressed.equals("dunkel")){
            setTheme(R.style.duestertheme);
        }

        setContentView(R.layout.activity_einstellungen_theme);
        //setTheme(R.style.bluetheme);
        radioGroup=findViewById(R.id.radioGroup);

    }


    private void WelchesTheme() {

        SharedPreferences sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("einstellungTheme","greentheme");
        editor.apply();

        String theme=sharedPreferences.getString("einstellungTheme","duesterTheme");

        //assert theme != null;
        if(theme.equals("greentheme")){
            setTheme(R.style.greentheme);
        }
        if(theme.equals("duestertheme")){
            setTheme(R.style.duestertheme);
        }
    }

    public void CheckRadioButton(View view) {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        radioButtonPressed=radioButton.getText().toString();

        editor.putString("radioButtonPressed",radioButtonPressed);
        editor.apply();

        TTS.speak("radio button"+radioButtonPressed);

        if(radioButtonPressed.equals("greentheme")){
            setTheme(R.style.greentheme);
        }
        if(radioButtonPressed.equals("duestertheme")){
            setTheme(R.style.duestertheme);
        }

    }
}
