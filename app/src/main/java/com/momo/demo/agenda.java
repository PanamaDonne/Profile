package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.Formatter;
import java.util.List;


public class agenda extends Activity {

    private AgendaListAdapter agendaListAdapter;
    private String TAG;
    private ListView listView;

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
                                builder1.setMessage("You want to delete this period?");

                                builder1.setPositiveButton("Sim",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {


                                                bookedList.get(0).deleteInBackground();

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

                    Intent intent = new Intent(agenda.this,LoginActivity.class);
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
