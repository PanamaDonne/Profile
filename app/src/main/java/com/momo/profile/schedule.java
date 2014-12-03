package com.momo.profile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import android.util.Log;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseUser;


public class schedule extends Activity {

    private String TAG;
    private ListView listView;
    public String date;
    public String day;
    private CalendarView calendarView;
    String currentDateTime;
    private TextView text;
    private ScheduleListAdapter scheduleAdapter;
    private ParseUser user;
    private String monthString;
    private String yearString;
    private Formatter fmt;
    private Formatter fmtDay;
    private String currentMonth;
    private String currentDay;
    Context context;
    private int rowPosition;
    private CharSequence periods;
    private String objectId;
    private ProgressDialog progress;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "calendarActivity";
        setContentView(com.momo.profile.R.layout.activity_schedule);
        calendarView = (CalendarView) findViewById(com.momo.profile.R.id.calendarView);
        listView = (ListView) findViewById(com.momo.profile.R.id.listView);


        context = getApplicationContext();


        progress = new ProgressDialog(schedule.this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait...");
        progress.show();

        long delayInMillis = 2000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                progress.dismiss();
            }
        }, delayInMillis);


        // ------------------------------------ Subclass of ParseQueryAdapter ------------------------
        scheduleAdapter = new ScheduleListAdapter(this);




        // ----------------------------------- INIT PARSE.COM ----------------------------------------
        ParseObject.registerSubclass(ParseStudent.class);
        parse();



        // CUSTOMIZE CALENDAR
        calendarView.setShowWeekNumber(Boolean.FALSE);




        fmt = new Formatter();
        final Calendar cal = Calendar.getInstance();


        // CURRENT MONTH
        fmt = new Formatter();
        fmt.format("%tB", cal);
        currentMonth = fmt.toString().toUpperCase();

        // CURRENT DAY
        fmtDay = new Formatter();
        fmtDay.format("%te", cal);
        currentDay = fmtDay.toString();




        currentDateTime = DateFormat.getDateTimeInstance().format(new Date());

        text = (TextView) findViewById(R.id.textView);





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View view,
                                    final int position, long id) {

                //GET CLASS INFO
                TextView studentNameTextField = (TextView) view.findViewById(R.id.text1);
                CharSequence studentName = studentNameTextField.getText();

                TextView teacherNameTextField = (TextView) view.findViewById(R.id.text2);
                CharSequence teacherName = teacherNameTextField.getText();

                TextView timeTextField = (TextView) view.findViewById(R.id.time);
                CharSequence time = timeTextField.getText();


                Intent intent = new Intent(schedule.this,ScheduleDetail.class);
                intent.putExtra("studentName", studentName);
                intent.putExtra("teacherName", teacherName);
                intent.putExtra("time", time);
                intent.putExtra("weekDay", Globals.dayOfMonth);
                intent.putExtra("date", date);
                startActivity(intent);

            }

        });



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {


                StringBuilder sb = new StringBuilder();
                sb.append(dayOfMonth);
                day = sb.toString();


                StringBuilder sb2 = new StringBuilder();
                sb2.append(year);
                yearString = sb2.toString();



                switch (month) {

                       case 0:  {

                        monthString = "JANUARY";


                    }
                    break;
                    case 1:  {

                        monthString = "FEBRUARY";

                    }
                    break;
                    case 2:  {

                        monthString = "MARCH";

                    }
                    break;
                    case 3:  {

                        monthString = "APRIL";

                    }
                    break;
                    case 4:  {

                        monthString = "MAY";

                    }
                    break;
                    case 5:  {

                        monthString = "JUNE";

                    }
                    break;
                    case 6:  {

                        monthString = "JULY";

                    }
                    break;
                    case 7:  {

                        monthString = "AUGUST";

                    }
                    break;
                    case 8:  {

                        monthString = "SEPTEMBER";

                    }
                    break;
                    case 9: {

                        monthString = "OCTOBER";

                    }
                    break;
                    case 10: {

                        monthString = "NOVEMBER";

                    }
                    break;
                    case 11: {

                        monthString = "DECEMBER";

                    }
                    break;




                }

                date = day + " " + monthString + " " + yearString;
                Log.i(TAG, "DATE: " + date);
                text.setText(date);


                // Set weekday
                GregorianCalendar cal = new GregorianCalendar(year, month, dayOfMonth);
                int weekDayInt = cal.get(Calendar.DAY_OF_WEEK);


                Log.i(TAG, "DAY OF MONTH: " + dayOfMonth);

               Globals.dayOfMonth = dayOfMonth;

                parseClasses();









            }


        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        user = ParseUser.getCurrentUser();
    }

    // Init Parse
    void parse() {
        Parse.initialize(this, "fpHnJtvttEKiKs2FmZ6UPMrVxPjD4KMdnRj3jgIi", "w3KrbARBlNEJg0gNhD0FwiJBvcoDokDloLYQMtDz");

    }



    // ------------------------------------  PARSE CLASSES ----------------------------
    void parseClasses() {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("student");

        progress.show();
        query.whereEqualTo("classDate", Globals.dayOfMonth);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {


                    Log.i(TAG, "FOUND: " + classList);

                    listView.setAdapter(scheduleAdapter);

                    progress.dismiss();

                } else if(classList.size() == 0) {

                    scheduleAdapter.clear();
                    progress.dismiss();

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }



}



