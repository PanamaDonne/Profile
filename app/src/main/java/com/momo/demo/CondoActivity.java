package com.momo.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;


public class CondoActivity extends Activity {




    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condo);

        checkLoggedIn();


        button1=(Button)findViewById(R.id.button1);
        button1.setTextColor(Color.WHITE);


        button2=(Button)findViewById(R.id.button2);
        button2.setTextColor(Color.WHITE);


        button3=(Button)findViewById(R.id.button3);
        button3.setTextColor(Color.WHITE);

        button4=(Button)findViewById(R.id.button4);
        button4.setTextColor(Color.WHITE);


        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CondoActivity.this,agenda.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CondoActivity.this,schedule.class);
                startActivity(intent);
            }
        });



        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Logout current user
                ParseUser.logOut();
                Intent intent = new Intent(CondoActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        /*button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CondoActivity.this,sindico.class);
                startActivity(intent);
            }
        });*/

    }



    void checkLoggedIn() {

        // Add your initialization code here
        Parse.initialize(this, "fNj6swlEg1d5Rn4rO8jBPwJ6BlAbDN0A2GJbYnTB", "6Ua0deolkpYrnWagJRZcoRulDI2BHbLFccXzW85E");


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
        // Determine whether the current user is an anonymous user
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            // If user is anonymous, send the user to LoginSignupActivity.class
            Intent intent = new Intent(CondoActivity.this,
                    LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // If current user is NOT anonymous user
            // Get current user data from Parse.com
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                // Do nothing

            } else {
                // Send user to LoginSignupActivity.class
                Intent intent = new Intent(CondoActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }


}
