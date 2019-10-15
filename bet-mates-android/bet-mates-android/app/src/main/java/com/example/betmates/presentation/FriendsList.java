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

import com.example.betmates.application.MainActivity;
import com.example.betmates.R;
import com.example.betmates.business.AccessUsers;
import com.example.betmates.presentation.adapter.UserAdapter;
import com.example.betmates.objects.content.User;

public class FriendsList  extends AppCompatActivity {

    AccessUsers userAccessor;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        userAccessor = new AccessUsers();
        currentUser = userAccessor.getFirstUser();

        RecyclerView userFriends = findViewById(R.id.listFriends);
        userFriends.setLayoutManager(new LinearLayoutManager(this));

        userFriends.setAdapter(new UserAdapter(userAccessor.getUserFriends()));

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

    public void betMatesClick(View view){ // Connect the header back to the main screen
        startActivity(new Intent(FriendsList.this, MainActivity.class));
    }
}
