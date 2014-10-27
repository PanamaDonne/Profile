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

    private String TAG = "StudentListAdapter";
    private TextView studentTextView;
    private TextView teacherTextView;




    public StudentListAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Student>() {
            public ParseQuery create() {

                ParseQuery query = new ParseQuery("student");
                query.orderByAscending("name");

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
        studentTextView.setText(student.getStudentName());

        teacherTextView = (TextView) v.findViewById(com.momo.profile.R.id.text2);
        teacherTextView.setText(student.getTeacherName());

        Log.i(TAG, "STUDENT NAME: " + student.getStudentName());

        Log.i(TAG, "TEACHER NAME: " + student.getTeacherName());



        return v;
    }



}
