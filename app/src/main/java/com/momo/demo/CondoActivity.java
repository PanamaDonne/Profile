package com.momo.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class CondoActivity extends Activity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condo);

        button1=(Button)findViewById(R.id.button1);
        button1.setText("SCHEDULE");

        button2=(Button)findViewById(R.id.button2);
        button2.setText("CLASSES");

        button3=(Button)findViewById(R.id.button3);
        button3.setText("NEWS");

        button4=(Button)findViewById(R.id.button4);
        button4.setText("REPORT");
    }


}
