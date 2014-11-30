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
public class TeacherListAdapter extends ParseQueryAdapter<ParseStudent> {

    private static String TAG = "TeacherListAdapter";
    private TextView teacherTextView;



    public TeacherListAdapter(Context context) {


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
            v = View.inflate(getContext(), R.layout.teacherlist, null);
        }

        super.getItemView(parseStudent, v, parent);


        teacherTextView = (TextView) v.findViewById(R.id.teacher);
        teacherTextView.setText(parseStudent.getTeacherName().toUpperCase());


        return v;
    }







}
