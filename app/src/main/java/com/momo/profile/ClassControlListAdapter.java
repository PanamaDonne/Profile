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
public class ClassControlListAdapter extends ParseQueryAdapter<ParseStudent> {

    private static String TAG = "ClassControlListAdapter";
    private TextView studentTextView;
    private TextView timeTextView;
    private TextView dateTextView;



    public ClassControlListAdapter(Context context) {


        super(context, new QueryFactory<ParseStudent>() {
            public ParseQuery create() {



                ParseQuery query = new ParseQuery("student");

                query.whereEqualTo("month", "December");

                query.orderByAscending("name");

                return query;

            }

        });
    }

    @Override
    public View getItemView(final ParseStudent parseStudent, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.classcontrollist, null);
        }

        super.getItemView(parseStudent, v, parent);

        studentTextView = (TextView) v.findViewById(R.id.studentName);
        studentTextView.setText(parseStudent.getStudentName().toUpperCase());

        timeTextView = (TextView) v.findViewById(R.id.time);
        timeTextView.setText(parseStudent.getTime());

        dateTextView = (TextView) v.findViewById(R.id.date);
        dateTextView.setText(parseStudent.getDate());

        return v;
    }


}
