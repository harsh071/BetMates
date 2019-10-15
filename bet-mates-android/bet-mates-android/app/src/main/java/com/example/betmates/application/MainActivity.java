package com.example.betmates.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.betmates.business.AccessModel;
import com.example.betmates.presentation.adapter.ModelAdapter;
import com.example.betmates.objects.content.Model;
import com.example.betmates.R;
import com.example.betmates.presentation.BetHistory;
import com.example.betmates.presentation.CreateBet;
import com.example.betmates.presentation.CurrentBets;
import com.example.betmates.presentation.Login;
import com.example.betmates.presentation.UserProfile;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Model> modelArrayList = new ArrayList<>();
    ModelAdapter modelAdapter;
    AccessModel accessModels;

    private static String dbName = "UsersAndBets"; // Name of the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accessModels = new AccessModel();
        modelArrayList = accessModels.getModels();

        recyclerView = findViewById(R.id.feed);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        modelAdapter = new ModelAdapter(this, modelArrayList);
        recyclerView.setAdapter(modelAdapter);

        modelAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_directory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mItem){
        switch(mItem.getItemId()){
            case R.id.profile:
                startActivity(new Intent(MainActivity.this, UserProfile.class ));
                return true;
            case R.id.currBets:
                startActivity(new Intent(MainActivity.this, CurrentBets.class));
                return true;
            case R.id.betHist:
                startActivity(new Intent(MainActivity.this, BetHistory.class));
                return true;
            case R.id.logOut:
                startActivity(new Intent(MainActivity.this, Login.class));
                return true;

        }
        return super.onOptionsItemSelected(mItem);
    }

    public void viewHistory(View view){
        startActivity(new Intent(MainActivity.this, BetHistory.class));
    }

    public void viewCurrentBets(View view){
        startActivity(new Intent(MainActivity.this, CurrentBets.class));
    }

    public void viewProfile(View view){
        startActivity(new Intent(MainActivity.this, UserProfile.class ));
    }

    public void createBet(View view){
        startActivity(new Intent(MainActivity.this, CreateBet.class ));

    }

    //--------------------------------
    // OnLike
    //
    // PURPOSE: To Like a post.
    //--------------------------------
    public void onLike(View view){
        Toast.makeText(this, "You have liked the post.", Toast.LENGTH_SHORT).show();
    }
}
