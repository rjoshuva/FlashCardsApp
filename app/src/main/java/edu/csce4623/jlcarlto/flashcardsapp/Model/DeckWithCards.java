package edu.csce4623.jlcarlto.flashcardsapp.Model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import static edu.csce4623.jlcarlto.flashcardsapp.Model.Card.CARD_DECK_ID;
import static edu.csce4623.jlcarlto.flashcardsapp.Model.Deck.DECK_ID;
import edu.csce4623.jlcarlto.flashcardsapp.Model.Deck;

public class DeckWithCards {
    @Embedded public Deck deck;
    @Relation (
            parentColumn = DECK_ID,
            entityColumn = CARD_DECK_ID
    )
    public List<Card> cards;
}
