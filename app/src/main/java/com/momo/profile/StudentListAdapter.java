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
public class StudentListAdapter extends ParseQueryAdapter<ParseStudent> {

    private static String TAG = "ScheduleListAdapter";
    private TextView studentTextView;



    public StudentListAdapter(Context context) {


        super(context, new QueryFactory<ParseStudent>() {
            public ParseQuery create() {



                ParseQuery query = new ParseQuery("student");

                query.whereEqualTo("duplicateName", true);

                query.orderByAscending("name");


                return query;

            }

        });
    }

    @Override
    public View getItemView(final ParseStudent parseStudent, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.studentlist, null);
        }

        super.getItemView(parseStudent, v, parent);


        studentTextView = (TextView) v.findViewById(R.id.text1);
        studentTextView.setText(parseStudent.getStudentName());


        return v;
    }







}




