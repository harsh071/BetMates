package com.example.betmates.presentation;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betmates.application.MainActivity;
import com.example.betmates.R;

public class AcceptRejectBet extends AppCompatActivity {


    private TextView betName, betCreator, betDescription,betAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_reject_bet);

        betName = findViewById(R.id.BetName);
        betDescription = findViewById(R.id.BetDescription);
        betCreator = findViewById(R.id.BetCreator);
        betAmount = findViewById(R.id.betAmount);

        setInfo();

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

    //Takes the information from the AdapterFeed and displays it.
    public void setInfo(){
        betDescription.setText(getIntent().getStringExtra("Bet Description"));
        betAmount.setText(getIntent().getStringExtra("Bet Amount"));
        betName.setText(getIntent().getStringExtra("Bet Name"));
        betCreator.setText(getIntent().getStringExtra("Bet Creator"));
    }

    //--------------------------------
    // backClick
    //
    // PURPOSE: to go back to homepage.
    //--------------------------------
    public void backClick(View view) {
        startActivity(new Intent(AcceptRejectBet.this, MainActivity.class ));
    }

    public void acceptClick(View view) {
        Toast.makeText(this, "You have accepted this Bet.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AcceptRejectBet.this, MainActivity.class));
    }

    public void rejectClick(View view) {
        Toast.makeText(this, "You have rejected this Bet.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AcceptRejectBet.this, MainActivity.class));
    }
}
