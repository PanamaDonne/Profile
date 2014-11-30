package com.momo.profile;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ScheduleDetail extends Activity {


    private TextView student;
    private TextView teacher;
    private TextView time;
    private TextView date;
    private TextView weekDay;
    private String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule_detail);

        student = (TextView)findViewById(R.id.studentName);
        student.setText((getIntent().getStringExtra("studentName")));

        teacher = (TextView)findViewById(R.id.teacherName);
        teacher.setText((getIntent().getStringExtra("teacherName")));

        time = (TextView)findViewById(R.id.time);
        time.setText((getIntent().getStringExtra("time")));


        date = (TextView)findViewById(R.id.date);
        date.setText((getIntent().getStringExtra("date")));

        weekDay = (TextView)findViewById(R.id.weekDay);
        weekDay.setText((getIntent().getStringExtra("weekDay")));


        TAG = "ScheduleDetail";


        }


}
