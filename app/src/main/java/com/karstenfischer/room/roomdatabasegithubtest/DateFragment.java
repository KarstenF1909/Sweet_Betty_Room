package com.karstenfischer.room.roomdatabasegithubtest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.Objects;

public class DateFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("MyPref", 0);
        String datum = sharedPreferences.getString("datum", "00.00.0000");

        //TTS.speak("date fragment" + datum);

        assert datum != null;
        String datumSeparated[] = datum.split("\\.");
        int year = Integer.parseInt(datumSeparated[2]);
        int month = (Integer.parseInt(datumSeparated[1]));
        int day = (Integer.parseInt(datumSeparated[0]));

        //TTS.speak("year" + datumSeparated[2]);
        //TTS.speak("month" + datumSeparated[1]);
        //TTS.speak("day" + datumSeparated[0]);

        //return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day); todo
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month-1, day);
    }
}
