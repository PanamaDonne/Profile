package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class sauna extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauna);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new String[] {"MONDAY 18 AUG 10:00", "MONDAY 18 AUG 15:30", "MONDAY 18 AUG 21:30", "TUESDAY 19 AUG 08:30", "TUESDAY 19 AUG 18:30", "WEDNESDAY 20 AUG 10:15"}));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String sText = ((TextView) view).getText().toString();
                if(sText != null) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(sauna.this);
                    builder1.setMessage("You want to schedule this class?");

                    builder1.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getApplicationContext(), "CLASS SCHEDULED. YOU WILL RECEIVE A NOTIFICATION X HOUR(S) BEFORE THE CLASS. WELCOME.",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("CANCEL",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

            }
        });
    }

}
