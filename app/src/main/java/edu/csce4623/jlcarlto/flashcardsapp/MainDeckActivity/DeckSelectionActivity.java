package edu.csce4623.jlcarlto.flashcardsapp.MainDeckActivity;

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

import edu.csce4623.jlcarlto.flashcardsapp.AddEditDeckActivity.AddEditDeckActivity;
import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;
import edu.csce4623.jlcarlto.flashcardsapp.Model.Deck;
import edu.csce4623.jlcarlto.flashcardsapp.R;
import edu.csce4623.jlcarlto.flashcardsapp.ViewModel.CardViewModel;

public class DeckSelectionActivity extends AppCompatActivity {

    DeckAdapter deckAdapter;
    List<Deck> decks;
    LiveData<List<Deck>> deckList;
    CardViewModel deckViewModel;
    RecyclerView rvDeck;
    Button btnAddDeck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_selection);
        rvDeck = findViewById(R.id.rv);

        deckViewModel = new CardViewModel(getApplication());
        deckList = deckViewModel.getAllDecks();
        decks = new ArrayList<>();

        deckList.observe(this, new Observer<List<Deck>>() {
            @Override
            public void onChanged(List<Deck> d) {
                decks.clear();
                decks.addAll(d);
                Log.d("onChanged decks=", decks.toString());
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvDeck.setLayoutManager(llm);

        btnAddDeck = findViewById(R.id.btnAddDeck);
        btnAddDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddEditDeckActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        deckAdapter = new DeckAdapter(decks);
        rvDeck.setAdapter(deckAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        deckAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(data != null) {
                Deck deck = (Deck) data.getSerializableExtra("Deck");
                deckViewModel.insertDeck(deck);
                deckAdapter.notifyDataSetChanged();
            }
        }
    }
}