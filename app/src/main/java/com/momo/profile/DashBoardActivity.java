package com.momo.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;
import com.parse.PushService;


public class DashBoardActivity extends Activity {


    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.momo.profile.R.layout.activity_dashboard);

        checkLoggedIn();

        button1=(Button)findViewById(com.momo.profile.R.id.button1);
        button1.setTextColor(Color.BLACK);

        /*if (ParseUser.getCurrentUser().getUsername().equals("admin")) {
            button1.setText("Agendamentos");
        }*/


        button2=(Button)findViewById(com.momo.profile.R.id.button2);
        button2.setTextColor(Color.BLACK);


        button3=(Button)findViewById(com.momo.profile.R.id.button3);
        button3.setTextColor(Color.BLACK);

        button4=(Button)findViewById(com.momo.profile.R.id.button4);





        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,schedule.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,schedule.class);
                startActivity(intent);
            }
        });



        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });

        button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Logout current user
                ParseUser.logOut();
                Intent intent = new Intent(DashBoardActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });




    }



    void checkLoggedIn() {

        // Add your initialization code here
        Parse.initialize(this, "fpHnJtvttEKiKs2FmZ6UPMrVxPjD4KMdnRj3jgIi", "w3KrbARBlNEJg0gNhD0FwiJBvcoDokDloLYQMtDz");

        PushService.setDefaultPushCallback(this, DashBoardActivity.class);
        ParseAnalytics.trackAppOpened(getIntent());



        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);

        defaultACL.setWriteAccess("xqmFxOtuxY", true);

        ParseACL.setDefaultACL(defaultACL, true);
        // Determine whether the current user is an anonymous user
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            // If user is anonymous, send the user to LoginSignupActivity.class
            Intent intent = new Intent(DashBoardActivity.this,
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
                Intent intent = new Intent(DashBoardActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }



}
