package com.momo.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View view,
                                    final int position, long id) {

                //GET STUDENT INFO
                TextView studentNameTextField = (TextView) view.findViewById(R.id.text1);
                CharSequence studentName = studentNameTextField.getText();


                Intent intent = new Intent(Students.this,StudentDetail.class);
                intent.putExtra("studentName", studentName);

                startActivity(intent);

            }

        });



    }



}
