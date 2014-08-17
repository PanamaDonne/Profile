package com.momo.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


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

        setContentView(R.layout.activity_classes);

        button1=(Button)findViewById(R.id.button1);
        button1.setText("SAUNA");
        button1.setTextColor(Color.WHITE);
        button1.setTextSize(50.0f);

        button2=(Button)findViewById(R.id.button2);
        button2.setText("MASSAGE");
        button2.setTextColor(Color.WHITE);
        button2.setTextSize(50.0f);

        button3=(Button)findViewById(R.id.button3);
        button3.setText("YOGA");
        button3.setTextColor(Color.WHITE);
        button3.setTextSize(50.0f);

        button4=(Button)findViewById(R.id.button4);
        button4.setText("TENNIS");
        button4.setTextColor(Color.WHITE);
        button4.setTextSize(50.0f);

        button5=(Button)findViewById(R.id.button5);
        button5.setText("SWIMMING");
        button5.setTextColor(Color.WHITE);
        button5.setTextSize(50.0f);

        button6=(Button)findViewById(R.id.button6);
        button6.setText("SQUASH");
        button6.setTextColor(Color.WHITE);
        button6.setTextSize(50.0f);




        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classes.this,sauna.class);
                startActivity(intent);
            }
        });
    }
}
