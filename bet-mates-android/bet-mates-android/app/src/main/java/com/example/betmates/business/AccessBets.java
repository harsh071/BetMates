package com.example.betmates.business;

import com.example.betmates.objects.content.Bet;
import com.example.betmates.persistence.BetPersistence;
import com.example.betmates.application.Services;
import com.example.betmates.objects.content.User;

import java.util.ArrayList;

public class AccessBets {
    private BetPersistence betPersistence;
    private ArrayList<Bet> bets;

    public AccessBets() {
        betPersistence = Services.getBetPersistence();
        bets = betPersistence.getBetsSequential();
    }

    public AccessBets(final BetPersistence betPersistence) {
        this();
        this.betPersistence = betPersistence;
    }

    public AccessBets(final BetPersistence betPersistence, final ArrayList<Bet> bets) {
        this();
        this.betPersistence = betPersistence;
        this.bets = bets;
    }

    public ArrayList<Bet> getBetsSequential(){
        return bets;
    }

    public ArrayList<Bet> getFirstBets() {
        if (bets==null) {
            bets = betPersistence.getBetsSequential();
        }
        return bets;
    }

    public Bet getBet(String betName) {
        Bet bet = null;
        if(betName != null) {
            boolean betFound = false;
            for (int i = 0; i < bets.size(); i++) {
                if(!betFound) {
                    bet = bets.get(i);
                    betFound = bet.getBetName().equals(betName);
                }
            }

            if (!betFound) {
                bet = null;
            }
        }
        return bet;
    }



    public ArrayList<Bet> getClosedBetSequential() {
        ArrayList<Bet> listClosedBet = new ArrayList<>();
        if (bets!=null) {
            for (int i=0; i<bets.size(); i++) {
                if (bets.get(i).getIfClosed()) {
                    listClosedBet.add(bets.get(i));
                }
            }
        }
        return listClosedBet;
    }

    public ArrayList<Bet> getOpenedBetSequential() {
        ArrayList<Bet> listOpenedBet = new ArrayList<>();
        if (bets!=null) {
            for (int i=0; i<bets.size(); i++) {
                if (!bets.get(i).getIfClosed()) {
                    listOpenedBet.add(bets.get(i));
                }
            }
        }
        return listOpenedBet;
    }

    public ArrayList<Bet> getUsersBetSequential(User user) {
        ArrayList<Bet> userInvolvedBet = new ArrayList<>();
        if (bets!=null) {
            for (int i=0; i<bets.size(); i++) {
                Bet currentBet = bets.get(i);
                String userName = user.getName();
                if(currentBet.sameCreator(userName)||currentBet.hasJoiner(userName))
                {
                    userInvolvedBet.add(bets.get(i));
                }
            }
        }
        return userInvolvedBet;
    }


    public Bet insertBet(Bet currentBet) {
        return betPersistence.insertBet(currentBet);
    }

    public Bet updateBet(Bet currentBet) {
//        return betPersistence.updateBet(currentBet);
        return null;
    }

    public boolean deleteBet(Bet currentBet) {
       return betPersistence.deleteBet(currentBet);
    }

    public void setCurrentBet(Bet newBet)
    {
        betPersistence.setCurrentBet(newBet);
    }

    public Bet getCurrentBet()
    {
        return betPersistence.getCurrentBet();
    }

    public void deleteCurrentBet()
    {
        betPersistence.deleteCurrentBet();
    }

}
