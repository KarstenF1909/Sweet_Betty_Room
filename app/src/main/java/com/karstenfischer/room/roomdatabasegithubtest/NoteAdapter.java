package com.karstenfischer.room.roomdatabasegithubtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.SharedPreferencesUtils;

import static android.content.ContentValues.TAG;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    private OnItemClickListener listener;
    private Typeface myFont;
    private Typeface myFontHeader;
    private String theme="";
    //SharedPreferences sharedPreferences;
    Context context;

    NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {

        @Override
        //public boolean areItemsTheSame(@NonNull Note note, @NonNull Note t1) {
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        //public boolean areContentsTheSame(@NonNull Note note, @NonNull Note t1) {
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority() &&
                    oldItem.getBlutzucker() == newItem.getBlutzucker() &&
                    oldItem.getBe() == newItem.getBe() &&
                    oldItem.getBolus() == newItem.getBolus() &&
                    oldItem.getKorrektur() == newItem.getKorrektur() &&
                    oldItem.getBasal() == newItem.getBasal() &&
                    oldItem.getDatum().equals(newItem.getDatum()) &&
                    oldItem.getUhrzeit().equals(newItem.getUhrzeit()) &&
                    oldItem.getCurrentTimeMillis() == newItem.getCurrentTimeMillis() &&
                    oldItem.getEintragDatumMillis() == newItem.getEintragDatumMillis();
        }
    };





    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvPriority;
        private TextView tvBlutzucker;
        private TextView tvBe;
        private TextView tvBolus;
        private TextView tvKorrektur;
        private TextView tvBasal;
        private TextView tvBlutzuckerHeader;
        private TextView tvBeHeader;
        private TextView tvBolusHeader;
        private TextView tvKorrekturHeader;
        private TextView tvBasalHeader;
        private TextView tvDatumHeader;
        private TextView tvUhrzeitHeader;
        private TextView tvDatum;
        private TextView tvUhrzeit;
        private TextView tvCurrentTimeMillis;
        private TextView tvEintragDatumMillis;
        private TextView tvMeineSwipeID;


        public ConstraintLayout  viewForeground;
        public RelativeLayout viewBackground;

        private ImageView ivEmoji;

        private CoordinatorLayout coordinatorLayout;



        NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvDatum = itemView.findViewById(R.id.tvDatum);
            tvUhrzeit = itemView.findViewById(R.id.tvUhrzeit);
            tvBlutzucker = itemView.findViewById(R.id.tvBlutzucker);
            tvBe = itemView.findViewById(R.id.tvBe);
            tvBolus = itemView.findViewById(R.id.tvBolus);
            tvKorrektur = itemView.findViewById(R.id.tvKorrektur);
            tvBasal = itemView.findViewById(R.id.tvBasal);
            tvDatumHeader = itemView.findViewById(R.id.tvDatumHeader);
            tvUhrzeitHeader = itemView.findViewById(R.id.tvUhrzeitHeader);

            tvBlutzuckerHeader = itemView.findViewById(R.id.tvBlutzuckerHeader);
            tvBeHeader = itemView.findViewById(R.id.tvBeHeader);
            tvBolusHeader = itemView.findViewById(R.id.tvBolusHeader);
            tvKorrekturHeader = itemView.findViewById(R.id.tvKorrekturHeader);
            tvBasalHeader = itemView.findViewById(R.id.tvBasalHeader);
            tvCurrentTimeMillis = itemView.findViewById(R.id.tvCurrentTimeMillis);
            tvEintragDatumMillis = itemView.findViewById(R.id.tvEintragDatumMillis);


            coordinatorLayout = itemView.findViewById(R.id.coordinatorLayout);
            ivEmoji = itemView.findViewById(R.id.ivEmoji);
            tvMeineSwipeID = itemView.findViewById(R.id.tvMeineSwipeID);

            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);



//todo*******************************************************************
            //String chosenPreference="";
            ////PreferenceChosen preferenceChosen = new PreferenceChosen(chosenPreference);
            //chosenPreference= PreferenceChosen.getChosenPref();
            //theme=chosenPreference;












            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item_neu_neu_neu, viewGroup, false);

        //todo*******************************************************************
        //String chosenPreference="";
        theme= PreferenceChosen.getChosenPref();





        //Context context=viewGroup.getContext();
        //SharedPreferences sharedPreferences=context.getSharedPreferences("myPrefs",Context.MODE_PRIVATE);
        //theme=sharedPreferences.getString("radioButtonPressed","dunkel");
//
        //Intent intent = new Intent();
