package com.sweetbetty.roomDiabetesEntries;

//Das hier ist die Entity (Eine Tabelle in der...)

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int priority;

    private int blutzucker;
    private float be;
    private float bolus;
    private float korrektur;
    private float basal;

    private String datum;
    private String uhrzeit;
    private long currentTimeMillis;
    private long eintragDatumMillis;

    //private long eintragID;

    //Constructor
    public Note(String title, String description, int priority,int blutzucker,float be,float bolus,float korrektur,float basal,String datum,String uhrzeit,long currentTimeMillis,long eintragDatumMillis) {
        this.title = title;
        this.description = description;
        this.priority = priority;

        this.blutzucker = blutzucker;
        this.be = be;
        this.bolus = bolus;
        this.korrektur = korrektur;
        this.basal = basal;

        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.currentTimeMillis = currentTimeMillis;
        this.eintragDatumMillis = eintragDatumMillis;


    }


    //Setter
    public void setId(int id) {
        this.id = id;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public int getBlutzucker() {
        return blutzucker;
    }

    public float getBe() {
        return be;
    }

    public float getBolus() {
        return bolus;
    }

    public float getKorrektur() {
        return korrektur;
    }

    public float getBasal() {
        return basal;
    }

    public String getDatum() {
        return datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public long getEintragDatumMillis() {
        return eintragDatumMillis;
    }

   // public long getEintragID() {
        //return eintragID;
    //}
}
