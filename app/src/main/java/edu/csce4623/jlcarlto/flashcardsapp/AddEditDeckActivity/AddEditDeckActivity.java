package edu.csce4623.jlcarlto.flashcardsapp.AddEditDeckActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import javax.annotation.Nullable;

import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;
import edu.csce4623.jlcarlto.flashcardsapp.Model.CardRepository;
import edu.csce4623.jlcarlto.flashcardsapp.Model.Deck;
import edu.csce4623.jlcarlto.flashcardsapp.R;
import edu.csce4623.jlcarlto.flashcardsapp.ViewModel.CardViewModel;

public class AddEditDeckActivity extends AppCompatActivity {
    EditText deckName;
    EditText deckDescription;
    Button btnSaveDeck;
    private long deckid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_deck);
        deckName = findViewById(R.id.etDeckName);
        deckDescription = findViewById(R.id.etDeckDesc);
        btnSaveDeck = findViewById(R.id.btnSaveDeck);
        if(getIntent().hasExtra("deckid")) {
            deckid = Long.parseLong(getIntent().getStringExtra("deckid"));
        }
        //Log.d("AddEditactivity deckid=", String.valueOf(deckid));
        btnSaveDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDeck();
            }
        });
    }
    private void saveDeck() {
        Random random = new Random();
        deckid = random.nextInt();
        Log.d("deckId", String.valueOf(deckid));
        Deck deck = new Deck(deckid, deckName.getText().toString(), deckDescription.getText().toString());
        Intent dataIntent = new Intent();
        dataIntent.putExtra("Deck", deck);
        setResult(RESULT_OK, dataIntent);
        finish();
    }
    private void deleteDeck() {

    }
}
