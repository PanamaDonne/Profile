package com.momo.profile;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by daniel-home on 11/09/14.
 */
public class StudentListAdapter extends ParseQueryAdapter<Student> {

    private static String TAG = "StudentListAdapter";
    private TextView studentTextView;
    private TextView teacherTextView;
    private TextView timeTextView;
    private static String weekDay;



    public StudentListAdapter(Context context) {


        super(context, new ParseQueryAdapter.QueryFactory<Student>() {
            public ParseQuery create() {

                weekDay = Globals.weekDay;

                ParseQuery query = new ParseQuery("student");

                query.whereContains("weekDay", weekDay);

                query.orderByDescending("time");


                return query;

            }

        });
    }

    @Override
    public View getItemView(final Student student, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), com.momo.profile.R.layout.studentlist, null);
        }

        super.getItemView(student, v, parent);


        studentTextView = (TextView) v.findViewById(com.momo.profile.R.id.text1);
        studentTextView.setText("Student: " + student.getStudentName());

        teacherTextView = (TextView) v.findViewById(com.momo.profile.R.id.text2);
        teacherTextView.setText("Teacher: " + student.getTeacherName());

        timeTextView = (TextView) v.findViewById(com.momo.profile.R.id.time);
        timeTextView.setText(student.getTime());




        return v;
    }







}




