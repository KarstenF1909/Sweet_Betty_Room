package com.sweetbetty.roomDiabetesEntries;

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

public class EintragInFirestore extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Wichtig zum Reden!!!
        TTS.init(getApplicationContext());

        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();


        Intent intent = getIntent();
        int blutzucker = intent.getIntExtra("Blutzucker", 0);
        float be = intent.getFloatExtra("Broteinheiten", 0);
        float bolus = intent.getFloatExtra("Bolus", 0);
        float korrektur = intent.getFloatExtra("Korrektur", 0);
        float basal = intent.getFloatExtra("Basal", 0);
        String datum = intent.getStringExtra("Datum");
        String uhrzeit = intent.getStringExtra("Uhrzeit");
        long currentTimeMillis = intent.getLongExtra("currentTimeMillis", 0);
        long eintragDatumMillis = intent.getLongExtra("eintragDatumMillis", 0);

        int eintragID= intent.getIntExtra("eintragID", 0);


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


/*
        firestore.collection("User").document(String.valueOf(currentTimeMillis)).collection(eintragID)
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String TAG = "TAG";
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String TAG = "TAG";
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        finish();
*/

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
    }
}
