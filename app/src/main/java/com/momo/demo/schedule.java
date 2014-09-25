package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.TimeZone;

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
import com.parse.SaveCallback;


public class schedule extends Activity {

    private String TAG;
    private ListView listView;
    public String date;
    public String day;
    private CalendarView calendarView;
    String currentDateTime;
    private TextView text;
    private PeriodListAdapter periodAdapter;
    private ParseUser user;
    private String monthString;
    private String currentDate;
    private String yearString;
    private Formatter fmt;
    private Formatter fmtDay;
    private String currentMonth;
    private String currentDay;
    Context context;
    private int rowPosition;
    private CharSequence periods;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "calendarActivity";
        setContentView(R.layout.activity_schedule);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        listView = (ListView) findViewById(R.id.listView);

        context = getApplicationContext();


        // ------------------------------------ Subclass of ParseQueryAdapter ------------------------
        periodAdapter = new PeriodListAdapter(this);


        // ----------------------------------- INIT PARSE.COM ----------------------------------------
        ParseObject.registerSubclass(Period.class);
        parse();


        listView.setAdapter(periodAdapter);


        fmt = new Formatter();
        Calendar cal = Calendar.getInstance();

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


        text = (TextView) findViewById(R.id.textView);
        text.setText(currentDate);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View view,
                                    final int position, long id) {


                Log.i(TAG, "MONTH: " + monthString + " = " + currentMonth);

                rowPosition = position;

                view.setSelected(true);

                TextView textViewPeriods = (TextView) view.findViewById(R.id.text1);
                periods = textViewPeriods.getText();


                // -------------------- BOOK & CHECK IF BOOKED BY ANYONE ---------------------------
                final ParseQuery<ParseObject> query = ParseQuery.getQuery("bookings_tennis");

                query.whereEqualTo("date", date);
                query.whereEqualTo("periods", periods);


                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(final List<ParseObject> bookedList, ParseException e) {
                        if (e == null) {
                            Log.i(TAG, "FOUND: " + bookedList.size());



                            // ---------------------------- CHECK IF BOOKED ------------------------------------------------
                            if (((bookedList.size() > 0))) {

                                objectId = bookedList.get(0).getObjectId();

                                AlertDialog.Builder builder1 = new AlertDialog.Builder(schedule.this);
                                builder1.setMessage("Este horário já foi agendado por outro usuário. Você quer entrar na ‘Lista de Espera’ para este horário?");



                                // SET STANDBY USER
                                builder1.setPositiveButton("SIM",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                ParseQuery<ParseObject> query = ParseQuery.getQuery("bookings_tennis");


                                                query.getInBackground(objectId, new GetCallback<ParseObject>() {
                                                    public void done(ParseObject bookings, ParseException e) {
                                                        if (e == null) {

                                                            bookings.put("standby1", user);
                                                            bookings.put("standby1username", user.getUsername());
                                                            bookings.saveInBackground();
                                                        }
                                                    }
                                                });



                                            }
                                        });
                                builder1.setCancelable(true);
                                builder1.setNegativeButton("CANCELAR",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }

                            // ------------------------- ADMIN USER & APTO USERS WITH RIGHT CRITERIA--------------------------------------------------
                            else {

                                bookPeriod();

                            }

                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });



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

                        monthString = "NOVEMRBO";

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

                // CHECK BOOKING CRITERIA
                checkBooking();
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
        Parse.initialize(this, "fNj6swlEg1d5Rn4rO8jBPwJ6BlAbDN0A2GJbYnTB", "6Ua0deolkpYrnWagJRZcoRulDI2BHbLFccXzW85E");

    }


    // CHECK IF USER BOOKED THE DATE & IF ALLOWED TO BOOK THE PERIOD
    void checkBooking() {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("bookings_tennis");

        query.whereEqualTo("date", date);
        query.whereEqualTo("bookedBy", user);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> bookedList, ParseException e) {
                if (e == null) {
                    Log.i(TAG, "FOUND: " + bookedList.size());

                    // ---------------------------- CHECK IF USER ALREADY BOOKED - ADMIN CAN BOOK ------------------------------------------------
                    if (((bookedList.size() > 0) && (!user.getUsername().equals("admin")))) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(schedule.this);
                        builder1.setMessage("You have already booked on this date.");

                        builder1.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                        builder1.setCancelable(false);

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }

                    // APTO USERS BOOKING PERIOD 1month - 5 days - ADMIN CAN BOOK
                    else if ((!user.getUsername().equals("admin")) && (!currentMonth.equals(monthString))) {

                        if (currentDay.equals("24") || currentDay.equals("25") || currentDay.equals("26") || currentDay.equals("27") || currentDay.equals("28") || currentDay.equals("29") || currentDay.equals("30") || currentDay.equals("31")) {

                            Log.i(TAG, "YOU CAN BOOK!");


                        } else {

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(schedule.this);
                            builder1.setMessage("You cannot book this period.");

                            builder1.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                        }
                                    });
                            builder1.setCancelable(false);

                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }


                    }
                    else {
                        //
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }


    void bookPeriod () {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(schedule.this);
        builder1.setMessage("Você quer agendar este horario?");

        builder1.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        ParseObject bookings = ParseObject.create("bookings_tennis");



                        switch (rowPosition) {


                            case 0:  {

                                bookings.put("date", date);
                                bookings.put("periods", "07:00 - 08:00");
                                bookings.put("position", 0);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();


                            }
                            break;
                            case 1:  {

                                bookings.put("date", date);
                                bookings.put("periods", "08:00 - 09:00" );
                                bookings.put("position", 1);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 2:  {

                                bookings.put("date", date);
                                bookings.put("periods", "09:00 - 10:00" );
                                bookings.put("position", 2);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 3:  {

                                bookings.put("date", date);
                                bookings.put("periods", "10:00 - 11:00" );
                                bookings.put("position", 3);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 4:  {

                                bookings.put("date", date);
                                bookings.put("periods", "11:00 - 12:00" );
                                bookings.put("position", 4);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 5:  {

                                bookings.put("date", date);
                                bookings.put("periods", "12:00 - 13:00" );
                                bookings.put("position", 5);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 6:  {

                                bookings.put("date", date);
                                bookings.put("periods", "13:00 - 14:00" );
                                bookings.put("position", 6);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 7:  {

                                bookings.put("date", date);
                                bookings.put("periods", "14:00 - 15:00" );
                                bookings.put("position", 7);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 8:  {

                                bookings.put("date", date);
                                bookings.put("periods", "15:00 - 16:00" );
                                bookings.put("position", 8);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 9: {

                                bookings.put("date", date);
                                bookings.put("periods", "16:00 - 17:00" );
                                bookings.put("position", 9);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 10: {

                                bookings.put("date", date);
                                bookings.put("periods", "17:00 - 18:00" );
                                bookings.put("position", 10);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 11: {

                                bookings.put("date", date);
                                bookings.put("periods", "18:00 - 19:00" );
                                bookings.put("position", 11);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 12: {

                                bookings.put("date", date);
                                bookings.put("periods", "19:00 - 20:00" );
                                bookings.put("booked", true);
                                bookings.put("position", 12);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 13: {

                                bookings.put("date", date);
                                bookings.put("periods", "20:00 - 21:00" );
                                bookings.put("position", 13);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;
                            case 14: {

                                bookings.put("date", date);
                                bookings.put("periods", "21:00 - 22:00" );
                                bookings.put("position", 14);
                                bookings.put("booked", true);
                                bookings.put("bookedBy", ParseUser.getCurrentUser());
                                bookings.put("username", ParseUser.getCurrentUser().getUsername());
                                bookings.saveInBackground();

                            }
                            break;

                        }







                        Toast.makeText(getApplicationContext(), "Horario marcada! Você receberá uma notificação duas horas antes da sua atividade.",
                                Toast.LENGTH_LONG).show();

                    }
                });
        builder1.setCancelable(true);
        builder1.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }



}



