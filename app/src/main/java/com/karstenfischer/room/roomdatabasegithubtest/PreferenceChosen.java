package com.karstenfischer.room.roomdatabasegithubtest;

class PreferenceChosen {

    private static String chosenPref;

    static String getChosenPref() {
        return chosenPref;
    }

    static String setChosenPref(String chosenPref) {
        PreferenceChosen.chosenPref = chosenPref;
        return chosenPref;
    }
}