//
        //theme=intent.getStringExtra("radioButtonPressed");
        //if(theme.equals("dunkel")){
        //    myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/batmfo__.ttf");
        //}
        //if(theme.equals("grün")){
        //    myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/GOUDYSTO.TTF");
        //}

        SharedPreferences   sharedPreferences=PreferenceManager.getDefaultSharedPreferences(viewGroup.getContext());
        //SharedPreferences sharedPreferences =viewGroup.getContext().getSharedPreferences("myPrefs",Context.MODE_PRIVATE);
        //theme=sharedPreferences.getString("radioButtonPressed","blöde");
        if(theme.equals("dunkel")){
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/IndieFlower.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/Gruppo-Regular.ttf");
        }
        if(theme.equals("grün")){
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/Monoton-Regular.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/Gruppo-Regular.ttf");

        }
        if(theme.equals("Army")){
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/AllertaStencil-Regular.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/AllertaStencil-Regular.ttf");

        }
        if(theme.equals("EmmasChoice")){
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/IndieFlower.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/IndieFlower.ttf");

        }
        //myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/IndieFlower.ttf");






        //SharedPreferences sharedPreferences = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(onCreateViewHolder());
        //SharedPreferences sharedPreferences = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(MainActivityNeuNeu.;

        //  SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences((Context) viewGroup.getContext().getSharedPreferences("myPrefs",Context.MODE_PRIVATE));


        //SharedPreferences sharedPreferences =viewGroup.getContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);


        //theme=sharedPreferences.getString(sharedPreferences.getString(PreferencesActivity.KEY_SEARCH_COUNTRY, "-"););


        //sharedPreferences=context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        //theme=sharedPreferences.getString("radioButtonPressed","dddddunkel");

        Log.d(TAG, "onCreateViewHolder: "+theme);


        //assert theme != null;


        //myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/GOUDYSTO.TTF");

        //this.mainText.setTypeface(tf_regular);



        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
        Note currentNote = getItem(position);

        //AssetManager am = getItemViewType(position)

        if(theme.equals("grün")){
            noteHolder.tvBlutzucker.setTextSize(85);
            noteHolder.tvBe.setTextSize(24);
            noteHolder.tvBolus.setTextSize(24);
            noteHolder.tvKorrektur.setTextSize(24);
            noteHolder.tvBasal.setTextSize(24);
            noteHolder.tvDatum.setTextSize(20);
            noteHolder.tvUhrzeit.setTextSize(30);
            noteHolder.tvTitle.setTextSize(24);
            noteHolder.tvDescription.setTextSize(12);

            noteHolder.tvBlutzuckerHeader.setTextSize(12);
            noteHolder.tvBeHeader.setTextSize(12);
            noteHolder.tvBolusHeader.setTextSize(12);
            noteHolder.tvKorrekturHeader.setTextSize(12);
            noteHolder.tvBasalHeader.setTextSize(12);
            noteHolder.tvDatumHeader.setTextSize(12);
            noteHolder.tvUhrzeitHeader.setTextSize(12);
        }
        if(theme.equals("Army")){
            noteHolder.tvBlutzucker.setTextSize(85);
            noteHolder.tvBe.setTextSize(24);
            noteHolder.tvBolus.setTextSize(24);
            noteHolder.tvKorrektur.setTextSize(24);
            noteHolder.tvBasal.setTextSize(24);
            noteHolder.tvDatum.setTextSize(25);
            noteHolder.tvUhrzeit.setTextSize(30);
            noteHolder.tvTitle.setTextSize(24);
            noteHolder.tvDescription.setTextSize(12);

            noteHolder.tvBlutzuckerHeader.setTextSize(14);
            noteHolder.tvBeHeader.setTextSize(14);
            noteHolder.tvBolusHeader.setTextSize(14);
            noteHolder.tvKorrekturHeader.setTextSize(14);
            noteHolder.tvBasalHeader.setTextSize(14);
            noteHolder.tvDatumHeader.setTextSize(14);
            noteHolder.tvUhrzeitHeader.setTextSize(14);
        }

        noteHolder.tvBlutzucker.setTypeface(myFont);
        noteHolder.tvBe.setTypeface(myFont);
        noteHolder.tvBolus.setTypeface(myFont);
        noteHolder.tvKorrektur.setTypeface(myFont);
        noteHolder.tvBasal.setTypeface(myFont);
        noteHolder.tvDatum.setTypeface(myFont);
        noteHolder.tvUhrzeit.setTypeface(myFont);
        noteHolder.tvTitle.setTypeface(myFont);
        noteHolder.tvDescription.setTypeface(myFont);


        noteHolder.tvBlutzuckerHeader.setTypeface(myFontHeader);
        noteHolder.tvBeHeader.setTypeface(myFontHeader);
        noteHolder.tvBolusHeader.setTypeface(myFontHeader);
        noteHolder.tvKorrekturHeader.setTypeface(myFontHeader);
        noteHolder.tvBasalHeader.setTypeface(myFontHeader);
        noteHolder.tvDatumHeader.setTypeface(myFontHeader);
        noteHolder.tvUhrzeitHeader.setTypeface(myFontHeader);



        noteHolder.tvPriority.setVisibility(View.GONE);
        noteHolder.tvPriority.setText(String.valueOf(currentNote.getPriority()));

        if (currentNote.getBlutzucker() == 0) {
            //noteHolder.tvBlutzucker.setVisibility(View.GONE);
            //noteHolder.tvBlutzuckerHeader.setVisibility(View.GONE);
            noteHolder.tvBlutzucker.setText(" -- ");
        } else {
            noteHolder.tvBlutzucker.setVisibility(View.VISIBLE);
            noteHolder.tvBlutzuckerHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBlutzucker.setText(String.valueOf(currentNote.getBlutzucker()));
        }


        if (currentNote.getBe() == 0) {
            noteHolder.tvBe.setVisibility(View.GONE);
            //noteHolder.tvBeHeader.setVisibility(View.GONE);
            noteHolder.tvBeHeader.setTextColor(Color.parseColor("#212121"));
            noteHolder.tvBe.setText(String.valueOf(currentNote.getBe()));
            //noteHolder.tvBe.setText(String.valueOf(currentNote.getBe()));
            //noteHolder.tvBe.setText(String.valueOf(" -- "));
        } else {
            noteHolder.tvBe.setVisibility(View.VISIBLE);
            noteHolder.tvBeHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBe.setText(String.valueOf(currentNote.getBe()));
        }


        if (currentNote.getBolus() == 0) {
            noteHolder.tvBolus.setVisibility(View.GONE);
            //noteHolder.tvBolusHeader.setVisibility(View.GONE);
            noteHolder.tvBolusHeader.setTextColor(Color.parseColor("#212121"));
            noteHolder.tvBolus.setText(String.valueOf(currentNote.getBolus()));
        } else {
            noteHolder.tvBolus.setVisibility(View.VISIBLE);
            noteHolder.tvBolusHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBolus.setText(String.valueOf(currentNote.getBolus()));
        }


        if (currentNote.getKorrektur() == 0) {
            noteHolder.tvKorrektur.setVisibility(View.GONE);
            //noteHolder.tvKorrekturHeader.setVisibility(View.GONE);
            noteHolder.tvKorrekturHeader.setTextColor(Color.parseColor("#212121"));
            noteHolder.tvKorrektur.setText(String.valueOf(currentNote.getKorrektur()));
        } else {
            noteHolder.tvKorrektur.setVisibility(View.VISIBLE);
            noteHolder.tvKorrekturHeader.setVisibility(View.VISIBLE);
            noteHolder.tvKorrektur.setText(String.valueOf(currentNote.getKorrektur()));
        }


        if (currentNote.getBasal() == 0) {
            noteHolder.tvBasal.setVisibility(View.GONE);
            //noteHolder.tvBasalHeader.setVisibility(View.GONE);
            noteHolder.tvBasalHeader.setTextColor(Color.parseColor("#212121"));
            noteHolder.tvBasal.setText(String.valueOf(currentNote.getBasal()));
        } else {
            noteHolder.tvBasal.setVisibility(View.VISIBLE);
            noteHolder.tvBasalHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBasal.setText(String.valueOf(currentNote.getBasal()));
        }


        if (currentNote.getBlutzucker() < 70) {
            noteHolder.ivEmoji.setImageResource(R.drawable.iconblau);
        }
        if (currentNote.getBlutzucker() > 69 && currentNote.getBlutzucker() < 200) {
            noteHolder.ivEmoji.setImageResource(R.drawable.icongruen);
        }
        if (currentNote.getBlutzucker() > 199) {
            noteHolder.ivEmoji.setImageResource(R.drawable.iconrot);
        }


        noteHolder.tvDatum.setText(String.valueOf(currentNote.getDatum()));
        noteHolder.tvUhrzeit.setText(String.valueOf(currentNote.getUhrzeit()));
        noteHolder.tvCurrentTimeMillis.setText(String.valueOf(currentNote.getCurrentTimeMillis()));
        noteHolder.tvEintragDatumMillis.setText(String.valueOf(currentNote.getEintragDatumMillis()));
        noteHolder.tvCurrentTimeMillis.setText(String.valueOf(currentNote.getCurrentTimeMillis()));
        noteHolder.tvUhrzeitHeader.setText("Uhrzeit");
        noteHolder.tvDatumHeader.setText("Datum");

        if (String.valueOf(currentNote.getTitle()).isEmpty()) {
            noteHolder.tvTitle.setVisibility(View.GONE);
        } else {
            noteHolder.tvTitle.setVisibility(View.VISIBLE);
     }
        noteHolder.tvTitle.setText(String.valueOf(currentNote.getTitle()));  //todo
        noteHolder.tvTitle.setVisibility(View.VISIBLE);
        //noteHolder.tvTitle.setText("scheiße"+theme);

        if (String.valueOf(currentNote.getDescription()).isEmpty()) {
            noteHolder.tvDescription.setVisibility(View.GONE);
        } else {
            noteHolder.tvDescription.setVisibility(View.VISIBLE);

        }
        noteHolder.tvDescription.setText(String.valueOf(currentNote.getDescription()));  //todo
    }
    //Für onSwipe
    Note getNoteAt(int position) {
        return getItem(position);
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
