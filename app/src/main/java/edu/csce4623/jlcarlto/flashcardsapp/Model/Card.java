package edu.csce4623.jlcarlto.flashcardsapp.Model;

import androidx.room.*;

/**
 * Card class
 * This class represents a Java object that represents a table in the SQLite database for individual
 * cards.
 * TODO: Implement deck building, implement user sign in (user ID foreign key field?)
 */
@Entity(tableName = "Card")
public class Card {

    //constant column name values so other classes can use column names without errors
    public static final String CARD_ID = "card_id";
    public static final String CARD_FRONT = "card_front";
    public static final String CARD_BACK = "card_back";

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name = CARD_ID)
    private long cardId;

    @ColumnInfo(name = CARD_FRONT)
    private String cardFront;

    @ColumnInfo(name = CARD_BACK)
    private String cardBack;

    public Card(String cardFront, String cardBack) {
        this.cardFront = cardFront;
        this.cardBack = cardBack;
    }

    public long getId() {
        return cardId;
    }

    public void setId(long id) {
        this.cardId = id;
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
