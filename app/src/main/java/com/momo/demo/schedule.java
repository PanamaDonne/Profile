package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class schedule extends Activity {

    private String TAG;
    private ListView listView;
    ParseQuery<ParseObject> query;
    public String date;
    public ArrayList <String> list;
    ParseQueryAdapter adapter;
    private Boolean booked;

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "calendarActivity";
        setContentView(R.layout.activity_schedule);
        calendarView = (CalendarView) findViewById(R.id.calendarView);

        // INIT PARSE.COM
        parse();

        adapter = new ParseQueryAdapter(this, "booking_tennis");
        adapter.setTextKey("periods");

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                final String ObjectId = adapter.getItem(position).getObjectId();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(schedule.this);
                builder1.setMessage("Você quer agendar este horario?");

                builder1.setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ParseQuery<ParseObject> query = ParseQuery.getQuery("booking_tennis");


                                query.getInBackground(ObjectId, new GetCallback<ParseObject>() {
                                    public void done(ParseObject booking_tennis, ParseException e) {
                                        if (e == null) {

                                            booking_tennis.put("booked", true);
                                            booking_tennis.saveInBackground();
                                            booked = true;


                                        }
                                    }
                                });

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


        });







        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                StringBuilder sb = new StringBuilder();
                sb.append(dayOfMonth);
                date = sb.toString();
                Log.i(TAG, "DATE: " + date);



            }
        });





    }


    void parse() {
        Parse.initialize(this, "fNj6swlEg1d5Rn4rO8jBPwJ6BlAbDN0A2GJbYnTB", "6Ua0deolkpYrnWagJRZcoRulDI2BHbLFccXzW85E");

    }


    void dialog () {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(schedule.this);
        builder1.setMessage("Você quer agendar este horario?");

        builder1.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

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


    void bookPeriod() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("booking_tennis");

        // Retrieve the object by id
        query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
            public void done(ParseObject gameScore, ParseException e) {
                if (e == null) {
                    // Now let's update it with some new data. In this case, only cheatMode and score
                    // will get sent to the Parse Cloud. playerName hasn't changed.
                    String objectId = gameScore.getObjectId();
                    gameScore.put("score", 1338);
                    gameScore.put("cheatMode", true);
                    gameScore.saveInBackground();
                }
            }
        });

    }



}



