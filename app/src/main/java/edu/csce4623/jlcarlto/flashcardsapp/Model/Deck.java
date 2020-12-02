package edu.csce4623.jlcarlto.flashcardsapp.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

import java.util.Collections;
import java.util.List;

import edu.csce4623.jlcarlto.flashcardsapp.Model.Card;

@Entity(tableName = "Deck")
public class Deck {

    //constant column name values so other classes can use column names without errors
    public static final String DECK_ID = "deck_id";
    public static final String DECK_TITLE = "deck_title";
    public static final String DECK_DESCRIPTION = "deck_description";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DECK_ID)
    private long deckId;

    @ColumnInfo(name = DECK_TITLE)
    private String deckTitle;

    @ColumnInfo(name = DECK_DESCRIPTION)
    private String deckDescription;


    /**
     * Constructor to create a new deck without any cards
     *
     * @param title
     * @param description
     */
    public Deck(String title, String description) {
        this.deckTitle = title;
        this.deckDescription = description;
    }

    public long getDeckId() {
        return deckId;
    }

    public String getDeckTitle() {
        return deckTitle;
    }

    public void setDeckTitle(String deckTitle) {
        this.deckTitle = deckTitle;
    }

    public String getDeckDescription() {
        return deckDescription;
    }

    public void setDeckDescription(String deckDescription) {
        this.deckDescription = deckDescription;
    }

}
