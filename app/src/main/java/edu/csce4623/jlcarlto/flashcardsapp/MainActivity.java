package edu.csce4623.jlcarlto.flashcardsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.csce4623.jlcarlto.flashcardsapp.AddEditCardActivity.AddEditCardActivity;
import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;
import edu.csce4623.jlcarlto.flashcardsapp.Model.CardRepository;
import edu.csce4623.jlcarlto.flashcardsapp.Model.Deck;
import edu.csce4623.jlcarlto.flashcardsapp.Model.DeckWithCards;
import edu.csce4623.jlcarlto.flashcardsapp.ViewModel.CardViewModel;

public class MainActivity extends AppCompatActivity {

    CardViewModel mViewModel;
    private LiveData<List<Card>> cardList;
    private DeckWithCards deckList;
    private List<Card> cards;
    private Deck deck;
    private RecyclerView rv;
    private Button btnNewCard;
    public RVAdapter adapter;
    private long deckid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);
        mViewModel = new CardViewModel(getApplication());
        deck = new Deck("Title", "description");
        mViewModel.insertDeck(deck);
        deckid = deck.getDeckId();
        Log.d("onCreate deckid=", String.valueOf(deckid));
        Deck deck2 = new Deck("t2", "c2");
        Log.d("onCreate deckid2=", String.valueOf(deck2.getDeckId()));
        cardList = mViewModel.getAllCards();
        cards = new ArrayList<>();

        cardList.observe(this, new Observer <List<Card>>() {
            @Override
            public void onChanged(List<Card> c) {
                cards.clear();
                cards.addAll(c);
                Log.d("onChanged cards=", cards.toString());
            }
        });
        rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        btnNewCard = (Button)findViewById(R.id.btnNewCard);
        btnNewCard.setOnClickListener(btnOnClickListener);

        Log.d("CARDS Size", Integer.toString(cards.size()));
        if(cards == null) {
            Log.d("debug", "cards is NULL");
        }
        initializeAdapter();
    }

    private final View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            addCardItem();
        }
    };

    private void addCardItem() {
        Intent intent = new Intent(this, AddEditCardActivity.class);
        intent.putExtra("deckid", String.valueOf(deckid));
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            Card c = (Card) data.getSerializableExtra("Card");
            c.setDeckId(deckid);
            mViewModel.insertCard(c);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void initializeAdapter(){
        adapter = new RVAdapter(cards);
        rv.setAdapter(adapter);
    }
}
