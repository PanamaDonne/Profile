package com.momo.demo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by daniel-home on 11/09/14.
 */
public class PeriodListAdapter extends ParseQueryAdapter<Period> {

    private String TAG = "PeriodListAdapter";
    private TextView bookedtextView;
    private TextView titleTextView;




    public PeriodListAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Period>() {
            public ParseQuery create() {

                ParseQuery query = new ParseQuery("periods");
                query.orderByAscending("order");
                return query;



            }
        });
    }

    @Override
    public View getItemView(Period period, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.periodlist, null);
        }

        super.getItemView(period, v, parent);



        titleTextView = (TextView) v.findViewById(R.id.text1);
        titleTextView.setText(period.getTitle());

        bookedtextView = (TextView) v.findViewById(R.id.text2);


        return v;
    }



}
