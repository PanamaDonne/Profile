package com.momo.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class classes extends Activity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.momo.profile.R.layout.activity_classes);

        button1=(Button)findViewById(com.momo.profile.R.id.button1);
        button1.setTextColor(Color.WHITE);
        button1.setTextSize(30.0f);

        button2=(Button)findViewById(com.momo.profile.R.id.button2);
        button2.setTextColor(Color.WHITE);
        button2.setTextSize(30.0f);

        button3=(Button)findViewById(com.momo.profile.R.id.button3);
        button3.setTextColor(Color.WHITE);
        button3.setTextSize(30.0f);

        button4=(Button)findViewById(com.momo.profile.R.id.button4);
        button4.setTextColor(Color.WHITE);
        button4.setTextSize(30.0f);

        button5=(Button)findViewById(com.momo.profile.R.id.button5);
        button5.setTextColor(Color.WHITE);
        button5.setTextSize(30.0f);





        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classes.this,sauna.class);
                startActivity(intent);
            }
        });
    }
}
