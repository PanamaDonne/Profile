package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class agenda extends Activity {

    private AgendaListAdapter agendaListAdapter;
    private String TAG;
    private ListView listView;
    private String objectId;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);


        TAG = "agenda";


        // Subclass of ParseQueryAdapter
        agendaListAdapter = new AgendaListAdapter(this);


        // INIT PARSE.COM
        ParseObject.registerSubclass(UserAgenda.class);
        parse();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(agendaListAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, final View view,
                                    final int position, long id) {


                TextView textView = (TextView) view.findViewById(R.id.textAgenda);
                final CharSequence date = textView.getText();

                TextView textView2 = (TextView) view.findViewById(R.id.textAgenda2);
                CharSequence period = textView2.getText();

                //TODO GET USERNAME
                TextView textView3 = (TextView) view.findViewById(R.id.userNameText);
                CharSequence userName = textView3.getText();

                Log.i(TAG, "LIST TEXT: " + period + " & " + date);

                // USER DELETE PERIOD WHEN CLICK ROW
                if(!ParseUser.getCurrentUser().getUsername().equals("admin")) {



                    final ParseQuery<ParseObject> query = ParseQuery.getQuery("bookings_tennis");

                    query.whereEqualTo("periods", period);
                    query.whereEqualTo("date", date);
                    query.whereEqualTo("bookedBy", ParseUser.getCurrentUser());
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(final List<ParseObject> bookedList, ParseException e) {

                            if (e == null) {



                                Log.i(TAG, "FOUND: " + bookedList.size());


                                AlertDialog.Builder builder1 = new AlertDialog.Builder(agenda.this);
                                builder1.setMessage("Você tem certeza que quer apagar este agendamento?");

                                builder1.setPositiveButton("Sim",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {



                                                // SET PERIOD TO STANDBY USER 1
                                                objectId = bookedList.get(0).getObjectId();

                                                ParseQuery<ParseObject> query = ParseQuery.getQuery("bookings_tennis");

                                                query.getInBackground(objectId, new GetCallback<ParseObject>() {
                                                    public void done(ParseObject bookings, ParseException e) {
                                                        if (e == null && (bookings.get("standby1") != null)) {


                                                            bookings.put("bookedBy", bookings.get("standby1"));
                                                            bookings.put("username", bookings.get("standby1username"));
                                                            bookings.saveInBackground();

                                                            Log.i(TAG, "STAND BY USER: " + bookings.get("standby1"));
                                                        }

                                                        else {

                                                            bookedList.get(0).deleteInBackground();


                                                        }

                                                        view.animate().setDuration(2000).alpha(0)
                                                                .withEndAction(new Runnable() {
                                                                    @Override
                                                                    public void run() {

                                                                        view.setAlpha(1);
                                                                        agendaListAdapter.clear();
                                                                        agendaListAdapter.notifyDataSetChanged();
                                                                        listView.setAdapter(agendaListAdapter);



                                                                    }
                                                                });





                                                    }
                                                });



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


                            } else {

                            }
                        }



                    });

                // ADMIN ENTER DETAIL VIEW
                } else {

                    Intent intent = new Intent(agenda.this,AgendaDetail.class);
                    intent.putExtra("date", date);
                    intent.putExtra("period", period);
                    intent.putExtra("username", userName);
                    startActivity(intent);

                }

            }


        });
    }






    void parse() {
        Parse.initialize(this, "fNj6swlEg1d5Rn4rO8jBPwJ6BlAbDN0A2GJbYnTB", "6Ua0deolkpYrnWagJRZcoRulDI2BHbLFccXzW85E");

    }

    void animateList (View view) {

    }



}
