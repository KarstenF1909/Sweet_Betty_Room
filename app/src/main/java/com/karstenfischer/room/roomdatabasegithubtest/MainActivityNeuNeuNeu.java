package com.karstenfischer.room.roomdatabasegithubtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

public class MainActivityNeuNeuNeu extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    public static final int UNDO_DELETE_REQUEST = 3;
    final NoteAdapter adapter = new NoteAdapter();
    private NoteViewModel noteViewModel;

    private Note note;
    private int diePosition;
    private RecyclerView recyclerView;
    private Typeface myFont;
    private FloatingActionButton fabTheme;
    private android.support.v7.widget.Toolbar toolbar;
    private String radioButtonPressed;
    private ImageView ivLogoBetty;
    private CollapsingToolbarLayout collapsingToolbar;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        //Wichtig zum Reden!!!
        TTS.init(getApplicationContext());

        //WelchesTheme();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);



        radioButtonPressed = sharedPreferences.getString("radioButtonPressed", "duesterTheme");

        //assert radioButtonPressed != null;
        if (radioButtonPressed.equals("grün")) {
            setTheme(R.style.greentheme);
        }
        if (radioButtonPressed.equals("dunkel")) {
            setTheme(R.style.duestertheme);
        }
        if (radioButtonPressed.equals("Army")) {
            setTheme(R.style.Army);
            //ivLogoBetty.setBackground(getDrawable(R.drawable.logo001army));
            //ivLogo.setImageResource(getResources().getDrawable().);
        }
        if (radioButtonPressed.equals("EmmasChoice")) {
            setTheme(R.style.EmmasChoice);
        }
        TTS.speak(radioButtonPressed);
        if (radioButtonPressed.equals("Jah")) {
            setTheme(R.style.Jah);
        }
        TTS.speak(radioButtonPressed);

        setContentView(R.layout.activity_main_neu_neu);

