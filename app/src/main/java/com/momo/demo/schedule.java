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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import android.widget.TextView;
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
    public String date;
    private Boolean booked;
    private String period;
    private String ObjectId;
    private CalendarView calendarView;
    String currentDateTime;
    private TextView text;
    ParseQueryAdapter.QueryFactory<ParseObject> factory;
    ParseQueryAdapter<ParseObject> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "calendarActivity";
        setContentView(R.layout.activity_schedule);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        listView = (ListView) findViewById(R.id.listView);

        // INIT PARSE.COM
        parse();
        parseFactory();

        currentDateTime = DateFormat.getDateTimeInstance().format(new Date());

        text = (TextView) findViewById(R.id.textView);
        text.setText(currentDateTime);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                Log.i(TAG, "DATE LIST CLICK: " + date);



                ObjectId = adapter.getItem(position).getObjectId();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(schedule.this);
                builder1.setMessage("Você quer agendar este horario?");

                builder1.setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ParseQuery<ParseObject> query = ParseQuery.getQuery("periods");

                                query.getInBackground(ObjectId, new GetCallback<ParseObject>() {
                                    public void done(ParseObject periods, ParseException e) {
                                        if (e == null) {

                                            periods.put("booked", true);
                                            periods.saveInBackground();
                                            //periods.put("user", user);
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

                month = month +1;
                StringBuilder sb = new StringBuilder();
                sb.append(year);
                sb.append("0" + month);
                sb.append(dayOfMonth);
                date = sb.toString();
                Log.i(TAG, "DATE: " + date);

                text.setText(date);

                parseFactory();
                adapter.notifyDataSetChanged();


            }
        });

    }

    // Init Parse
    void parse() {
        Parse.initialize(this, "fNj6swlEg1d5Rn4rO8jBPwJ6BlAbDN0A2GJbYnTB", "6Ua0deolkpYrnWagJRZcoRulDI2BHbLFccXzW85E");

    }

    // Init Parse Adapter
    void parseFactory() {
        factory = new ParseQueryAdapter.QueryFactory<ParseObject>() {

            public ParseQuery create() {
                ParseQuery query = new ParseQuery("periods");
                query.whereEqualTo("date", date);
                return query;
            }
        };

        adapter = new ParseQueryAdapter<ParseObject>(this, factory);
        adapter.setTextKey("period");
        listView.setAdapter(adapter);
    }

    // booking
    void booking () {
        if (booked == true) {



        }
    }








}



