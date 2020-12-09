package edu.csce4623.jlcarlto.flashcardsapp.AddEditCardActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.annotation.Nullable;

import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;
import edu.csce4623.jlcarlto.flashcardsapp.Model.CardRepository;
import edu.csce4623.jlcarlto.flashcardsapp.R;
import edu.csce4623.jlcarlto.flashcardsapp.ViewModel.CardViewModel;

public class AddEditCardActivity extends AppCompatActivity {
    EditText front;
    EditText back;
    Button btnSaveCard;
    Button btnDeleteCard;
    private long deckid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_card);
        front = findViewById(R.id.etFront);
        back = findViewById(R.id.etBack);
        btnSaveCard = findViewById(R.id.btnSaveCard);
        btnDeleteCard = findViewById(R.id.btnDeleteCard);
        deckid = Long.parseLong(getIntent().getStringExtra("deckid"));
        Log.d("AddEditactivity deckid=", String.valueOf(deckid));
        btnSaveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCard();
            }
        });
        btnDeleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCard();
            }
        });
    }
    private void saveCard() {
        Card card = new Card(front.getText().toString(), back.getText().toString(), deckid);
        Intent dataIntent = new Intent();
        dataIntent.putExtra("Card", card);
        setResult(RESULT_OK, dataIntent);
        finish();
    }
    private void deleteCard() {
        Intent dataIntent = new Intent();
        dataIntent.putExtra("deckId", deckid);
        setResult(2, dataIntent);
        finish();
    }
}