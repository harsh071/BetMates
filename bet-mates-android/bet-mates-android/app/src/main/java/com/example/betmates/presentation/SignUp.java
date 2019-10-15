package com.example.betmates.presentation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.betmates.R;
import com.example.betmates.business.Authenticate;
import com.example.betmates.business.SignUpInputVeryfier;
import com.example.betmates.objects.content.User;

public class SignUp extends AppCompatActivity {
    private EditText userName;             // firstNLastName of user
    private EditText password;          // password of user
    private EditText age;              // name of user
    private EditText confirmPassword;   // confirm password of user
    private Authenticate authenticate;
    private ProgressDialog progressDialog;  // progress Dialogue



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        authenticate = new Authenticate();

        userName = findViewById(R.id.enterName );
        age = findViewById(R.id.enterAge);
        password = findViewById(R.id.enterPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
    }

    public void signUpButton(View view){
        String userName = this.userName.getText().toString();
        String password = this.password.getText().toString();
        String age = this.age.getText().toString();
        String confirmPassword = this.confirmPassword.getText().toString();
        SignUpInputVeryfier inputVeryfier =  new SignUpInputVeryfier(userName,password,age,confirmPassword);
        boolean empty = inputVeryfier.isInputEmpty();

        if(empty){
            Toast.makeText(getApplicationContext(), "One of the fields is empty.",Toast.LENGTH_SHORT).show();
        }else{
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Signing Up");
            progressDialog.show();
            User signUpUser = authenticate.authenticator(userName,password,age,confirmPassword);
            progressDialog.dismiss();
            if(signUpUser!= null){
                Toast.makeText(getApplicationContext(), "You have been Signed up! You can now login.", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }else{ //unsuccessful sign up
                if(authenticate.userExists(userName)) {
                    Toast.makeText(getApplicationContext(), "UserName already exists!", Toast.LENGTH_SHORT).show();
                } else if(!authenticate.verifyAge(age)) {
                    Toast.makeText(getApplicationContext(), "You have to be at least 18 years older!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid username and password.", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    public void login(View view){
        //Link to Login.
        startActivity(new Intent(SignUp.this, Login.class));
    }


}
