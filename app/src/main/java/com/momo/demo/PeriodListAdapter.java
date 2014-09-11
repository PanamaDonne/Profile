package com.momo.demo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.Arrays;

/**
 * Created by daniel-home on 11/09/14.
 */
public class PeriodListAdapter extends ParseQueryAdapter<Period> {

    private String TAG = "PeriodListAdapter";



    public PeriodListAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Period>() {
            public ParseQuery create() {
                // Here we can configure a ParseQuery to display
                // only top-rated meals.
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



        TextView titleTextView = (TextView) v.findViewById(R.id.text1);
        titleTextView.setText(period.getTitle());


        TextView bookedtextView = (TextView) v
                .findViewById(R.id.text2);



        return v;
    }


}
