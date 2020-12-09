package edu.csce4623.jlcarlto.flashcardsapp.FlashCardActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.csce4623.jlcarlto.flashcardsapp.AddEditCardActivity.AddEditCardActivity;
import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;
import edu.csce4623.jlcarlto.flashcardsapp.Model.CardRepository;
import edu.csce4623.jlcarlto.flashcardsapp.Model.Deck;
import edu.csce4623.jlcarlto.flashcardsapp.Model.DeckWithCards;
import edu.csce4623.jlcarlto.flashcardsapp.R;
import edu.csce4623.jlcarlto.flashcardsapp.ViewModel.CardViewModel;

public class MainActivity extends AppCompatActivity {

    CardViewModel mViewModel;
    private LiveData<List<Card>> cardList;
    private List<Card> cards;
    private CardRepository mCardRepository;
    private Deck deck;
    private RecyclerView rv;
    private Button btnNewCard;
    public RVAdapter adapter;
    private long deckId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);
        mViewModel = new CardViewModel(getApplication());
        deckId = (long) getIntent().getSerializableExtra("deckId");
        Log.d("onCreate deckid=", String.valueOf(deckId));
        //Deck deck2 = new Deck("t2", "c2");
        //Log.d("onCreate deckid2=", String.valueOf(deck2.getDeckId()));
        cardList = mViewModel.getCardsInDeckById(deckId);
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
        adapter.notifyDataSetChanged();
    }

    private final View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            addCardItem();
        }
    };

    private void addCardItem() {
        Intent intent = new Intent(this, AddEditCardActivity.class);
        intent.putExtra("deckid", String.valueOf(deckId));
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if (data != null) {
                Card c = (Card) data.getSerializableExtra("Card");
                c.setDeckId(deckId);
                mViewModel.insertCard(c);
                cardList = mViewModel.mCardRepository.getAllCards();
                cards.add(c);
                adapter.replaceData(cards);
                adapter.notifyItemInserted((int) c.getCardId());
                adapter.notifyDataSetChanged();
            }
        } else if (resultCode == 2) {
                int deckId = data.getIntExtra("deckId", 0);
                mCardRepository.deleteById(deckId);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cardList = mViewModel.mCardRepository.getAllCards();
        adapter.replaceData(cards);
    }

    private void initializeAdapter(){
        adapter = new RVAdapter(cards, mViewModel);
        rv.setAdapter(adapter);
    }
}
