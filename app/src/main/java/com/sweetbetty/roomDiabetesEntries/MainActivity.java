package com.sweetbetty.roomDiabetesEntries;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    final NoteAdapter adapter = new NoteAdapter();
    private NoteViewModel noteViewModel;
    private CoordinatorLayout coordinatorLayout;
    private Note note;
    private int diePosition;
    private RecyclerView recyclerView;
    //private Typeface myFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_neu_neu);

        //Wichtig zum Reden!!!
        TTS.init(getApplicationContext());

        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        //myFont = Typeface.createFromAsset(this.getAssets(), "font/Oswald-Regular.ttf");
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }



        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TTS.speak("Neuer Eintrag");
                Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
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





        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {

                //todo final int position = viewHolder.getAdapterPosition(); //get position which is swipe

                if (direction == ItemTouchHelper.LEFT) {    //if swipe left

                    /*   */
                    //Snackbar snackbar = Snackbar
                    //        .make(coordinatorLayout, " gelöscht!", Snackbar.LENGTH_LONG);
                    //snackbar.setAction("DOCH NICHT", new View.OnClickListener() {
                    //    @Override
                    //    public void onClick(View view) {
                    //        adapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                    //        adapter.notifyItemRangeChanged(position, adapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                    //        return;
                    //    }
                    //});
                    //snackbar.setActionTextColor(Color.YELLOW);
                    //snackbar.show();
                    //noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));


                    //TextView swipeID=viewHolder.itemView.get(R.id.tvCurrentTimeMillis);
                    /**/
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //alert for confirm to delete
                    builder.setMessage("Eintrag löschen?");    //set message
                    builder.setIcon(R.drawable.emoji_rot);

                    builder.setPositiveButton("LÖSCHEN", new DialogInterface.OnClickListener() { //when click on DELETE
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final int position = viewHolder.getAdapterPosition(); //get position which is swipe
                            //todo adapter.notifyItemRemoved(position);    //item removed from recylcerview
                            //sqldatabase.execSQL("delete from " + TABLE_NAME + " where _id='" + (position + 1) + "'"); //query for delete
                            //list.remove(position);  //then remove item
                            noteViewModel.delete(adapter.getNoteAt(position));

                            //todo currentTimeMillis muß auch anders zu kriegen sein!!!
                            final long currentTimeMillis = Long.parseLong(
                                    ((TextView)Objects.requireNonNull
                                            (recyclerView.findViewHolderForAdapterPosition(position))
                                            .itemView.findViewById(R.id.tvMeineSwipeID)).getText().toString());


                            //Eintrag aus Firestore löschen
                            firestore.collection("Users").document(String.valueOf(currentTimeMillis)).delete();
                            TTS.speak("li la löschen ");

                        }
                    }).setNegativeButton("DOCH NICHT", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final int position = viewHolder.getAdapterPosition(); //get position which is swipe
                            adapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                            adapter.notifyItemRangeChanged(position, adapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                            return;
                        }
                    }).show();  //show alert dialog

                    noteViewModel.getAllNotes().observe(MainActivity.this, new Observer<List<Note>>() {
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
                Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
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
            int eintragID=data.getIntExtra(AddEditNoteActivity.EXTRA_ID, 0);

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

}
