package com.example.betmates.persistence;

import com.example.betmates.objects.content.Model;
import com.example.betmates.objects.content.Bet;

import java.util.ArrayList;

public interface  BetPersistence {
    ArrayList<Bet> getBetsSequential();//return all the bets
    Bet insertBet(Bet newBet);//insert the bet to the database and return that bet
    Bet updateBet(Bet newBet);
    boolean deleteBet(Bet currentBet);//if removed successful then will return ture.
    void setCurrentBet(Bet newBet);
    Bet getCurrentBet();
    void deleteCurrentBet();
}
