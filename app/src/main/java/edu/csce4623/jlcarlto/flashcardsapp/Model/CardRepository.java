package edu.csce4623.jlcarlto.flashcardsapp.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CardRepository {
    private CardDao cardDao;
    private LiveData<List<Card>> cards;

    private CardRepository(Application application) {
        //Get instance of database and get access to DAO
        CardDatabase database = CardDatabase.getInstance(application);
        cardDao = database.cardDao();
        cards = cardDao.getAllCards();
    }
}
