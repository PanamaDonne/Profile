package com.momo.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;


public class Teachers extends Activity {

    private TeacherListAdapter teacherAdapter;
    private String TAG;
    private Context context;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        listView = (ListView) findViewById(com.momo.profile.R.id.listView);
        TAG = "teacherActivity";

        context = getApplicationContext();


        // ------------------------------------ Subclass of ParseQueryAdapter ------------------------
        teacherAdapter = new TeacherListAdapter(this);



        // ----------------------------------- INIT PARSE.COM ----------------------------------------
        ParseObject.registerSubclass(ParseStudent.class);

        listView.setAdapter(teacherAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View view,
                                    final int position, long id) {


                TextView teacherNameTextField = (TextView) view.findViewById(R.id.teacher);
                CharSequence teacherName = teacherNameTextField.getText();

                Intent intent = new Intent(Teachers.this, TeacherDetails.class);
                intent.putExtra("teacherName", teacherName);

                startActivity(intent);
            }

        });
    }







}
