package com.example.betmates.objects.content;

import java.util.ArrayList;

public class User {

    private String name;
    private int age;
    private float balance; // how much money you got!
    private ArrayList<Bet> currentBets; //contains all the bets a user has "bet" on
    private ArrayList<User> friendsList; //contains all of this users friends (Users)
    private String password; //default password 123456


    /* NULL CONSTRUCTOR. */
    public User() {
        name = "";
        age = -1;
        balance = -1;
        currentBets = new ArrayList<>();
        friendsList = new ArrayList<>();
        password = "123456";
    }

    /* CONSTRUCTOR. */
    public User(String name, int age, float balance) {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.currentBets = new ArrayList<>();
        this.friendsList = new ArrayList<>();
        password = "123456";

    }


    /* CONSTRUCTOR. */
    public User(String name, int age, float balance,String password) {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.currentBets = new ArrayList<>();
        this.friendsList = new ArrayList<>();
        this.password = password;
    }

    //--------------------------------
    // addBet
    //
    // PURPOSE: add a Bet object into the users ArrayList of current bets
    //
    // PARAMS:
    //      Bet newBet: the bet obj to be added
    //--------------------------------
    public void addBet(Bet newBet) {
        currentBets.add(newBet);
    }

    //--------------------------------
    // oldBet
    //
    // PURPOSE: remove a Bet object from current bets. Should probably happen
    //          when a bet has been completed/satisfied.
    //
    // PARAMS:
    //      Bet oldBet: bet obj to be removed
    //--------------------------------
    public void removeBet(Bet oldBet) {
        //first remove User from the Bet
        oldBet.getJoiners().remove(this); // Right now Bet class getJoiners() returns a String arrayList
        this.getCurrentBets().remove(oldBet); // Now remove the bet from this users bet list
    }



    //--------------------------------
    // addFriend
    //
    // PURPOSE: add a User object into the *CURRENT* users ArrayList of current Friends (who are users)
    //
    // PARAMS:
    //      User newFriend: the user obj to be added to the current users friends list
    //--------------------------------
    public void addFriend(User newFriend) {
        if (!friendsList.contains(newFriend)) {
            friendsList.add(newFriend); // add the friend
        }
    }

    //--------------------------------
    // removeFriend
    //
    // PURPOSE: remove a User obj from this users friends list
    //
    // PARAMS:
    //      User badFriend: the user obj to be removed from this users friends list
    //--------------------------------
    public void removeFriend(User badFriend) {
        friendsList.remove(badFriend);
    }

    //--------------------------------
    // friendExists
    //
    // PURPOSE: To see if a User exists in the friendsList
    //
    // PARAMS:
    //      User findFriend: user obj to be looked for
    //
    // RETURN: true if user obj is found
    //--------------------------------
    public boolean friendExists(User findFriend) {
        return this.friendsList.contains(findFriend);
    }

    public boolean findFriend(User friend) {
        return friendsList.contains(friend);
    }

    /*
        MUTATORS
     */

    //--------------------------------
    // modifyBalance
    //
    // PURPOSE: adjust a users balance
    //
    // PARAMS:
    //      float amount: amount of $$ to increase/decrease by
    //      boolean addMoney: TRUE = add, FALSE = subtract
    //--------------------------------
    public float modifyBalance(float amount, boolean addMoney) {
        if (addMoney) {
            balance += amount;
        } else {
            balance -= amount;
        }

        return balance;
    }


    public void setName(String newName) {
        name = newName;
    }

    public void setAge(int newAge) {
        age = newAge;
    }

    public void setBalance(float newBalance) {
        balance = newBalance;
    }

    //------------------------------------------------------
    // GETTERS.
    //
    // PURPOSE: Get user information
    //-----------------------------------------------------
    public ArrayList<Bet> getCurrentBets() {
        return currentBets;
    }

    public ArrayList<User> getFriendsList() {
        return friendsList;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public float getBalance() {
        return balance;
    }

}
