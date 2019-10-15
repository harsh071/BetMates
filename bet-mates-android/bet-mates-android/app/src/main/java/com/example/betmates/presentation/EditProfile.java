package com.example.betmates.presentation;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.betmates.R;
import com.example.betmates.application.MainActivity;
import com.example.betmates.business.AccessUsers;
import com.example.betmates.objects.content.User;

public class EditProfile extends AppCompatActivity {
    private User currUser;
    private EditText userName;
    private AccessUsers accUsers = new AccessUsers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        userName = findViewById(R.id.changeName);
        currUser = accUsers.getFirstUser();

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

    public boolean setInfo(){
        boolean infoSet = false;
        String newUserName = userName.getText().toString();
        if(!newUserName.equals("")) {
            currUser.setName(newUserName);
            accUsers.setUserLoggedIn(currUser);
            Toast.makeText(this, "Name changed!", Toast.LENGTH_SHORT).show();
            infoSet = true;

        }else{
            Toast.makeText(this, "You have not entered any name.", Toast.LENGTH_SHORT).show();

        }
        return infoSet;
    }

    public void homeClick (View view){
        if(setInfo()){
            startActivity(new Intent(EditProfile.this, UserProfile.class));
        }
    }
}