//Intent intent=new Intent(getApplicationContext(),NoteAdapter.class);
//intent.putExtra("radioButtonPressed",radioButtonPressed);
//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//startActivity(intent);


        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.toolbar);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        fabTheme = findViewById(R.id.fabTheme);
        ivLogoBetty=findViewById(R.id.ivLogoBetty);
        collapsingToolbar=findViewById(R.id.collapsingToolbar);


        if (radioButtonPressed.equals("Army")) {
            collapsingToolbar.setBackgroundResource(R.drawable.backgroundarmy1);
            ivLogoBetty.setImageResource(R.drawable.logo001army);
        }
        if (radioButtonPressed.equals("grün")) {
            collapsingToolbar.setBackgroundResource(R.drawable.collapsegreen);
        }
        if (radioButtonPressed.equals("dunkel")) {
            collapsingToolbar.setBackgroundResource(R.drawable.collapsedunkel);
        }
        if (radioButtonPressed.equals("Jah")) {
            collapsingToolbar.setBackgroundResource(R.drawable.collapsejah);
        }

        fabTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EinstellungenTheme.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        //if (toolbar != null) {
        //    setSupportActionBar(toolbar);
        //}


        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TTS.speak("Neuer Eintrag");
                Intent intent = new Intent(MainActivityNeuNeuNeu.this, AddEditNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });


        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //update Recyclerview
                adapter.submitList(notes);
                //Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });





        //todo*******************************************************************

        //PreferenceChosen preferenceChosen=(PreferenceChosen) getApplicationContext();




        String chosenPref=radioButtonPressed;
        PreferenceChosen preferenceChosen = new PreferenceChosen();
        PreferenceChosen.setChosenPref(chosenPref);
        TTS.speak("preference Chosen"+chosenPref);




        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {

                //todo final int position = viewHolder.getAdapterPosition(); //get position which is swipe

                if (direction == ItemTouchHelper.LEFT) {    //if swipe left
                    final int position = viewHolder.getAdapterPosition(); //get position which is swipe

                  final   int blutzucker;


                    String blutzuckerTest=
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvBlutzucker)).getText().toString();

                    if(blutzuckerTest.equals(" -- ")){
                          blutzucker =0;
                    }else {
                          blutzucker = Integer.parseInt(
                                ((TextView) Objects.requireNonNull
                                        (recyclerView.findViewHolderForAdapterPosition(position))
                                        .itemView.findViewById(R.id.tvBlutzucker)).getText().toString());
                    }




                    final float be = Float.parseFloat(
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvBe)).getText().toString());

                    final float bolus = Float.parseFloat(
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvBolus)).getText().toString());

                    final float korrektur = Float.parseFloat(
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvKorrektur)).getText().toString());

                    final float basal = Float.parseFloat(
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvBasal)).getText().toString());

                    final long currentTimeMillis = Long.parseLong(
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvCurrentTimeMillis)).getText().toString());

                    final long eintragDatumMillis = Long.parseLong(
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvEintragDatumMillis)).getText().toString());

                    final String datum =
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvDatum)).getText().toString();

                    final String uhrzeit =
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvUhrzeit)).getText().toString();

                    final String title =
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvTitle)).getText().toString();

                    final String description =
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvDescription)).getText().toString();

                    final int priority = Integer.parseInt(
                            ((TextView) Objects.requireNonNull
                                    (recyclerView.findViewHolderForAdapterPosition(position))
                                    .itemView.findViewById(R.id.tvPriority)).getText().toString());


                    Snackbar snackbar = Snackbar
                            //.make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
                            .make(coordinatorLayout, "" + " removed from cart!", Snackbar.LENGTH_LONG);

                    noteViewModel.delete(adapter.getNoteAt(position));
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            final int position = viewHolder.getAdapterPosition(); //get position which is swipe

                            Note note = new Note(title, description, priority, blutzucker, be, bolus, korrektur, basal, datum, uhrzeit, currentTimeMillis, eintragDatumMillis);

                            noteViewModel.insert(note);

                            Intent intent = new Intent(MainActivityNeuNeuNeu.this, UndoDeleteNote.class);

                            //intent.putExtra(AddEditNoteActivity.EXTRA_ID, id);
                            intent.putExtra(UndoDeleteNote.EXTRA_TITLE, title);
                            intent.putExtra(UndoDeleteNote.EXTRA_DESCRIPTION, description);
                            intent.putExtra(UndoDeleteNote.EXTRA_PRIORITY, priority);
                            intent.putExtra(UndoDeleteNote.EXTRA_BLUTZUCKER, blutzucker);
                            intent.putExtra(UndoDeleteNote.EXTRA_BE, be);
                            intent.putExtra(UndoDeleteNote.EXTRA_BOLUS, bolus);
                            intent.putExtra(UndoDeleteNote.EXTRA_KORREKTUR, korrektur);
                            intent.putExtra(UndoDeleteNote.EXTRA_BASAL, basal);
                            intent.putExtra(UndoDeleteNote.EXTRA_DATUM, datum);
                            intent.putExtra(UndoDeleteNote.EXTRA_UHRZEIT, uhrzeit);
                            intent.putExtra(UndoDeleteNote.EXTRA_CURRENT_TIME_MILLIS, currentTimeMillis);
                            intent.putExtra(UndoDeleteNote.EXTRA_EINTRAG_DATUM_MILLIS, eintragDatumMillis);

                            startActivityForResult(intent, UNDO_DELETE_REQUEST);


                            adapter.notifyItemChanged(position);
                            noteViewModel.getAllNotes().observe(MainActivityNeuNeuNeu.this, new Observer<List<Note>>() {
                                @Override
                                public void onChanged(@Nullable List<Note> notes) {
                                    adapter.submitList(notes);
                                }
                            });


                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();

                    //Eintrag aus Firestore löschen todo wieder aktivieren!
                    firestore.collection("Users").document(String.valueOf(currentTimeMillis)).delete();
                    //TTS.speak("li la löschen ");


                    noteViewModel.getAllNotes().observe(MainActivityNeuNeuNeu.this, new Observer<List<Note>>() {
                        @Override
                        public void onChanged(@Nullable List<Note> notes) {
                            adapter.submitList(notes);
                        }
                    });
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView); //set swipe to recylcerview
        //oder...
        //new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                //TTS.speak("Eintrag ändern");
                Intent intent = new Intent(MainActivityNeuNeuNeu.this, AddEditNoteActivity.class);
                intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
                intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
                intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY, note.getPriority());
                intent.putExtra(AddEditNoteActivity.EXTRA_BLUTZUCKER, note.getBlutzucker());
                intent.putExtra(AddEditNoteActivity.EXTRA_BE, note.getBe());
                intent.putExtra(AddEditNoteActivity.EXTRA_BOLUS, note.getBolus());
                intent.putExtra(AddEditNoteActivity.EXTRA_KORREKTUR, note.getKorrektur());
                intent.putExtra(AddEditNoteActivity.EXTRA_BASAL, note.getBasal());
                intent.putExtra(AddEditNoteActivity.EXTRA_DATUM, note.getDatum());
                intent.putExtra(AddEditNoteActivity.EXTRA_UHRZEIT, note.getUhrzeit());
                intent.putExtra(AddEditNoteActivity.EXTRA_CURRENT_TIME_MILLIS, note.getCurrentTimeMillis());
                intent.putExtra(AddEditNoteActivity.EXTRA_EINTRAG_DATUM_MILLIS, note.getEintragDatumMillis());


                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);
            int blutzucker = data.getIntExtra(AddEditNoteActivity.EXTRA_BLUTZUCKER, 1);
            float be = data.getFloatExtra(AddEditNoteActivity.EXTRA_BE, 1);
            float bolus = data.getFloatExtra(AddEditNoteActivity.EXTRA_BOLUS, 1);
            float korrektur = data.getFloatExtra(AddEditNoteActivity.EXTRA_KORREKTUR, 1);
            float basal = data.getFloatExtra(AddEditNoteActivity.EXTRA_BASAL, 1);
            String datum = data.getStringExtra(AddEditNoteActivity.EXTRA_DATUM);
            String uhrzeit = data.getStringExtra(AddEditNoteActivity.EXTRA_UHRZEIT);
            long currentTimeMillis = data.getLongExtra(AddEditNoteActivity.EXTRA_CURRENT_TIME_MILLIS, 0);
            long eintragDatumMillis = data.getLongExtra(AddEditNoteActivity.EXTRA_EINTRAG_DATUM_MILLIS, 0);
            int eintragID = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, 0);

            note = new Note(title, description, priority, blutzucker, be, bolus, korrektur, basal, datum, uhrzeit, currentTimeMillis, eintragDatumMillis);
            noteViewModel.insert(note);
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);
            int blutzucker = data.getIntExtra(AddEditNoteActivity.EXTRA_BLUTZUCKER, 1);
            float be = data.getFloatExtra(AddEditNoteActivity.EXTRA_BE, 1);
            float bolus = data.getFloatExtra(AddEditNoteActivity.EXTRA_BOLUS, 1);
            float korrektur = data.getFloatExtra(AddEditNoteActivity.EXTRA_KORREKTUR, 1);
            float basal = data.getFloatExtra(AddEditNoteActivity.EXTRA_BASAL, 1);
            String datum = data.getStringExtra(AddEditNoteActivity.EXTRA_DATUM);
            String uhrzeit = data.getStringExtra(AddEditNoteActivity.EXTRA_UHRZEIT);
            long currentTimeMillis = data.getLongExtra(AddEditNoteActivity.EXTRA_CURRENT_TIME_MILLIS, 0);
            long eintragDatumMillis = data.getLongExtra(AddEditNoteActivity.EXTRA_EINTRAG_DATUM_MILLIS, 0);

            int eintragID = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, 0);

            Note note = new Note(title, description, priority, blutzucker, be, bolus, korrektur, basal, datum, uhrzeit, currentTimeMillis, eintragDatumMillis);
            note.setId(id);
            noteViewModel.update(note);
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nothing saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this, "Alles gelöscht", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void WelchesTheme() {


    }

}
