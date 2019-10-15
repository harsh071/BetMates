package com.example.betmates.presentation;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.betmates.application.MainActivity;
import com.example.betmates.R;
import com.example.betmates.business.AccessBets;
import com.example.betmates.business.AccessUsers;
import com.example.betmates.presentation.adapter.RecyclerViewBetAdapter;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;

import java.util.ArrayList;


public class UserProfile extends AppCompatActivity {

    private TextView userName, userBalance;
    private User currUser;
    private AccessUsers accUsers = new AccessUsers();
    ArrayList<Bet> userCurrBets = new ArrayList<>();
    AccessBets accessBets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //get the user for this profile
        currUser = accUsers.getFirstUser();
        //this is to fill in the info on their profile
        userName = findViewById(R.id.UserName);
        userBalance = findViewById(R.id.UserBalance);
        //set their name and balance on the profile
        setInfo();

        //get the bets for the user and put on their profile
        accessBets = new AccessBets();
        userCurrBets = accessBets.getBetsSequential();
        //userCurrBets = accessBets.getOpenedBetSequential(); // Can we do this? I think we'd need to change the AccessBets function to make the proper call!

        RecyclerView currentBetsList = findViewById(R.id.currentBets);
        currentBetsList.setLayoutManager(new LinearLayoutManager(this));

        currentBetsList.setAdapter(new RecyclerViewBetAdapter(userCurrBets));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setInfo() {
        userName.setText(currUser.getName());
        userBalance.setText("$" + Float.toString(currUser.getBalance()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void viewHistory(View view) {
        startActivity(new Intent(UserProfile.this, BetHistory.class));
    }

    public void viewCurrentBets(View view) {
        startActivity(new Intent(UserProfile.this, CurrentBets.class));
    }

    public void viewFriends(View view) {
        startActivity(new Intent(UserProfile.this, FriendsList.class));
    }

    public void homeClick(View view) {
        startActivity(new Intent(UserProfile.this, MainActivity.class));
    }

    public void editProfile(View view) {
        startActivity(new Intent(UserProfile.this, EditProfile.class));
    }

}


