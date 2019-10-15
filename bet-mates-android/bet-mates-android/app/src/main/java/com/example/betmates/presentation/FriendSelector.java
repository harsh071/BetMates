package com.example.betmates.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.betmates.R;
import com.example.betmates.business.AccessBets;
import com.example.betmates.business.AccessUsers;
import com.example.betmates.presentation.adapter.UserSelectorRecycle;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;

import java.util.ArrayList;

public class FriendSelector extends AppCompatActivity {
    private ArrayList<User> friendList;
    private AccessBets betAccessor;
    private AccessUsers userAccessor;
    private UserSelectorRecycle adapter;
    private Bet currentBet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_selector);
        RecyclerView selectFriend = findViewById(R.id.listFriendSelector);
        selectFriend.setLayoutManager(new LinearLayoutManager(this));
        betAccessor = new AccessBets();
        userAccessor = new AccessUsers();
        currentBet = betAccessor.getCurrentBet();
        friendList = userAccessor.getUserFriends();
        adapter = new UserSelectorRecycle((friendList));
        selectFriend.setAdapter(adapter);
    }

    public void user_selector_add_friend_buttonOnClick(View v)
    {
        currentBet.setJoiner(adapter.getSelectedList());
        betAccessor.setCurrentBet(currentBet);
        startActivity(new Intent(FriendSelector.this,CreateBet.class));
    }
}
