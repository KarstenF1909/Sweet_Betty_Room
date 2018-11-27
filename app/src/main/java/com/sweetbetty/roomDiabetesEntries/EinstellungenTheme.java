package com.sweetbetty.roomDiabetesEntries;

import android.content.Context;
import android.content.Intent;
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


        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //assert theme != null;
        radioButtonPressed = sharedPreferences.getString("radioButtonPressed", "duestertheme");


        if (radioButtonPressed.equals("grün")) {
            setTheme(R.style.greentheme);
        }
        if (radioButtonPressed.equals("dunkel")) {
            setTheme(R.style.duestertheme);
        }

        setContentView(R.layout.activity_einstellungen_theme);
        //setTheme(R.style.bluetheme);
        radioGroup = findViewById(R.id.radioGroup);

    }


    private void WelchesTheme() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("einstellungTheme", "greentheme");
        editor.apply();

        String theme = sharedPreferences.getString("einstellungTheme", "duesterTheme");

        //assert theme != null;
        if (theme.equals("grün")) {
            setTheme(R.style.greentheme);
        }
        if (theme.equals("dunkel")) {
            setTheme(R.style.duestertheme);
        }
        if (theme.equals("Army")) {
            setTheme(R.style.Army);
        }
        if (theme.equals("EmmasChoice")) {
            setTheme(R.style.EmmasChoice);
        }
        if (theme.equals("jah")) {
            setTheme(R.style.Jah);
        }
        if (theme.equals("spiderman")) {
            setTheme(R.style.spiderman);
        }
        if (theme.equals("pinklady")) {
            setTheme(R.style.pinklady);
        }
    }

    public void CheckRadioButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        radioButtonPressed = radioButton.getText().toString().trim();

        if (radioButtonPressed.equals("Emmas Choice")) {
            radioButtonPressed = "EmmasChoice";
            editor.putString("radioButtonPressed", radioButtonPressed);
            editor.apply();
        }


        editor.putString("radioButtonPressed", radioButtonPressed);
        editor.apply();

        TTS.speak("radio button" + radioButtonPressed);

        if (radioButtonPressed.equals("grün")) {
            setTheme(R.style.greentheme);
        }
        if (radioButtonPressed.equals("dunkel")) {
            setTheme(R.style.duestertheme);
        }
        if (radioButtonPressed.equals("Army")) {
            setTheme(R.style.Army);
        }
        if (radioButtonPressed.equals("EmmasChoice")) {
            setTheme(R.style.EmmasChoice);
        }
        if (radioButtonPressed.equals("spiderman")) {
            setTheme(R.style.spiderman);
        }
        if (radioButtonPressed.equals("pinklady")) {
            setTheme(R.style.pinklady);
        }
        Intent intent = new Intent(getApplicationContext(), MainActivityNeuNeuNeu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
