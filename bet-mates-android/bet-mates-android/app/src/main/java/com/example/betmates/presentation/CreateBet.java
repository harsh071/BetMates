package com.example.betmates.presentation;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betmates.application.MainActivity;
import com.example.betmates.R;
import com.example.betmates.business.AccessBets;
import com.example.betmates.business.AccessModel;
import com.example.betmates.business.AccessUsers;
import com.example.betmates.presentation.adapter.UserAdapter;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.Model;
import com.example.betmates.objects.content.User;

import java.util.ArrayList;

public class CreateBet extends AppCompatActivity {

    private Bet currentBet;

    private AccessBets betAccessor;
    private AccessUsers userAccessor;
    private AccessModel modelAccessor;

    private RecyclerView recyclerView;
    private ArrayList<User> friendList;
    private UserAdapter userAdapter;

    private FloatingActionButton addFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bet);

        betAccessor = new AccessBets();
        userAccessor = new AccessUsers();
        modelAccessor = new AccessModel();

        if(betAccessor.getCurrentBet()==null)
        {
            currentBet = new Bet(userAccessor.getFirstUser());
            friendList = new ArrayList<User>();
            betAccessor.setCurrentBet(currentBet);
        }
        else
        {
            currentBet = betAccessor.getCurrentBet();
            if(currentBet.getBetName()!=null)
            {
                ((TextView)findViewById(R.id.createBet_name)).setText(currentBet.getBetName());
            }
            if(currentBet.getBetDescription()!=null)
            {
                ((TextView)findViewById(R.id.createBet_description)).setText(currentBet.getBetDescription());
            }
            if(currentBet.getAmount()>0)
            {
                ((TextView)findViewById(R.id.createBet_amount)).setText(""+currentBet.getAmount());
            }
        }



        recyclerView = findViewById(R.id.createBet_addList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        addFriends = (FloatingActionButton) findViewById(R.id.FloatingAction_addFriend);
        addFriends.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                dataPasserToCurrentBet();
                betAccessor.setCurrentBet(currentBet);
                startActivity(new Intent(CreateBet.this,FriendSelector.class));
            }
        });

        friendList = new ArrayList<User>();

       userAdapter = new UserAdapter(currentBet.getJoiners());
       recyclerView.setAdapter(userAdapter);


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


    private void dataPasserToCurrentBet()
    {
        EditText name = findViewById(R.id.createBet_name);
        EditText description = findViewById(R.id.createBet_description);
        EditText amount = findViewById(R.id.createBet_amount);

        String betName = name.getText().toString();
        String betDescription = description.getText().toString();
        String amountString = amount.getText().toString();

        if(!betName.equals(""))
        {
            currentBet.setName(betName);
        }
        if(!betDescription.equals(""))
        {
            currentBet.setBetDescription(betDescription);
        }
        if(!amountString.equals(""))
        {
            currentBet.setAmount(Double.valueOf(amountString));
        };
    }

    private Bet createBetFromEditText()
    {
        EditText editID = findViewById(R.id.createBet_name);
        EditText editID2 = findViewById(R.id.createBet_description);
        EditText editID3 = findViewById(R.id.createBet_amount);
        String betName = editID.getText().toString();
        String betDescription = editID2.getText().toString();

        double betAmount = 0;

        if(!editID3.getText().toString().equals("")&&Double.valueOf(editID3.getText().toString())>=0) {
            betAmount = Double.valueOf(editID3.getText().toString());
        }else{
            Toast.makeText(this, "You did not enter a valid amount, set to 0.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CreateBet.this,CreateBet.class));

        }

        if (betName.equals("") || betDescription.equals("")) {
            Toast.makeText(this, "You did not enter a valid name or description", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CreateBet.this,CreateBet.class));
        }


        User creator = userAccessor.getFirstUser();
        return new Bet(betAmount,betDescription,betName,creator);
    }

    private Model createModelFromText(Bet newBet)
    {
        int id = modelAccessor.getNewid();
        int likes = 0;
        String hour = "current created";
        String name = newBet.getCreator().getName();
        String status = newBet.getBetDescription();
        Model newModel = new Model(id,likes,R.drawable.sample_profile_pic,R.drawable.sample_picture,name,hour,status,newBet);
        return newModel;
    }


    public void buttonBetCreateOnClick(View v)
    {
        currentBet = createBetFromEditText();
        if(currentBet.getCreator()!=null && !currentBet.getBetName().equals("") && !currentBet.getBetDescription().equals("")) {
            Model newModel = createModelFromText(currentBet);
            betAccessor.insertBet(currentBet);
            modelAccessor.insertModel(newModel);
            Toast.makeText(this, "Bet Created!.", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Error Creating the bet.", Toast.LENGTH_SHORT).show();
        }
        betAccessor.deleteCurrentBet();
        Intent mainIntent = new Intent(CreateBet.this, MainActivity.class);
        startActivity(mainIntent);
    }

}
