package edu.csce4623.jlcarlto.flashcardsapp.MainDeckActivity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.csce4623.jlcarlto.flashcardsapp.AddEditCardActivity.AddEditCardActivity;
import edu.csce4623.jlcarlto.flashcardsapp.FlashCardActivity.MainActivity;
import edu.csce4623.jlcarlto.flashcardsapp.FlashCardActivity.RVAdapter;
import edu.csce4623.jlcarlto.flashcardsapp.Model.Deck;
import edu.csce4623.jlcarlto.flashcardsapp.R;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.DeckViewHolder> {


    List<Deck> decks;
    Deck deck;
    DeckAdapter (List<Deck> d) {
        decks = d;
    }

    @NonNull
    @Override
    public DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck_list_item, parent, false);
        DeckAdapter.DeckViewHolder dvh = new DeckAdapter.DeckViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final DeckViewHolder holder, final int position) {
        deck = decks.get(position);
        holder.tvDeck.findViewById(R.id.tvDeck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deck = decks.get(position);
                Intent intent = new Intent(holder.tvDeck.getContext(), MainActivity.class);
                Log.d("Deckid", String.valueOf(deck.getDeckId()));
                intent.putExtra("deckId", deck.getDeckId());
                view.getContext().startActivity(intent);
            }
        });
        holder.tvDeck.setText(deck.getDeckTitle());

    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    public class DeckViewHolder extends RecyclerView.ViewHolder {
        TextView tvDeck;
        CardView cardView;
        public DeckViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDeck = (TextView) itemView.findViewById(R.id.tvDeck);
            cardView = (CardView) itemView.findViewById(R.id.cv);
        }
    }
}
