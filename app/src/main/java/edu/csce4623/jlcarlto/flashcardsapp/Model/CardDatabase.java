package edu.csce4623.jlcarlto.flashcardsapp.Model;


import android.content.Context;

import androidx.room.Database;
import androidx.room.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.csce4623.jlcarlto.flashcardsapp.Constants;


/**
 * CardDatabase class
 * Instantiates a singleton instance of the card database
 * Connects Entities to corresponding DAOs
 * NOTE: If schema changes to database, need to uninstall app and reinstall to reinstantiate database
 * NOTE: Can implement callback functions for instance to call on database create
 * on device
 */
@Database(entities = {Card.class, Deck.class}, version = 1, exportSchema = false)
public abstract class CardDatabase extends RoomDatabase {

    // Instance singleton - only want one instance of the database everywhere in app
    //TODO: Need to change this if add multiple users? Not sure, I think not
    private static CardDatabase instance;

    // Use this to run database asynchronously on a background thread
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(Constants.NUM_THREADS);

    // Filename of the database
    private static String dbFileName = "flash_card_database";

    // Use this method to access our Dao, Room will automatically generate the code for this method
    public abstract CardDao cardDao();

    // Only one thread at a time can access this method
    public static synchronized CardDatabase getInstance(Context context) {
        // Only instantiate database if it has not already been instantiated
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                        CardDatabase.class, dbFileName)
                        .fallbackToDestructiveMigration() // lets Room recreate db if version number increased
                        .build(); // returns an instance of this database
        }
        // return newly created or already created instance
        return instance;
    }
}
