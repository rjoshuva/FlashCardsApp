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
     * Insert a card into the table
     * @param card
     */
    @Insert
    void insert(Card card);

    /**
     * Update a card in the table
     * @param card
     */
    @Update
    void update(Card card);

    /**
     * Delete a card in the table
     * @param id
     */
    @Query("DELETE FROM Card WHERE card_id = :id")
    void delete(long id);

    /**
     * Get all cards from the table
     * Room will generate all necessary code to update LiveData when database is updated
     * @return List of all cards in the database
     * TODO: Might want this to return a cursor object
     */
    @Query("SELECT * FROM Card")
    LiveData<List<Card>> getAllCards();

    /**
     * Get individual card from the database
     */
    @Query("SELECT * FROM Card WHERE card_id = :id")
    Card getCard(long id);
}
