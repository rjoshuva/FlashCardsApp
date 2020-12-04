package edu.csce4623.jlcarlto.flashcardsapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CardviewActivity extends Activity {

    TextView cardFront;
    TextView cardBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_activity);

        cardFront.setText("Front test");
        cardBack.setText("Back test");
    }
}