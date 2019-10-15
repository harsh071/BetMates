package com.example.betmates.presentation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.betmates.R;
import com.example.betmates.application.DBConnectionManager;
import com.example.betmates.application.MainActivity;
import com.example.betmates.business.Authenticate;
import com.example.betmates.objects.content.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {


    private EditText userName;             // the edit text box for userName of the user
    private EditText password;          // the edit text box for password of the user
    private ProgressDialog progressDialog;  // progress Dialogue
    private Authenticate authenticate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        copyDatabaseToDevice(); // Copy the database


        authenticate = new Authenticate();
        userName = findViewById(R.id.userNameText);
        password = findViewById(R.id.passwordText);
    }

    private void copyDatabaseToDevice(){
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try
        {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++)
            {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(context, assetNames, dataDirectory);
            DBConnectionManager.setDBPathName(dataDirectory.toString() + "/" + DBConnectionManager.getDBPathName());

        }
        catch (final IOException ioe)
        {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }

    }

    public void copyAssetsToDirectory(Context context, String[] assets, File directory) throws IOException
    {
        AssetManager assetManager = context.getAssets();

        for (String asset : assets)
        {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists())
            {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1)
                {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    public void loginButtonClick(View view) {

        String userName = this.userName.getText().toString();
        String userPassword = this.password.getText().toString();

        /*
         if userName or password field is empty
        */
        if (userName.isEmpty() || userPassword.isEmpty()) {
            //error message.
            Toast.makeText(getApplicationContext(), "One Of the above field is empty. ", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Signing In");
            progressDialog.show();

            User checkUser = authenticate.authenticator(userName, userPassword);
            progressDialog.dismiss();
            if (checkUser != null) {
                //login was successful
                Toast.makeText(getApplicationContext(), "You have been logged in!", Toast.LENGTH_SHORT).show();
                // login and go to homepage
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            else    //unsuccessful login.
            {

                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void signUp(View view){
        //Link to SignUp
        startActivity(new Intent(Login.this, SignUp.class));
    }

}
