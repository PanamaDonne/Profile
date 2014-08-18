package com.momo.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;


public class schedule extends Activity {

    private ListView listView;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        listView = (ListView) findViewById(R.id.listView);




        calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                if (dayOfMonth == 23) {
                    listView.setAdapter(new ArrayAdapter<String>(schedule.this,
                            android.R.layout.simple_list_item_1,
                            new String[]{"08:30 MASSAGE"}));
                }
                else if (dayOfMonth ==24) {
                    listView.setAdapter(new ArrayAdapter<String>(schedule.this,
                            android.R.layout.simple_list_item_1,
                            new String[]{"17:30 SAUNA"}));
                }
                else {
                    listView.setAdapter(new ArrayAdapter<String>(schedule.this,
                            android.R.layout.simple_list_item_1,
                            new String[]{"07:15 SQUASH"}));
                }





            }
        });



    }

}
