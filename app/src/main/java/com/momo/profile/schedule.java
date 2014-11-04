package com.momo.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


public class schedule extends Activity {

    private String TAG;
    private ListView listView;
    public String date;
    public String day;
    private CalendarView calendarView;
    String currentDateTime;
    private TextView text;
    private StudentListAdapter studentAdapter;
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
        progress.setTitle("Carregando");
        progress.setMessage("Por favor aguarde...");
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
        studentAdapter = new StudentListAdapter(this);




        // ----------------------------------- INIT PARSE.COM ----------------------------------------
        ParseObject.registerSubclass(Student.class);
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

        Log.i(TAG, "TIME STAMP: " + currentDay);


        currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
        date = currentDay + " de " + currentMonth + " 2014";

        text = (TextView) findViewById(com.momo.profile.R.id.textView);
        text.setText(date);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View view,
                                    final int position, long id) {

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

                        monthString = "JANEIRO";


                    }
                    break;
                    case 1:  {

                        monthString = "FEVEREIRO";

                    }
                    break;
                    case 2:  {

                        monthString = "MARCO";

                    }
                    break;
                    case 3:  {

                        monthString = "ABRIL";

                    }
                    break;
                    case 4:  {

                        monthString = "MAIO";

                    }
                    break;
                    case 5:  {

                        monthString = "JUNHO";

                    }
                    break;
                    case 6:  {

                        monthString = "JULHO";

                    }
                    break;
                    case 7:  {

                        monthString = "AGOSTO";

                    }
                    break;
                    case 8:  {

                        monthString = "SETEMBRO";

                    }
                    break;
                    case 9: {

                        monthString = "OUTUBRO";

                    }
                    break;
                    case 10: {

                        monthString = "NOVEMBRO";

                    }
                    break;
                    case 11: {

                        monthString = "DEZEMBRO";

                    }
                    break;




                }

                date = day + " de " + monthString + " " + yearString;
                Log.i(TAG, "DATE: " + date);
                text.setText(date);


                // Set weekday
                GregorianCalendar cal = new GregorianCalendar(year, month, dayOfMonth);
                int weekDayInt = cal.get(Calendar.DAY_OF_WEEK);

                switch(weekDayInt){
                    case 1: Globals.weekDay = "Sunday";
                        break;
                    case 2: Globals.weekDay = "Monday";
                        break;
                    case 3: Globals.weekDay = "Tuesday";
                        break;
                    case 4: Globals.weekDay = "Wednesday";
                        break;
                    case 5: Globals.weekDay = "Thursday";
                        break;
                    case 6: Globals.weekDay = "Friday";
                        break;
                    case 7: Globals.weekDay = "Saturday";
                        break;
                }

                parseClasses();


                Log.i(TAG, "WEEKDAY: " + Globals.weekDay);


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
        query.whereEqualTo("weekDay", Globals.weekDay);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {


                    Log.i(TAG, "FOUND: " + classList);

                    listView.setAdapter(studentAdapter);

                    progress.dismiss();

                } else if(classList.size() == 0) {

                    studentAdapter.clear();
                    progress.dismiss();

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }



}



