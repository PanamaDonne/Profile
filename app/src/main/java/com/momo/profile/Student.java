package com.momo.profile;

import android.nfc.Tag;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by daniel-home on 11/09/14.
 */

@ParseClassName("student")
public class Student extends ParseObject {


    public Student() {
        // A default constructor is required.
    }

    public String getStudentName() {
        return getString("name");
    }

    public String getTeacherName() {
        return getString("teacher");
    }

    public String getTime() {
        return getString("time");
    }






}