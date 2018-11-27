package com.karstenfischer.room.roomdatabasegithubtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UndoDeleteNote extends AppCompatActivity {
    public static final String EXTRA_ID = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_DECRIPTION";
    public static final String EXTRA_PRIORITY = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_PRIORITY";

    public static final String EXTRA_BLUTZUCKER = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_BLUTZUCKER";
    public static final String EXTRA_BE = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_BE";
    public static final String EXTRA_BOLUS = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_BOLUS";
    public static final String EXTRA_KORREKTUR = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_KORREKTUR";
    public static final String EXTRA_BASAL = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_BASAL";

    public static final String EXTRA_DATUM = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_DATUM";
    public static final String EXTRA_UHRZEIT = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_UHRZEIT";
    public static final String EXTRA_CURRENT_TIME_MILLIS = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_CURRENT_TIME_MILLIS";
    public static final String EXTRA_EINTRAG_DATUM_MILLIS = "com.karstenfischer.room.roomdatabasegithubtest.EXTRA_EINTRAG_DATUM_MILLIS";

    private int priority;
    private String title;
    private String description;
    private int blutzucker;
    private float be;
    private float bolus;
    private float korrektur;
    private float basal;
    private String datum;
    private String uhrzeit;
    private long currentTimeMillis;
    private long eintragDatumMillis;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firestore = FirebaseFirestore.getInstance();
        //Wichtig zum Reden!!!
        TTS.init(getApplicationContext());

        UndoDelete();
        finish();
    }

    private void UndoDelete() {

        Intent intent = getIntent();

        setTitle("Eintrag ändern");

        priority = (intent.getIntExtra(EXTRA_PRIORITY, 1));
        title = (intent.getStringExtra(EXTRA_TITLE));
        description = (intent.getStringExtra(EXTRA_DESCRIPTION));

        blutzucker = intent.getIntExtra(EXTRA_BLUTZUCKER, 0);
        be = intent.getFloatExtra(EXTRA_BE, 0);
        bolus = intent.getFloatExtra(EXTRA_BOLUS, 0);
        korrektur = intent.getFloatExtra(EXTRA_KORREKTUR, 0);
        basal = intent.getFloatExtra(EXTRA_BASAL, 0);

        datum  = (intent.getStringExtra(EXTRA_DATUM));
        uhrzeit  = (intent.getStringExtra(EXTRA_UHRZEIT));
        currentTimeMillis = intent.getLongExtra(EXTRA_CURRENT_TIME_MILLIS, 0);
        eintragDatumMillis = intent.getLongExtra(EXTRA_EINTRAG_DATUM_MILLIS, 0);

        TTS.speak("scheiß angeber");

        Map<String, Object> entry = new HashMap<>();
        entry.put("1-Blutzucker", blutzucker);
        entry.put("2-Broteinheiten", be);
        entry.put("3-Bolus", bolus);
        entry.put("4-Korrektur", korrektur);
        entry.put("5-Basal", basal);
        entry.put("6-Datum", datum);
        entry.put("7-Uhrzeit", uhrzeit);
        entry.put("8-currentTimeMillis", currentTimeMillis);
        entry.put("9-eintragDatumMillis", eintragDatumMillis);

        firestore.collection("Users").document(String.valueOf(currentTimeMillis))
                .set(entry)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        String TAG = "TAG";
                        Log.d(TAG, "DocumentSnapshot added with ID: " + firestore.collection("Users").getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String TAG = "TAG";
                Log.w(TAG, "Error adding document", e);
            }
        });
        finish();

        Intent data = new Intent();

        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        data.putExtra(EXTRA_BLUTZUCKER, blutzucker);
        data.putExtra(EXTRA_BE, be);
        data.putExtra(EXTRA_BOLUS, bolus);
        data.putExtra(EXTRA_KORREKTUR, korrektur);
        data.putExtra(EXTRA_BASAL, basal);
        data.putExtra(EXTRA_DATUM, datum);
        data.putExtra(EXTRA_UHRZEIT, uhrzeit);
        data.putExtra(EXTRA_CURRENT_TIME_MILLIS, currentTimeMillis);
        data.putExtra(EXTRA_EINTRAG_DATUM_MILLIS, eintragDatumMillis);

        setResult(RESULT_OK, data);
    }
}
