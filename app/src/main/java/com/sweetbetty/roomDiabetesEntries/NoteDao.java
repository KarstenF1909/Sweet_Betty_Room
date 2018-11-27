package com.sweetbetty.roomDiabetesEntries;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

//Dao erstellen (Dao muß Interface oder Abstract Class sein)  Data Access Object
@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void delateAllNotes();

    @Query("SELECT * FROM note_table ORDER BY currentTimeMillis DESC")
    LiveData<List<Note>> getAllNotes();
}
