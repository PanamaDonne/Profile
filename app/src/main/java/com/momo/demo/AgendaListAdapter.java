package com.momo.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
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
import com.parse.ParseUser;

import java.util.Arrays;

/**
 * Created by daniel-home on 11/09/14.
 */
public class AgendaListAdapter extends ParseQueryAdapter<UserAgenda> {







    public AgendaListAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<UserAgenda>() {
            public ParseQuery create() {

                ParseQuery query = new ParseQuery("bookings_tennis");


                // USER LIST ONLY CURRENT USER AGENDA / ADMIN ALL USERS AGENDAS
                if(!ParseUser.getCurrentUser().getUsername().equals("admin")) {

                    query.whereEqualTo("bookedBy", ParseUser.getCurrentUser());

                }

                query.orderByAscending("date");



                return query;



            }
        });
    }

    @Override
    public View getItemView(UserAgenda useragenda, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.agendalist, null);
        }

        super.getItemView(useragenda, v, parent);

        ImageView garbageIcon = (ImageView) v.findViewById(R.id.garbage);

        TextView titleTextView = (TextView) v.findViewById(R.id.textAgenda);
        titleTextView.setText(useragenda.getTitle());

        TextView classTextView = (TextView) v.findViewById(R.id.classCat);
        classTextView.setText("Tênis");

        TextView periodTextView = (TextView) v.findViewById(R.id.textAgenda2);
        periodTextView.setText(useragenda.getPeriod());

        TextView userTextView = (TextView) v.findViewById(R.id.userNameText);

        // ADMIN PERMISSION
        if(ParseUser.getCurrentUser().getUsername().equals("admin")) {

            garbageIcon.setVisibility(View.GONE);
            userTextView.setText(useragenda.getUser());

        }

        return v;
    }





}


