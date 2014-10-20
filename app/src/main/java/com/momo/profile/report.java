package com.momo.profile;

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
        setContentView(com.momo.profile.R.layout.activity_report);

        tv = (TextView) findViewById(com.momo.profile.R.id.textView);

        tv.setTextColor(Color.BLACK);
        tv.setTextSize(20.0f);
    }


}
