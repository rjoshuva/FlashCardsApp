package edu.csce4623.jlcarlto.flashcardsapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {
    List<Card> cards;

    RVAdapter(List<Card> c){
        cards = c;
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView front;
            TextView back;
            front = (TextView) view.findViewById(R.id.card_front);
            back = (TextView) view.findViewById(R.id.card_back);

            if (front.getVisibility() == View.VISIBLE) {
                front.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);
            } else {
                back.setVisibility(View.INVISIBLE);
                front.setVisibility(View.VISIBLE);
            }
        }
    };

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_list_item, viewGroup, false);
        CardViewHolder cvh = new CardViewHolder(v);
        cvh.cv.setOnClickListener(mOnClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int i) {
        Card card = cards.get(i);
        Log.d("adapter", Integer.toString(i));
        holder.front.setText(card.getCardFront());
        holder.back.setText(card.getCardBack());
    }

    @Override
    public int getItemCount() {
            Log.d("getItemCount() size=", String.valueOf(cards.size()));
            return cards.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView front;
        TextView back;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            front = (TextView) itemView.findViewById(R.id.card_front);
            back = (TextView) itemView.findViewById(R.id.card_back);
            back.setVisibility(View.INVISIBLE);
        }
    }

}