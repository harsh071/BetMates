package com.example.betmates.objects;

import com.example.betmates.objects.content.User;

import java.util.ArrayList;

public interface BetInterface{
    void addJoiner(User newJoiner);//add one joiner to the bet
    void addJoiner(ArrayList<User> newJoiner);//add a list of joiner to the bet
    boolean removeJoiner(User editor, User removedUser);//input: editor name and people want to remove; output: if remove success return true
    double getAmount();

    Boolean getClosed();
    String getBetName();
    String getBetDescription();
    User getCreator();
    ArrayList<User> getUsers();

    void setClosed();
}
