package edu.csce4623.jlcarlto.flashcardsapp.Model;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

/**
 * CardDao Database Access Object interface for Card Room implementation.
 * Defines all database operations to execute on Entity
 * NOTE (remove later): Best practice to imlement one DAO per Entity
 */

@Dao
public interface CardDao {

    /**
     * Insert a card into the Card table
     * @param card
     */
    @Insert
    void insertCard(Card card);

    /**
     * Update a card in the Card table
     * @param card
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCard(Card card);

    /**
     * Delete a card in the Card table
     * @param id
     */
    @Query("DELETE FROM Card WHERE card_id = :id")
    void deleteCard(long id);

    /**
     * Get all cards from the table
     * Room will generate all necessary code to update LiveData when database is updated
     * @return List of all cards in the database
     * TODO: Might want this to return a cursor object
     */
    @Query("SELECT * FROM Card")
    public LiveData<List<Card>> getAllCards();

    /**
     * Get individual card from the database
     */
    @Query("SELECT * FROM Card WHERE card_id = :id")
    Card getCard(long id);

    /**
     * Insert a Deck into the Deck table
     * @param deck
     */
    @Insert
    void insertDeck(Deck deck);

    /**
     * Delete a Deck from the table
     * @param id
     */
    @Query("DELETE FROM Deck WHERE deck_id = :id")
    void deleteDeck(long id);

    /**
     * Get all decks in the Deck table
     * @return List of all decks
     */
    @Query("SELECT * FROM Deck")
    public LiveData<List<Deck>> getAllDecks();

    /**
     * Get deck of cards by Deck Name
     * @param deckName
     * @return
     */
    @Transaction
    @Query("SELECT * FROM Deck WHERE deck_title = :deckName")
    public LiveData<List<DeckWithCards>> getDeckWithCards(String deckName);

    /**
     * Get deck of cards by Deck ID
     * @param deckID
     * @return
     */
    @Transaction
    @Query("SELECT * FROM Deck WHERE deck_title = :deckID")
    public LiveData<List<DeckWithCards>> getDeckWithCards(long deckID);
}
