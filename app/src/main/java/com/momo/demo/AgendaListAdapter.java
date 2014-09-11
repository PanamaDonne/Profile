package com.momo.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


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
public class AgendaListAdapter extends ParseQueryAdapter<UserAgenda> {





    public AgendaListAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<UserAgenda>() {
            public ParseQuery create() {
                // Here we can configure a ParseQuery to display
                // only top-rated meals.
                ParseQuery query = new ParseQuery("bookings_tennis");

                query.orderByAscending("date");

                return query;



            }
        });
    }

    @Override
    public View getItemView(UserAgenda useragenda, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.periodlist, null);
        }

        super.getItemView(useragenda, v, parent);



        TextView titleTextView = (TextView) v.findViewById(R.id.text1);
        titleTextView.setText(useragenda.getTitle());


        TextView periodTextView = (TextView) v
                .findViewById(R.id.text2);
      periodTextView.setText(useragenda.getPeriod());



        return v;
    }


}
