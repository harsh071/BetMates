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
import android.widget.Toast;


import com.example.betmates.presentation.adapter.RecyclerViewBetAdapter;
import com.example.betmates.application.MainActivity;
import com.example.betmates.R;
import com.example.betmates.business.AccessBets;
import com.example.betmates.objects.content.Bet;

import java.util.ArrayList;

public class BetHistory extends AppCompatActivity {

    ArrayList<Bet> userBets = new ArrayList<>();
    AccessBets accessBets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_history);
        accessBets = new AccessBets();
        userBets = accessBets.getClosedBetSequential();
        if(userBets.isEmpty()){
            Toast.makeText(this, "Your bet history is empty!.", Toast.LENGTH_SHORT).show();
        }
        RecyclerView betHistoryList = findViewById(R.id.betHistory);
        betHistoryList.setLayoutManager(new LinearLayoutManager(this));


        betHistoryList.setAdapter(new RecyclerViewBetAdapter(userBets));

        //This sets up the back button
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /*
        This is for the back button so users can return to main homepage
     */
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
        This is for the back button so users can return to main homepage
    */
    public boolean onCreateOptionsMenu(Menu menu){
        return true;
    }

    //--------------------------------
    // BetMates Logo click
    //
    // PURPOSE: to go back to homepage.
    //--------------------------------
    public void betMatesClick(View view){
        startActivity(new Intent(BetHistory.this, MainActivity.class));
    }
}
