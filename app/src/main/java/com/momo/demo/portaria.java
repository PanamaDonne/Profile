package com.momo.demo;

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
        setContentView(R.layout.activity_portaria);

        buttonDelivery = (Button) findViewById(R.id.buttonDelivery);
        buttonDelivery.setTextColor(Color.WHITE);
        buttonDelivery.setTextSize(30.0f);

        buttonVisitor = (Button) findViewById(R.id.buttonVisitor);
        buttonVisitor.setTextColor(Color.WHITE);
        buttonVisitor.setTextSize(30.0f);

        buttonQuestions = (Button) findViewById(R.id.buttonQuestions);
        buttonQuestions.setTextColor(Color.WHITE);
        buttonQuestions.setTextSize(30.0f);
    }


}
