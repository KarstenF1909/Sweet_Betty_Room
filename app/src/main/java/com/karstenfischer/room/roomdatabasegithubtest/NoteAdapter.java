package com.karstenfischer.room.roomdatabasegithubtest;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    private OnItemClickListener listener;

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

        private TextView tvDatum;
        private TextView tvUhrzeit;
        private TextView tvCurrentTimeMillis;
        private TextView tveintragDatumMillis;
        private TextView tvMeineSwipeID;

        private ImageView ivEmoji;

        private CoordinatorLayout coordinatorLayout;

        NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPriority = itemView.findViewById(R.id.tvPriority);

            tvBlutzucker = itemView.findViewById(R.id.tvBlutzucker);
            tvBe = itemView.findViewById(R.id.tvBe);
            tvBolus = itemView.findViewById(R.id.tvBolus);
            tvKorrektur = itemView.findViewById(R.id.tvKorrektur);
            tvBasal = itemView.findViewById(R.id.tvBasal);

            tvBlutzuckerHeader = itemView.findViewById(R.id.tvBlutzuckerHeader);
            tvBeHeader = itemView.findViewById(R.id.tvBeHeader);
            tvBolusHeader = itemView.findViewById(R.id.tvBolusHeader);
            tvKorrekturHeader = itemView.findViewById(R.id.tvKorrekturHeader);
            tvBasalHeader = itemView.findViewById(R.id.tvBasalHeader);

            tvDatum = itemView.findViewById(R.id.tvDatum);
            tvUhrzeit = itemView.findViewById(R.id.tvUhrzeit);
            tvCurrentTimeMillis = itemView.findViewById(R.id.tvCurrentTimeMillis);
            tveintragDatumMillis = itemView.findViewById(R.id.tvEintragDatumMillis);
            ivEmoji = itemView.findViewById(R.id.ivEmoji);
            coordinatorLayout = itemView.findViewById(R.id.coordinatorLayout);

            tvDatumHeader = itemView.findViewById(R.id.tvDatumHeader);
            tvMeineSwipeID = itemView.findViewById(R.id.tvMeineSwipeID);

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
                .inflate(R.layout.note_item_neu, viewGroup, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
        Note currentNote = getItem(position);

        noteHolder.tvPriority.setVisibility(View.GONE);
        noteHolder.tvPriority.setText(String.valueOf(currentNote.getPriority()));

        if (currentNote.getTitle().isEmpty()) {
            noteHolder.tvTitle.setVisibility(View.GONE);
            noteHolder.tvTitle.setText(String.valueOf(currentNote.getTitle()));
        } else {
            noteHolder.tvTitle.setVisibility(View.VISIBLE);
            noteHolder.tvTitle.setText(String.valueOf(currentNote.getTitle()));
        }


        if (currentNote.getDescription().isEmpty()) {
            noteHolder.tvDescription.setVisibility(View.GONE);
            noteHolder.tvDescription.setText(String.valueOf(currentNote.getDescription()));
        } else {
            noteHolder.tvDescription.setVisibility(View.VISIBLE);
            noteHolder.tvDescription.setText(String.valueOf(currentNote.getDescription()));
        }


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
            noteHolder.tvBeHeader.setVisibility(View.GONE);
            noteHolder.tvBe.setText(String.valueOf(currentNote.getBe()));
        } else {
            noteHolder.tvBe.setVisibility(View.VISIBLE);
            noteHolder.tvBeHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBe.setText(String.valueOf(currentNote.getBe()));
        }


        if (currentNote.getBolus() == 0) {
            noteHolder.tvBolus.setVisibility(View.GONE);
            noteHolder.tvBolusHeader.setVisibility(View.GONE);
            noteHolder.tvBolus.setText(String.valueOf(currentNote.getBolus()));
        } else {
            noteHolder.tvBolus.setVisibility(View.VISIBLE);
            noteHolder.tvBolusHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBolus.setText(String.valueOf(currentNote.getBolus()));
        }


        if (currentNote.getKorrektur() == 0) {
            noteHolder.tvKorrektur.setVisibility(View.GONE);
            noteHolder.tvKorrekturHeader.setVisibility(View.GONE);
            noteHolder.tvKorrektur.setText(String.valueOf(currentNote.getKorrektur()));
        } else {
            noteHolder.tvKorrektur.setVisibility(View.VISIBLE);
            noteHolder.tvKorrekturHeader.setVisibility(View.VISIBLE);
            noteHolder.tvKorrektur.setText(String.valueOf(currentNote.getKorrektur()));
        }


        if (currentNote.getBasal() == 0) {
            noteHolder.tvBasal.setVisibility(View.GONE);
            noteHolder.tvBasalHeader.setVisibility(View.GONE);
            noteHolder.tvBasal.setText(String.valueOf(currentNote.getBasal()));
        } else {
            noteHolder.tvBasal.setVisibility(View.VISIBLE);
            noteHolder.tvBasalHeader.setVisibility(View.VISIBLE);
            noteHolder.tvBasal.setText(String.valueOf(currentNote.getBasal()));
        }


        if (currentNote.getBlutzucker() < 70) {
            noteHolder.ivEmoji.setImageResource(R.drawable.emoji_blau);
        }
        if (currentNote.getBlutzucker() > 69 && currentNote.getBlutzucker() < 200) {
            noteHolder.ivEmoji.setImageResource(R.drawable.emoji_gruen);
        }
        if (currentNote.getBlutzucker() > 199) {
            noteHolder.ivEmoji.setImageResource(R.drawable.emoji_rot);
        }


        noteHolder.tvDatum.setText(String.valueOf(currentNote.getDatum()));
        noteHolder.tvUhrzeit.setText(String.valueOf(currentNote.getUhrzeit()));
        //noteHolder.tvCurrentTimeMillis.setText(String.valueOf(currentNote.getCurrentTimeMillis()));
        //noteHolder.tveintragDatumMillis.setText(String.valueOf(currentNote.getEintragDatumMillis()));
        noteHolder.tvMeineSwipeID.setText(String.valueOf(currentNote.getCurrentTimeMillis()));
        noteHolder.tveintragDatumMillis.setText("Uhrzeit");
        noteHolder.tvDatumHeader.setText("Datum");
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
