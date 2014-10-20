package com.momo.profile;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;


public class portaria extends Activity {

    private Button buttonDelivery;
    private Button buttonVisitor;
    private Button buttonQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.momo.profile.R.layout.activity_portaria);

        buttonDelivery = (Button) findViewById(com.momo.profile.R.id.buttonDelivery);
        buttonDelivery.setTextColor(Color.WHITE);
        buttonDelivery.setTextSize(30.0f);

        buttonVisitor = (Button) findViewById(com.momo.profile.R.id.buttonVisitor);
        buttonVisitor.setTextColor(Color.WHITE);
        buttonVisitor.setTextSize(30.0f);

        buttonQuestions = (Button) findViewById(com.momo.profile.R.id.buttonQuestions);
        buttonQuestions.setTextColor(Color.WHITE);
        buttonQuestions.setTextSize(30.0f);
    }


}
