package edu.csce4623.jlcarlto.flashcardsapp.Model;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;
import static edu.csce4623.jlcarlto.flashcardsapp.Model.Card.CARD_DECK_ID;
import static edu.csce4623.jlcarlto.flashcardsapp.Model.Card.CARD_ID;
import static edu.csce4623.jlcarlto.flashcardsapp.Model.Deck.DECK_ID;

/**
 * Card class
 * This class represents a Java object that represents a table in the SQLite database for individual
 * cards.
 * The Foreign Key annotation declares foreign key on Deck Entity.
 */
@Entity(tableName = "Card", foreignKeys = @ForeignKey(entity = Deck.class,
                                                      parentColumns = DECK_ID,
                                                      childColumns = CARD_DECK_ID,
                                                      onDelete = CASCADE,
                                                      onUpdate = CASCADE))
public class Card implements Serializable {

    //constant column name values so other classes can use column names without errors
    public static final String CARD_ID = "card_id";
    public static final String CARD_FRONT = "card_front";
    public static final String CARD_BACK = "card_back";
    public static final String CARD_DECK_ID = "card_deck_id";

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name = CARD_ID)
    private long cardId;

    @ColumnInfo(name = CARD_FRONT)
    private String cardFront;

    @ColumnInfo(name = CARD_BACK)
    private String cardBack;

    @NonNull
    @ColumnInfo(name = CARD_DECK_ID)
    private long deckId;

    public Card(String cardFront, String cardBack, long deckId) {
        this.cardFront = cardFront;
        this.cardBack = cardBack;
        this.deckId = deckId;
    }

    public long getCardId() {
        return cardId;
    }

    public long getDeckId() {
        return deckId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }

    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    public String getCardBack() {
        return cardBack;
    }

    public void setCardBack(String cardBack) {
        this.cardBack = cardBack;
    }
}
