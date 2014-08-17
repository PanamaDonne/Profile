package com.momo.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class report extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        tv = (TextView) findViewById(R.id.textView);

        tv.setTextColor(Color.BLACK);
        tv.setTextSize(20.0f);
    }


}
