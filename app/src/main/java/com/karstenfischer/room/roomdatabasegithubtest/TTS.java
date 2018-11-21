/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karstenfischer.room.roomdatabasegithubtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Locale;


public class TTS extends AppCompatActivity {

    private static TextToSpeech textToSpeech;
    private static String langString;

private static     UtteranceProgressListener utteranceProgressListener;

    public static void init(final Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", MODE_PRIVATE);
   langString=     sharedPreferences.getString("lang_code","de");

        if (textToSpeech == null) {
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {

                    final String TAG = "speech";
                  utteranceProgressListener=new UtteranceProgressListener() {

                        @Override
                        public void onStart(String utteranceId) {
                            Log.d(TAG, "onStart ( utteranceId :"+utteranceId+" ) ");
                        }

                        @Override
                        public void onError(String utteranceId) {
                            Log.d(TAG, "onError ( utteranceId :"+utteranceId+" ) ");
                        }

                        @Override
                        public void onDone(String utteranceId) {
                            Log.d(TAG, "onDone ( utteranceId :"+utteranceId+" ) ");
                        //    finish();
                        }
                    };





                }
            });
        }
    }

    public static void speak(final String text) {
    //    textToSpeech.setLanguage(Locale.forLanguageTag("de"));
        textToSpeech.setLanguage(Locale.forLanguageTag(langString));
        textToSpeech.setOnUtteranceProgressListener(utteranceProgressListener);
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
    }


}
