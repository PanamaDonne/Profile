package com.momo.profile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by daniel-home on 11/09/14.
 */
public class ScheduleListAdapter extends ParseQueryAdapter<ParseStudent> {

    private static String TAG = "ScheduleListAdapter";
    private TextView studentTextView;
    private TextView teacherTextView;
    private TextView timeTextView;
    private static String weekDay;



    public ScheduleListAdapter(Context context) {


        super(context, new ParseQueryAdapter.QueryFactory<ParseStudent>() {
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
    public View getItemView(final ParseStudent parseStudent, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), com.momo.profile.R.layout.schedulelist, null);
        }

        super.getItemView(parseStudent, v, parent);


        studentTextView = (TextView) v.findViewById(com.momo.profile.R.id.text1);
        studentTextView.setText("Student: " + parseStudent.getStudentName());

        teacherTextView = (TextView) v.findViewById(com.momo.profile.R.id.text2);
        teacherTextView.setText("Teacher: " + parseStudent.getTeacherName());

        timeTextView = (TextView) v.findViewById(com.momo.profile.R.id.time);
        timeTextView.setText(parseStudent.getTime());




        return v;
    }







}




