package com.sweetbetty.roomDiabetesEntries;

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
