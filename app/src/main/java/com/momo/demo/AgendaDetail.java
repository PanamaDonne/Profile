package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class AgendaDetail extends Activity {

    private String TAG;
    private TextView date;
    private TextView period;
    private TextView user;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_detail);

        TAG = "AgendaDetail";

        Log.i(TAG, "DATE: " + getIntent().getStringExtra("date"));

        date = (TextView)findViewById(R.id.dateText);
        date.setText(getIntent().getStringExtra("date"));

        period = (TextView)findViewById(R.id.periodText);
        period.setText(getIntent().getStringExtra("period"));

        user = (TextView)findViewById(R.id.userText);
        user.setText(getIntent().getStringExtra("username").replace("Booked by: ", ""));

        deleteButton = (Button)findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final ParseQuery<ParseObject> query = ParseQuery.getQuery("bookings_tennis");

                Log.i(TAG, "CLICKED");

                query.whereEqualTo("periods", period.getText());
                query.whereEqualTo("date", date.getText());
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(final List<ParseObject> bookedList, ParseException e) {

                        if (e == null) {
                            Log.i(TAG, "FOUND: " + bookedList.size());
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(AgendaDetail.this);
                            builder1.setMessage("You want to delete this period?");

                            builder1.setPositiveButton("Sim",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {


                                            bookedList.get(0).deleteInBackground();

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
                            //
                        }
                    }
                });
            }

        });
    }



}