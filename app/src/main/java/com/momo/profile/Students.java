package com.momo.profile;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseObject;


public class Students extends Activity {

    private ListView listView;
    private String TAG;
    private Context context;
    private StudentListAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        listView = (ListView) findViewById(com.momo.profile.R.id.listView);
        TAG = "studentActivity";

        context = getApplicationContext();


        // ------------------------------------ Subclass of ParseQueryAdapter ------------------------
        studentAdapter = new StudentListAdapter(this);




        // ----------------------------------- INIT PARSE.COM ----------------------------------------
        ParseObject.registerSubclass(ParseStudent.class);
        Parse.initialize(this, "fpHnJtvttEKiKs2FmZ6UPMrVxPjD4KMdnRj3jgIi", "w3KrbARBlNEJg0gNhD0FwiJBvcoDokDloLYQMtDz");


        listView.setAdapter(studentAdapter);



    }



}
