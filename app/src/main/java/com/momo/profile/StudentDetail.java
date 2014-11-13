package com.momo.profile;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class StudentDetail extends Activity {

    private TextView student;
    private TextView day1;
    private TextView day2;
    private TextView day3;
    private TextView time1;
    private TextView time2;
    private TextView time3;
    private String weekDay;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_student_detail);

        student = (TextView)findViewById(R.id.studentName);
        student.setText((getIntent().getStringExtra("studentName")));

        day1 = (TextView)findViewById(R.id.day1);
        day2 = (TextView)findViewById(R.id.day2);
        day3 = (TextView)findViewById(R.id.day3);

        time1 = (TextView)findViewById(R.id.time1);
        time2 = (TextView)findViewById(R.id.time2);
        time3 = (TextView)findViewById(R.id.time3);


        parseClasses();



        TAG = "StudentDetail";


    }



    void parseClasses() {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("student");

        String studentString = student.getText().toString();

        query.whereEqualTo("name", studentString);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {

                    day1.setText(classList.get(0).getString("weekDay").toUpperCase());
                    time1.setText(classList.get(0).getString("time"));

                    if(classList.size() > 1) {
                        day2.setText(classList.get(1).getString("weekDay").toUpperCase());
                        time2.setText(classList.get(1).getString("time"));
                    }

                    if(classList.size() > 2) {
                        day3.setText(classList.get(2).getString("weekDay").toUpperCase());
                        time3.setText(classList.get(2).getString("time"));
                    }


                }else {

                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }
}
