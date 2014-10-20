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
public class PeriodListAdapter extends ParseQueryAdapter<Period> {

    private String TAG = "PeriodListAdapter";
    public TextView bookedtextView;
    private TextView titleTextView;




    public PeriodListAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Period>() {
            public ParseQuery create() {

                ParseQuery query = new ParseQuery("periods");
                query.orderByAscending("order");
                query.include("booked");
                return query;



            }
        });
    }

    @Override
    public View getItemView(final Period period, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), com.momo.profile.R.layout.periodlist, null);
        }

        super.getItemView(period, v, parent);


        titleTextView = (TextView) v.findViewById(com.momo.profile.R.id.text1);
        titleTextView.setText(period.getTitle());

        bookedtextView = (TextView) v.findViewById(com.momo.profile.R.id.text2);


        return v;
    }



}
