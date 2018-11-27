package com.karstenfischer.room.roomdatabasegithubtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    private OnItemClickListener listener;
    private Typeface myFont;
    private Typeface myFontHeader;
    private String theme = "";


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
        private TextView tvBeHeader1;
        private TextView tvBolusHeader;
        private TextView tvBolusHeader1;
        private TextView tvKorrekturHeader;
        private TextView tvKorrekturHeader1;
        private TextView tvBasalHeader;
        private TextView tvBasalHeader1;
        private TextView tvDatumHeader;
        private TextView tvUhrzeitHeader;
        private TextView tvDatum;
        private TextView tvUhrzeit;
        private TextView tvUhr;
        private TextView tvCurrentTimeMillis;
        private TextView tvEintragDatumMillis;


        public ConstraintLayout viewForeground;
        public RelativeLayout viewBackground;

        private ImageView ivEmoji;



        NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvDatum = itemView.findViewById(R.id.tvDatum);
            tvUhrzeit = itemView.findViewById(R.id.tvUhrzeit);
            tvUhr = itemView.findViewById(R.id.tvUhr);
            tvBlutzucker = itemView.findViewById(R.id.tvBlutzucker);
            tvBe = itemView.findViewById(R.id.tvBe);
            tvBolus = itemView.findViewById(R.id.tvBolus);
            tvKorrektur = itemView.findViewById(R.id.tvKorrektur);
            tvBasal = itemView.findViewById(R.id.tvBasal);
            tvDatumHeader = itemView.findViewById(R.id.tvDatumHeader);
            tvUhrzeitHeader = itemView.findViewById(R.id.tvUhrzeitHeader);

            tvBlutzuckerHeader = itemView.findViewById(R.id.tvBlutzuckerHeader);
            tvBeHeader = itemView.findViewById(R.id.tvBeHeader);
            tvBeHeader1 = itemView.findViewById(R.id.tvBeHeader1);
            tvBolusHeader = itemView.findViewById(R.id.tvBolusHeader);
            tvBolusHeader1 = itemView.findViewById(R.id.tvBolusHeader1);
            tvKorrekturHeader = itemView.findViewById(R.id.tvKorrekturHeader);
            tvKorrekturHeader1 = itemView.findViewById(R.id.tvKorrekturHeader1);
            tvBasalHeader = itemView.findViewById(R.id.tvBasalHeader);
            tvBasalHeader1 = itemView.findViewById(R.id.tvBasalHeader1);
            tvCurrentTimeMillis = itemView.findViewById(R.id.tvCurrentTimeMillis);
            tvEintragDatumMillis = itemView.findViewById(R.id.tvEintragDatumMillis);
            ivEmoji = itemView.findViewById(R.id.ivEmoji);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

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

        //Zu geil!
        theme = PreferenceChosen.getChosenPref();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(viewGroup.getContext());

        if (theme.equals("dunkel")) {
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/IndieFlower.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/Gruppo-Regular.ttf");
        }
        if (theme.equals("grün")) {
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/Monoton-Regular.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/Gruppo-Regular.ttf");

        }
        if (theme.equals("Army")) {
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/AllertaStencil-Regular.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/AllertaStencil-Regular.ttf");

        }
        if (theme.equals("EmmasChoice")) {
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/IndieFlower.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/IndieFlower.ttf");

        }
        if (theme.equals("Jah")) {
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/FrederickatheGreat-Regular.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/IndieFlower.ttf");
        }
        if (theme.equals("spiderman")) {
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/EXTRAARA.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/EXTRASOM.ttf");
        }
        if (theme.equals("pinklady")) {
            myFont = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/GreatVibes-Regular.ttf");
            myFontHeader = Typeface.createFromAsset(viewGroup.getContext().getAssets(), "font/GreatVibes-Regular.ttf");
        }

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
        Note currentNote = getItem(position);

        if (theme.equals("dunkel")) {
            noteHolder.tvBlutzucker.setTextSize(85);
            noteHolder.tvBe.setTextSize(24);
            noteHolder.tvBolus.setTextSize(24);
            noteHolder.tvKorrektur.setTextSize(24);
            noteHolder.tvBasal.setTextSize(24);
            noteHolder.tvDatum.setTextSize(25);
            noteHolder.tvUhrzeit.setTextSize(30);
            noteHolder.tvUhr.setTextSize(14);
            noteHolder.tvTitle.setTextSize(24);
            noteHolder.tvDescription.setTextSize(12);

            noteHolder.tvBlutzuckerHeader.setTextSize(14);
            noteHolder.tvBeHeader.setTextSize(14);
            noteHolder.tvBeHeader1.setTextSize(14);
            noteHolder.tvBolusHeader.setTextSize(14);
            noteHolder.tvBolusHeader1.setTextSize(14);
            noteHolder.tvKorrekturHeader.setTextSize(14);
            noteHolder.tvKorrekturHeader1.setTextSize(14);
            noteHolder.tvBasalHeader.setTextSize(14);
            noteHolder.tvBasalHeader1.setTextSize(14);
            noteHolder.tvDatumHeader.setTextSize(14);
            noteHolder.tvUhrzeitHeader.setTextSize(14);
        }

        if (theme.equals("grün")) {
            noteHolder.tvBlutzucker.setTextSize(85);
            noteHolder.tvBe.setTextSize(24);
            noteHolder.tvBolus.setTextSize(24);
            noteHolder.tvKorrektur.setTextSize(24);
            noteHolder.tvBasal.setTextSize(24);
            noteHolder.tvDatum.setTextSize(20);
            noteHolder.tvUhrzeit.setTextSize(30);
            noteHolder.tvUhr.setTextSize(12);
            noteHolder.tvTitle.setTextSize(24);
            noteHolder.tvDescription.setTextSize(12);
            noteHolder.tvBlutzuckerHeader.setTextSize(12);
            noteHolder.tvBeHeader.setTextSize(12);
            noteHolder.tvBeHeader1.setTextSize(12);
            noteHolder.tvBolusHeader.setTextSize(12);
            noteHolder.tvBolusHeader1.setTextSize(12);
            noteHolder.tvKorrekturHeader.setTextSize(12);
            noteHolder.tvKorrekturHeader1.setTextSize(12);
            noteHolder.tvBasalHeader.setTextSize(12);
            noteHolder.tvBasalHeader1.setTextSize(12);
            noteHolder.tvDatumHeader.setTextSize(12);
            noteHolder.tvUhrzeitHeader.setTextSize(12);
        }
        if (theme.equals("Army")) {
            noteHolder.tvBlutzucker.setTextSize(85);
            noteHolder.tvBe.setTextSize(24);
            noteHolder.tvBolus.setTextSize(24);
            noteHolder.tvKorrektur.setTextSize(24);
            noteHolder.tvBasal.setTextSize(24);
            noteHolder.tvDatum.setTextSize(25);
            noteHolder.tvUhrzeit.setTextSize(30);
            noteHolder.tvUhr.setTextSize(14);
            noteHolder.tvTitle.setTextSize(24);
            noteHolder.tvDescription.setTextSize(12);
            noteHolder.tvBlutzuckerHeader.setTextSize(14);
            noteHolder.tvBeHeader.setTextSize(14);
            noteHolder.tvBeHeader1.setTextSize(14);
            noteHolder.tvBolusHeader.setTextSize(14);
            noteHolder.tvBolusHeader1.setTextSize(14);
            noteHolder.tvKorrekturHeader.setTextSize(14);
            noteHolder.tvKorrekturHeader1.setTextSize(14);
            noteHolder.tvBasalHeader.setTextSize(14);
            noteHolder.tvBasalHeader1.setTextSize(14);
            noteHolder.tvDatumHeader.setTextSize(14);
            noteHolder.tvUhrzeitHeader.setTextSize(14);
        }

        if (theme.equals("EmmasChoice")) {
            noteHolder.tvBlutzucker.setTextSize(85);
            noteHolder.tvBe.setTextSize(24);
            noteHolder.tvBolus.setTextSize(24);
            noteHolder.tvKorrektur.setTextSize(24);
            noteHolder.tvBasal.setTextSize(24);
            noteHolder.tvDatum.setTextSize(25);
            noteHolder.tvUhrzeit.setTextSize(30);
            noteHolder.tvUhr.setTextSize(14);
            noteHolder.tvTitle.setTextSize(24);
            noteHolder.tvDescription.setTextSize(12);
            noteHolder.tvBlutzuckerHeader.setTextSize(14);
            noteHolder.tvBeHeader.setTextSize(14);
            noteHolder.tvBeHeader1.setTextSize(14);
            noteHolder.tvBolusHeader.setTextSize(14);
            noteHolder.tvBolusHeader1.setTextSize(14);
            noteHolder.tvKorrekturHeader.setTextSize(14);
            noteHolder.tvKorrekturHeader1.setTextSize(14);
            noteHolder.tvBasalHeader.setTextSize(14);
            noteHolder.tvBasalHeader1.setTextSize(14);
            noteHolder.tvDatumHeader.setTextSize(14);
            noteHolder.tvUhrzeitHeader.setTextSize(14);
        }

        if (theme.equals("Jah")) {
            noteHolder.tvBlutzucker.setTextSize(85);
            noteHolder.tvBe.setTextSize(24);
            noteHolder.tvBolus.setTextSize(24);
            noteHolder.tvKorrektur.setTextSize(24);
            noteHolder.tvBasal.setTextSize(24);
            noteHolder.tvDatum.setTextSize(25);
            noteHolder.tvUhrzeit.setTextSize(30);
            noteHolder.tvUhr.setTextSize(14);
            noteHolder.tvTitle.setTextSize(24);
            noteHolder.tvDescription.setTextSize(12);
            noteHolder.tvBlutzuckerHeader.setTextSize(14);
            noteHolder.tvBeHeader.setTextSize(14);
            noteHolder.tvBeHeader1.setTextSize(14);
            noteHolder.tvBolusHeader.setTextSize(14);
            noteHolder.tvBolusHeader1.setTextSize(14);
            noteHolder.tvKorrekturHeader.setTextSize(14);
            noteHolder.tvKorrekturHeader1.setTextSize(14);
            noteHolder.tvBasalHeader.setTextSize(14);
            noteHolder.tvBasalHeader1.setTextSize(14);
            noteHolder.tvDatumHeader.setTextSize(14);
            noteHolder.tvUhrzeitHeader.setTextSize(14);
            noteHolder.tvUhr.setTextSize(14);
        }

        if (theme.equals("spiderman")) {
            noteHolder.tvBlutzucker.setTextSize(55);
            noteHolder.tvBe.setTextSize(24);
            noteHolder.tvBolus.setTextSize(24);
            noteHolder.tvKorrektur.setTextSize(24);
            noteHolder.tvBasal.setTextSize(24);
            noteHolder.tvDatum.setTextSize(25);
            noteHolder.tvUhrzeit.setTextSize(30);
            noteHolder.tvUhr.setTextSize(14);
            noteHolder.tvTitle.setTextSize(24);
            noteHolder.tvDescription.setTextSize(12);
            noteHolder.tvBlutzuckerHeader.setTextSize(14);
            noteHolder.tvBeHeader.setTextSize(8);
            noteHolder.tvBeHeader1.setTextSize(8);
            noteHolder.tvBolusHeader.setTextSize(8);
            noteHolder.tvBolusHeader1.setTextSize(8);
            noteHolder.tvKorrekturHeader.setTextSize(8);
            noteHolder.tvKorrekturHeader1.setTextSize(8);
            noteHolder.tvBasalHeader.setTextSize(8);
            noteHolder.tvBasalHeader1.setTextSize(8);
            noteHolder.tvDatumHeader.setTextSize(8);
            noteHolder.tvUhrzeitHeader.setTextSize(8);
        }

        if (theme.equals("pinklady")) {
            noteHolder.tvBlutzucker.setTextSize(85);
            noteHolder.tvBe.setTextSize(24);
            noteHolder.tvBolus.setTextSize(24);
            noteHolder.tvKorrektur.setTextSize(24);
            noteHolder.tvBasal.setTextSize(24);
            noteHolder.tvDatum.setTextSize(25);
            noteHolder.tvUhrzeit.setTextSize(30);
            noteHolder.tvUhr.setTextSize(14);
            noteHolder.tvTitle.setTextSize(24);
            noteHolder.tvDescription.setTextSize(12);
            noteHolder.tvBlutzuckerHeader.setTextSize(14);
            noteHolder.tvBeHeader.setTextSize(12);
            noteHolder.tvBeHeader1.setTextSize(12);
            noteHolder.tvBolusHeader.setTextSize(12);
            noteHolder.tvBolusHeader1.setTextSize(12);
            noteHolder.tvKorrekturHeader.setTextSize(12);
            noteHolder.tvKorrekturHeader1.setTextSize(12);
            noteHolder.tvBasalHeader.setTextSize(12);
            noteHolder.tvBasalHeader1.setTextSize(12);
            noteHolder.tvDatumHeader.setTextSize(12);
            noteHolder.tvUhrzeitHeader.setTextSize(12);
        }

        noteHolder.tvBlutzucker.setTypeface(myFont);
        noteHolder.tvBe.setTypeface(myFont);
        noteHolder.tvBolus.setTypeface(myFont);
        noteHolder.tvKorrektur.setTypeface(myFont);
        noteHolder.tvBasal.setTypeface(myFont);
        noteHolder.tvDatum.setTypeface(myFont);
        noteHolder.tvUhrzeit.setTypeface(myFont);
        noteHolder.tvUhr.setTypeface(myFont);
        noteHolder.tvTitle.setTypeface(myFont);
        noteHolder.tvDescription.setTypeface(myFont);
        noteHolder.tvBlutzuckerHeader.setTypeface(myFontHeader);
        noteHolder.tvBeHeader.setTypeface(myFontHeader);
        noteHolder.tvBeHeader1.setTypeface(myFontHeader);
        noteHolder.tvBolusHeader.setTypeface(myFontHeader);
        noteHolder.tvBolusHeader1.setTypeface(myFontHeader);
        noteHolder.tvKorrekturHeader.setTypeface(myFontHeader);
        noteHolder.tvKorrekturHeader1.setTypeface(myFontHeader);
        noteHolder.tvBasalHeader.setTypeface(myFontHeader);
        noteHolder.tvBasalHeader1.setTypeface(myFontHeader);
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
            noteHolder.tvBe.setVisibility(View.INVISIBLE);
            noteHolder.tvBeHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBeHeader1.setVisibility(View.INVISIBLE);
        } else {
            noteHolder.tvBe.setVisibility(View.VISIBLE);
            noteHolder.tvBeHeader.setVisibility(View.INVISIBLE);
            noteHolder.tvBeHeader1.setVisibility(View.VISIBLE);

        }
        noteHolder.tvBe.setText(String.valueOf(currentNote.getBe()));

        if (currentNote.getBolus() == 0) {
            noteHolder.tvBolus.setVisibility(View.INVISIBLE);
            noteHolder.tvBolusHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBolusHeader1.setVisibility(View.INVISIBLE);
        } else {
            noteHolder.tvBolus.setVisibility(View.VISIBLE);
            noteHolder.tvBolusHeader.setVisibility(View.INVISIBLE);
            noteHolder.tvBolusHeader1.setVisibility(View.VISIBLE);

        }
        noteHolder.tvBolus.setText(String.valueOf(currentNote.getBolus()));

        if (currentNote.getKorrektur() == 0) {
            noteHolder.tvKorrektur.setVisibility(View.INVISIBLE);
            noteHolder.tvKorrekturHeader.setVisibility(View.VISIBLE);
            noteHolder.tvKorrekturHeader1.setVisibility(View.INVISIBLE);
        } else {
            noteHolder.tvKorrektur.setVisibility(View.VISIBLE);
            noteHolder.tvKorrekturHeader.setVisibility(View.INVISIBLE);
            noteHolder.tvKorrekturHeader1.setVisibility(View.VISIBLE);
        }
        noteHolder.tvKorrektur.setText(String.valueOf(currentNote.getKorrektur()));



        if (currentNote.getBasal() == 0) {
            noteHolder.tvBasal.setVisibility(View.INVISIBLE);
            noteHolder.tvBasalHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBasalHeader1.setVisibility(View.INVISIBLE);
        } else {
            noteHolder.tvBasal.setVisibility(View.VISIBLE);
            noteHolder.tvBasalHeader.setVisibility(View.INVISIBLE);
            noteHolder.tvBasalHeader1.setVisibility(View.VISIBLE);
        }
        noteHolder.tvBasal.setText(String.valueOf(currentNote.getBasal()));



        if(theme.equals("spiderman")){
            if (currentNote.getBlutzucker() < 70) {
                noteHolder.ivEmoji.setImageResource(R.drawable.wertblauspiderman);
            }
            if (currentNote.getBlutzucker() > 69 && currentNote.getBlutzucker() < 200) {
                noteHolder.ivEmoji.setImageResource(R.drawable.wertgruenspiderman);
            }
            if (currentNote.getBlutzucker() > 199) {
                noteHolder.ivEmoji.setImageResource(R.drawable.wertrotspiderman);
            }
        }else
        if(theme.equals("pinklady")){
            if (currentNote.getBlutzucker() < 70) {
                noteHolder.ivEmoji.setImageResource(R.drawable.wertblaupinklady);
            }
            if (currentNote.getBlutzucker() > 69 && currentNote.getBlutzucker() < 200) {
                noteHolder.ivEmoji.setImageResource(R.drawable.wertgruenpinklady);
            }
            if (currentNote.getBlutzucker() > 199) {
                noteHolder.ivEmoji.setImageResource(R.drawable.wertrotpinklady);
            }
        }else{
            if (currentNote.getBlutzucker() < 70) {
                noteHolder.ivEmoji.setImageResource(R.drawable.iconblau);
            }
            if (currentNote.getBlutzucker() > 69 && currentNote.getBlutzucker() < 200) {
                noteHolder.ivEmoji.setImageResource(R.drawable.icongruen);
            }
            if (currentNote.getBlutzucker() > 199) {
                noteHolder.ivEmoji.setImageResource(R.drawable.iconrot);
            }
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
