package com.momo.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseRole;
import com.parse.ParseUser;


public class LoginActivity extends Activity {
    // Declare Variables
    Button loginbutton;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;
    private String TAG;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = "loginActivity";
        // Get the view from main.xml
        setContentView(com.momo.profile.R.layout.activity_login);
        // Locate EditTexts in main.xml
        username = (EditText) findViewById(com.momo.profile.R.id.username);
        password = (EditText) findViewById(com.momo.profile.R.id.password);

        // Locate Buttons in main.xml
        loginbutton = (Button) findViewById(com.momo.profile.R.id.login);


        // Login Button Click Listener
        loginbutton.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();

                // Send data to Parse.com for verification
                ParseUser.logInInBackground(usernametxt, passwordtxt,
                        new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {


                                if (user != null) {


                                    if (user.getUsername().equals("altair")) {

                                        ParseACL roleACL = new ParseACL();
                                        roleACL.setPublicReadAccess(true);
                                        roleACL.setPublicWriteAccess(true);
                                        ParseRole role = new ParseRole("Administrator", roleACL);
                                        role.saveInBackground();

                                        Log.i(TAG,"ADMIN LOGGED IN");

                                    }



                                    Intent intent = new Intent(
                                            LoginActivity.this,
                                            DashBoardActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Logged in",
                                            Toast.LENGTH_LONG).show();
                                    finish();

                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "No such user exist. Please try again.",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });



    }
}