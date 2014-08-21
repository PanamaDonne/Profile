package com.momo.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;


public class myCondo extends Activity {

    private Button buttonMap;
    private Button buttonService;
    private Button buttonTel;
    private Button buttonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_condo);

        buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonMap.setTextColor(Color.WHITE);
        buttonMap.setTextSize(20.0f);

        buttonService = (Button) findViewById(R.id.buttonService);
        buttonService.setTextColor(Color.WHITE);
        buttonService.setTextSize(20.0f);

        buttonTel = (Button) findViewById(R.id.buttonTel);
        buttonTel.setTextColor(Color.WHITE);
        buttonTel.setTextSize(20.0f);

        buttonInfo = (Button) findViewById(R.id.buttonInfo);
        buttonInfo.setTextColor(Color.WHITE);
        buttonInfo.setTextSize(20.0f);
    }
}
