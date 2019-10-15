package com.example.betmates.objects.content;

import java.util.ArrayList;

public class Bet {

    private double amount;
    private String betDescription;
    private String betName;
    private User creator;
    private ArrayList<User> joiner;
    private boolean closed;

    public Bet()
    {
        amount = -1;
        betName = null;
        betDescription = null;
        creator = null;
        joiner = new ArrayList<User>();
        closed = false;
    }

    public Bet(User me) {
        amount = -1;
        betName = null;
        betDescription = null;
        creator = me;
        joiner = new ArrayList<User>();
        closed = false;
    }

    //--------------------------------
    // Constructor
    //
    // PURPOSE: constructor without interface with user
    //--------------------------------
    public Bet(double amount, String betDescription, String betName) {
        this.amount = amount;
        this.betDescription = betDescription;
        this.betName = betName;
        creator = new User("No Owner",20,0);
        joiner = new ArrayList<User>();
        closed = false;
    }

    public Bet(double amount, String betDescription, String betName, User betCreator) {
        if(amount<=betCreator.getBalance()) {
            this.amount = amount;
            this.betDescription = betDescription;
            this.betName = betName;
            joiner = new ArrayList<User>();
            creator = betCreator;
            closed = false;
        } else {
            System.out.println("Insufficent balance.");
        }
    }

    //------------------------------------------------------
    // addJoiner
    //
    // PURPOSE:   adds a bunch of Joiner to the bet.
    //-----------------------------------------------------
    public void addJoiner(User newJoiner) {
        joiner.add(newJoiner);
    }

    //User
    public void addJoiner(ArrayList<User> newJoiner) {
        joiner.addAll(newJoiner);
    }

    //------------------------------------------------------
    // removeJoiner
    //
    // PURPOSE:   remove one of the Joiner to the bet.(only creator can
    //            edit remove the joiner)
    //-----------------------------------------------------
    public Boolean removeJoiner(User currentUser) {
        return joiner.remove(currentUser);
    }

    //----------------------------------------------------------
    //some Verifyier
    //
    //PURPOSE: verify the information of the bet class
    //
    //--------------------------------------------------------------
    public Boolean sameBet(String betName)
    {
        return this.betName.equals(betName);
    }

    public Boolean sameCreator(String userName)
    {
        return this.creator.getName().equals(userName);
    }

    public Boolean hasJoiner(String joinerName) {
        if (joiner != null) {
            for (int i = 0; i < joiner.size(); i++) {
                if (joiner.get(i).getName().equals(joinerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setJoiner(ArrayList<User> friends)
    {
        joiner = friends;
    }

    //------------------------------------------------------
    // GETTERS.
    //
    // PURPOSE: Getting the bet information.
    //-----------------------------------------------------
    public String getBetName() {
        return betName;
    }

    public double getAmount() {
        return amount;
    }

    public String getBetDescription() {
        return betDescription;
    }

    public User getCreator() {
        return creator;
    }

    public ArrayList<User> getJoiners() {
        return joiner;
    }

    public boolean getIfClosed() {
        return closed;
    }

    //End of getters.

    //------------------------------------------------------
    // SETTERS.
    //
    // PURPOSE: setting the bet information.
    //-----------------------------------------------------
    public void setName(String betName){this.betName = betName;}

    public void setBetDescription(String betDescription){this.betDescription = betDescription;}

    public void setAmount(Double amount){this.amount = amount;}

    public void setClosed() {
        closed = true;
    }

}
