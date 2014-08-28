package com.momo.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class StartActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        startTimer();

    }



    /*
     ** Set timer to splash
     */
    void startTimer() {
        Thread timer=new Thread()
        {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally
                {
                    Intent i=new Intent(StartActivity.this,CondoActivity.class);
                    finish();
                    startActivity(i);
                }
            }
        };
        timer.start();
    }


}
