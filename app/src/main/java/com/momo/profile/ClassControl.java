package com.momo.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class ClassControl extends Activity {

    private final String TAG = "ClassControl";
    private ClassControlListAdapter classControlListAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_control);

        listView = (ListView) findViewById(R.id.listView);

        // ------------------------------------ Subclass of ParseQueryAdapter ------------------------
        classControlListAdapter = new ClassControlListAdapter(this);



        // ----------------------------------- INIT PARSE.COM ----------------------------------------
        ParseObject.registerSubclass(ParseStudent.class);
        parse();
        //parseClasses();

        listView.setAdapter(classControlListAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View view,
                                    final int position, long id) {

            }

        });

        

    }




    // Init Parse
    void parse() {
        Parse.initialize(this, "fpHnJtvttEKiKs2FmZ6UPMrVxPjD4KMdnRj3jgIi", "w3KrbARBlNEJg0gNhD0FwiJBvcoDokDloLYQMtDz");

    }


    // ------------------------------------  PARSE CLASSES ----------------------------
    void parseClasses() {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("student");


        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {


                    Log.i(TAG, "FOUND: " + classList.size());

                    //listView.setAdapter(scheduleAdapter);

                    //progress.dismiss();

                } else if(classList.size() == 0) {

                    //scheduleAdapter.clear();
                    //progress.dismiss();

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }


}
