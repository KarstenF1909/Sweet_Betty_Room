package com.karstenfischer.room.roomdatabasegithubtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST=1;
    public static final int EDIT_NOTE_REQUEST=2;

    private NoteViewModel noteViewModel;
    private Typeface myFont;
    private CoordinatorLayout coordinatorLayout;
    private Note note;
    private int diePosition;
    private RecyclerView recyclerView;
    final NoteAdapter adapter=new NoteAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_neu_neu);


        //Wichtig zum Reden!!!
        TTS.init(getApplicationContext());


        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);

        if (toolbar != null) { setSupportActionBar(toolbar);}

        // setSupportActionBar(toolbar);


        myFont = Typeface.createFromAsset(this.getAssets(), "font/Oswald-Regular.ttf");
        coordinatorLayout=findViewById(R.id.coordinatorLayout);

        FloatingActionButton fabAdd=findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TTS.speak("Neuer Eintrag");
                Intent intent=new Intent(MainActivity.this,AddEditNoteActivity.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);



        noteViewModel=ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //update Recyclerview
                adapter.submitList(notes);

                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            //public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {    //todo
                diePosition=i;


                //int blutzuckerZwischenspeicher=note.getBlutzucker();
                //TTS.speak("Gelöschter Blutzuckerwert"+blutzuckerZwischenspeicher);


                TTS.speak("Gelöscht");
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Gelöscht", Toast.LENGTH_SHORT).show();






                //final RecyclerView.ViewHolder vh=viewHolder;

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout,  " removed from cart!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // undo is selected, restore the deleted item
                        //mAdapter.restoreItem(deletedItem, deletedIndex);

                        //TTS.speak("hier passiert noch gar nichts");
                        noteViewModel.insert(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                        Toast.makeText(MainActivity.this, "Eingefügt", Toast.LENGTH_SHORT).show();

                        //noteViewModel.insert(adapter.getNoteAt(diePosition));


                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                TTS.speak("Eintrag ändern");
                Intent intent=new Intent(MainActivity.this,AddEditNoteActivity.class);
                intent.putExtra(AddEditNoteActivity.EXTRA_ID,note.getId());
                intent.putExtra(AddEditNoteActivity.EXTRA_TITLE,note.getTitle());
                intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION,note.getDescription());
                intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY,note.getPriority());
                intent.putExtra(AddEditNoteActivity.EXTRA_BLUTZUCKER,note.getBlutzucker());
                intent.putExtra(AddEditNoteActivity.EXTRA_BE,note.getBe());
                intent.putExtra(AddEditNoteActivity.EXTRA_BOLUS,note.getBolus());
                intent.putExtra(AddEditNoteActivity.EXTRA_KORREKTUR,note.getKorrektur());
                intent.putExtra(AddEditNoteActivity.EXTRA_BASAL,note.getBasal());
                intent.putExtra(AddEditNoteActivity.EXTRA_DATUM,note.getDatum());
                intent.putExtra(AddEditNoteActivity.EXTRA_UHRZEIT,note.getUhrzeit());
                intent.putExtra(AddEditNoteActivity.EXTRA_CURRENT_TIME_MILLIS,note.getCurrentTimeMillis());
                intent.putExtra(AddEditNoteActivity.EXTRA_EINTRAG_DATUM_MILLIS,note.getEintragDatumMillis());

                startActivityForResult(intent,EDIT_NOTE_REQUEST);
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_notes:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this, "Alles gelöscht", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==ADD_NOTE_REQUEST&&resultCode==RESULT_OK){
            String title=data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description=data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority=data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY,1);
            int blutzucker=data.getIntExtra(AddEditNoteActivity.EXTRA_BLUTZUCKER,1);
            float be=data.getFloatExtra(AddEditNoteActivity.EXTRA_BE,1);
            float bolus=data.getFloatExtra(AddEditNoteActivity.EXTRA_BOLUS,1);
            float korrektur=data.getFloatExtra(AddEditNoteActivity.EXTRA_KORREKTUR,1);
            float basal=data.getFloatExtra(AddEditNoteActivity.EXTRA_BASAL,1);
            String datum=data.getStringExtra(AddEditNoteActivity.EXTRA_DATUM);
            String uhrzeit=data.getStringExtra(AddEditNoteActivity.EXTRA_UHRZEIT);
            long currentTimeMillis=data.getLongExtra(AddEditNoteActivity.EXTRA_CURRENT_TIME_MILLIS,0);
            long eintragDatumMillis=data.getLongExtra(AddEditNoteActivity.EXTRA_EINTRAG_DATUM_MILLIS,0);

            note =new Note(title,description,priority,blutzucker,be,bolus,korrektur,basal,datum,uhrzeit,currentTimeMillis,eintragDatumMillis);
            noteViewModel.insert(note);
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        }


        else if(requestCode==EDIT_NOTE_REQUEST&&resultCode==RESULT_OK){
            int id=data.getIntExtra(AddEditNoteActivity.EXTRA_ID,-1);

            if(id==-1){
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title=data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description=data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority=data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY,1);
            int blutzucker=data.getIntExtra(AddEditNoteActivity.EXTRA_BLUTZUCKER,1);
            float be=data.getFloatExtra(AddEditNoteActivity.EXTRA_BE,1);
            float bolus=data.getFloatExtra(AddEditNoteActivity.EXTRA_BOLUS,1);
            float korrektur=data.getFloatExtra(AddEditNoteActivity.EXTRA_KORREKTUR,1);
            float basal=data.getFloatExtra(AddEditNoteActivity.EXTRA_BASAL,1);
            String datum=data.getStringExtra(AddEditNoteActivity.EXTRA_DATUM);
            String uhrzeit=data.getStringExtra(AddEditNoteActivity.EXTRA_UHRZEIT);
            long currentTimeMillis=data.getLongExtra(AddEditNoteActivity.EXTRA_CURRENT_TIME_MILLIS,0);
            long eintragDatumMillis=data.getLongExtra(AddEditNoteActivity.EXTRA_EINTRAG_DATUM_MILLIS,0);

            Note note=new Note(title,description,priority,blutzucker,be,bolus,korrektur,basal,datum,uhrzeit,currentTimeMillis,eintragDatumMillis);
            note.setId(id);
            noteViewModel.update(note);
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Nothing saved", Toast.LENGTH_SHORT).show();
        }
    }




}
