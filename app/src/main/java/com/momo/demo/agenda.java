package com.momo.demo;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


public class agenda extends Activity {

    private AgendaListAdapter agendaListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        // Subclass of ParseQueryAdapter
        agendaListAdapter = new AgendaListAdapter(this);


        // INIT PARSE.COM
        ParseObject.registerSubclass(UserAgenda.class);
        parse();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(agendaListAdapter);



    }

    void parse() {
        Parse.initialize(this, "fNj6swlEg1d5Rn4rO8jBPwJ6BlAbDN0A2GJbYnTB", "6Ua0deolkpYrnWagJRZcoRulDI2BHbLFccXzW85E");

    }

}
