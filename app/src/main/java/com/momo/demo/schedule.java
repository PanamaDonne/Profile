package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

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




public class schedule extends Activity {

    private String TAG;
    private ListView listView;
    public String date;
    private CalendarView calendarView;
    String currentDateTime;
    private TextView text;
    //private ParseQueryAdapter.QueryFactory<ParseObject> factory;
    //private ParseQueryAdapter<ParseObject> adapter;
    private PeriodListAdapter periodAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "calendarActivity";
        setContentView(R.layout.activity_schedule);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        listView = (ListView) findViewById(R.id.listView);


        // Subclass of ParseQueryAdapter
        periodAdapter = new PeriodListAdapter(this);


        // INIT PARSE.COM
        ParseObject.registerSubclass(Period.class);
        parse();

        listView.setAdapter(periodAdapter);





        currentDateTime = DateFormat.getDateTimeInstance().format(new Date());

        text = (TextView) findViewById(R.id.textView);
        text.setText(currentDateTime);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View view,
                                    final int position, long id) {


                Log.i(TAG, "DATE LIST CLICK: " + date);
                Log.i(TAG, "POSITION: " + position);


                // CHECK IF BOOKED.
                final ParseQuery<ParseObject> query = ParseQuery.getQuery("bookings_tennis");
                query.whereEqualTo("date", date);
                query.whereEqualTo("position", position);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> bookedList, ParseException e) {
                        if (e == null) {
                            Log.i(TAG, "FOUND: " + bookedList.size());



                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });




                AlertDialog.Builder builder1 = new AlertDialog.Builder(schedule.this);
                builder1.setMessage("Você quer agendar este horario?");

                builder1.setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {



                                ParseObject bookings = new ParseObject("bookings_tennis");



                                switch (position) {


                                    case 0:  {

                                        bookings.put("date", date);
                                        bookings.put("periods", "07:00 - 08:00");
                                        bookings.put("booked", true);
                                        bookings.put("position", 0);
                                        bookings.saveInBackground();


                                    }
                                        break;
                                    case 1:  {

                                        bookings.put("date", date);
                                        bookings.put("periods", "08:00 - 09:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 1);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 2:  {

                                        bookings.put("date", date);
                                        bookings.put("periods", "09:00 - 10:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 2);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 3:  {

                                        bookings.put("date", date);
                                        bookings.put("periods", "10:00 - 11:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 3);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 4:  {

                                        bookings.put("date", date);
                                        bookings.put("periods", "11:00 - 12:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 4);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 5:  {

                                        bookings.put("date", date);
                                        bookings.put("periods", "12:00 - 13:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 5);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 6:  {

                                        bookings.put("date", date);
                                        bookings.put("periods", "13:00 - 14:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 6);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 7:  {

                                        bookings.put("date", date);
                                        bookings.put("periods", "14:00 - 15:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 7);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 8:  {

                                        bookings.put("date", date);
                                        bookings.put("periods", "15:00 - 16:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 8);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 9: {

                                        bookings.put("date", date);
                                        bookings.put("periods", "16:00 - 17:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 9);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 10: {

                                        bookings.put("date", date);
                                        bookings.put("periods", "17:00 - 18:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 10);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 11: {

                                        bookings.put("date", date);
                                        bookings.put("periods", "18:00 - 19:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 11);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 12: {

                                        bookings.put("date", date);
                                        bookings.put("periods", "19:00 - 20:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 12);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 13: {

                                        bookings.put("date", date);
                                        bookings.put("periods", "20:00 - 21:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 13);
                                        bookings.saveInBackground();

                                    }
                                        break;
                                    case 14: {

                                        bookings.put("date", date);
                                        bookings.put("periods", "21:00 - 22:00" );
                                        bookings.put("booked", true);
                                        bookings.put("position", 14);
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



            }
        });

    }

    // Init Parse
    void parse() {
        Parse.initialize(this, "fNj6swlEg1d5Rn4rO8jBPwJ6BlAbDN0A2GJbYnTB", "6Ua0deolkpYrnWagJRZcoRulDI2BHbLFccXzW85E");

    }




}



