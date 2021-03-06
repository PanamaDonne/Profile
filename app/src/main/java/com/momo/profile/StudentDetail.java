package com.momo.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class StudentDetail extends Activity  {

    private TextView student;
    private TextView teacher;
    private TextView day1;
    private TextView day2;
    private TextView day3;
    private TextView time1;
    private TextView time2;
    private TextView time3;
    private Button deleteStudentBtn;
    private Button changeTeacherBtn;
    private String weekDay;
    private String TAG;
    private String newTeacher;
    private String oldTeacher;
    private Context context;
    private ProgressDialog progress;
    private int startHour, startMinute, endHour, endMinute;
    private String stringStartHour, stringStartMinute, stringEndHour, stringEndMinute;
    private String studentString;
    private String objectId;
    private int timeTextViewId;
    private int dayTextViewId;


    // ------------------------ PARSE QUERY FOR "STUDENT" CLASS. -----------------------------------

    final ParseQuery<ParseObject> query = ParseQuery.getQuery("student");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = "StudentDetail";

        context = this;


        setContentView(R.layout.activity_student_detail);

        student = (TextView)findViewById(R.id.studentName);
        student.setText((getIntent().getStringExtra("studentName")));

        studentString =  student.getText().toString();

        teacher = (TextView)findViewById(R.id.teacherName);

        day1 = (TextView)findViewById(R.id.day1);
        day2 = (TextView)findViewById(R.id.day2);
        day3 = (TextView)findViewById(R.id.day3);

        time1 = (TextView)findViewById(R.id.time1);
        time2 = (TextView)findViewById(R.id.time2);
        time3 = (TextView)findViewById(R.id.time3);


        deleteStudentBtn = (Button) findViewById(R.id.btnDeleteStudent);
        changeTeacherBtn = (Button) findViewById(R.id.btnChangeTeacher);



        //parseClasses();
        //showProgressDialog();

        // ------------------------------------------- CLICK EVENTS --------------------------------

        deleteStudentBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "PRESSED");
                deleteStudentAlertDialog();


            }

        });

        changeTeacherBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "PRESSED");

                changeTeacherList();


            }

        });


        View.OnClickListener onClickListenerWeekday = new View.OnClickListener() {
            public void onClick(View view) {

                dayTextViewId = view.getId();

                switch (dayTextViewId) {
                    case R.id.day1:

                        query.whereEqualTo("name", studentString);
                        query.whereEqualTo("weekDay", day1.getText().toString().toLowerCase());


                        break;
                    case R.id.day2:

                        query.whereEqualTo("name", studentString);
                        query.whereEqualTo("weekDay", day2.getText().toString().toLowerCase());


                        break;
                    case R.id.day3:

                        query.whereEqualTo("name", studentString);
                        query.whereEqualTo("weekDay", day3.getText().toString().toLowerCase());

                        break;
                }


                daysOfWeekPicker();

            }
        };


        View.OnClickListener onClickListenerTime = new View.OnClickListener() {
            public void onClick(View view) {

                timeTextViewId = view.getId();

                switch (timeTextViewId) {
                    case R.id.time1:

                        query.whereEqualTo("name", studentString);
                        query.whereEqualTo("weekDay", day1.getText().toString().toLowerCase());


                        break;
                    case R.id.time2:

                        query.whereEqualTo("name", studentString);
                        query.whereEqualTo("weekDay", day2.getText().toString().toLowerCase());


                        break;
                    case R.id.time3:

                        query.whereEqualTo("name", studentString);
                        query.whereEqualTo("weekDay", day3.getText().toString().toLowerCase());

                        break;
                }

                startTimePicker();

            }
        };

        day1.setOnClickListener(onClickListenerWeekday);
        day2.setOnClickListener(onClickListenerWeekday);
        day3.setOnClickListener(onClickListenerWeekday);

        time1.setOnClickListener(onClickListenerTime);
        time2.setOnClickListener(onClickListenerTime);
        time3.setOnClickListener(onClickListenerTime);



    }


    void showProgressDialog () {

        progress = new ProgressDialog(StudentDetail.this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait...");
        progress.show();
    }



    // --------------------------------------  TIME PICKER DIALOGS ---------------------------------


    void parseTime () {
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {

                    Log.i(TAG, "ID: " + classList.get(0).getObjectId());

                    switch (timeTextViewId) {


                        case R.id.time1:

                            objectId = classList.get(0).getObjectId();


                            break;
                        case R.id.time2:

                            objectId = classList.get(1).getObjectId();

                            break;
                        case R.id.time3:

                            objectId = classList.get(2).getObjectId();

                            break;
                    }





                   query.getInBackground(objectId, new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {



                                object.put("time", stringStartHour + "." + stringStartMinute + " - " + stringEndHour + "." + stringEndMinute);
                                object.saveInBackground();

                                Log.i(TAG, "TIME: " + stringStartHour + "." + stringStartMinute + " - " + stringEndHour + "." + stringEndMinute);


                            } else {

                                Log.d(TAG, "Error: " + e.getMessage());
                            }
                        }
                    });



                }else {

                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
    }


    void parseWeekDay () {
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {

                    Log.i(TAG, "ID: " + classList.get(0).getObjectId());

                    Log.i(TAG, "FOUND: " + classList.size());

                    objectId = classList.get(0).getObjectId();




                    query.getInBackground(objectId, new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {

                                object.put("weekDay", weekDay.toLowerCase());
                                object.saveInBackground();



                            } else {

                                Log.d(TAG, "Error: " + e.getMessage());
                            }
                        }
                    });



                }else {

                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
    }



    void parseTeacher () {
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {





                }else {

                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
    }


    // DATE PICKER
    private void daysOfWeekPicker() {


        final String[] days = getResources().getStringArray(R.array.days);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change day of class");
        builder.setItems(days, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                weekDay = days[item];

                changeDayAlertDialog();





            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }



    // TIME PICKER DIALOGS
    void endTimePicker() {

        TimePickerDialog timePickerDialogEndTime = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {


                        stringEndHour = Integer.toString(hourOfDay);
                        stringEndMinute = Integer.toString(minute);

                        parseTime();

                        Log.i(TAG, "TIME PICKER: " + hourOfDay + " " + minute);
                    }
                }, endHour, endMinute, true);

        timePickerDialogEndTime.setTitle("Please select the end time");
        timePickerDialogEndTime.show();

    }



    void startTimePicker() {

        TimePickerDialog timePickerDialogStartTime = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        stringStartHour = Integer.toString(hourOfDay);
                        stringStartMinute = Integer.toString(minute);
                        endTimePicker();

                    }
                }, startHour, startMinute, true);


        timePickerDialogStartTime.setTitle("Please select the start time");
        timePickerDialogStartTime.show();

    }






    // ALERT DIALOGS
    void deleteStudentAlertDialog() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(StudentDetail.this);
        builder1.setMessage("Are You sure You want to delete the student?");


            // DELETE STUDENT
            builder1.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {


                            showProgressDialog();
                            deleteStudent();

                        }
                    });


            builder1.setCancelable(true);
            builder1.setNegativeButton("CANCEL",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });



        builder1.show();



    }



    void changeDayAlertDialog() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(StudentDetail.this);
        builder1.setMessage("Are you sure you want to change day of the class?");


        // DELETE STUDENT
        builder1.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        parseWeekDay();
                        Intent intent = new Intent(StudentDetail.this,
                                Students.class);
                        startActivity(intent);

                    }
                });


        builder1.setCancelable(true);
        builder1.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });



        builder1.show();



    }




    void parseClasses() {


        final String CLASS_INFO_LABEL = "classinfo";


        query.whereEqualTo("name", studentString);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {

                    day1.setText(classList.get(0).getString("weekDay").toUpperCase());
                    time1.setText(classList.get(0).getString("time"));
                    teacher.setText(classList.get(0).getString("teacher"));

                    if(classList.size() > 1) {
                        day2.setText(classList.get(1).getString("weekDay").toUpperCase());
                        time2.setText(classList.get(1).getString("time"));
                    }

                    if(classList.size() > 2) {
                        day3.setText(classList.get(2).getString("weekDay").toUpperCase());
                        time3.setText(classList.get(2).getString("time"));
                    }

                    progress.dismiss();

                    // Release any objects previously pinned for this query.
                    ParseObject.unpinAllInBackground(CLASS_INFO_LABEL, classList, new DeleteCallback() {
                        public void done(ParseException e) {
                            if (e != null) {
                                // There was some error.
                                return;
                            }

                            // Add the latest results for this query to the cache.
                            ParseObject.pinAllInBackground(CLASS_INFO_LABEL, classList);
                        }
                    });


                }else {

                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }


    void deleteStudent() {


        query.whereEqualTo("name", studentString);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {

                    classList.get(0).deleteInBackground(new DeleteCallback() {
                        public void done(ParseException e) {
                            if (e == null) {

                                progress.dismiss();
                                Toast.makeText(context, studentString + " deleted",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(StudentDetail.this,
                                        DashBoardActivity.class);
                                startActivity(intent);

                            } else {
                                progress.dismiss();
                                Toast.makeText(context, "  not deleted. Please try again.",
                                        Toast.LENGTH_SHORT).show();
                                e.printStackTrace();

                            }
                        }
                    });

                    if(classList.size() > 1) {
                        classList.get(1).deleteInBackground(new DeleteCallback() {
                            public void done(ParseException e) {
                                if (e == null) {

                                } else {

                                    e.printStackTrace();

                                }
                            }
                        });
                    }

                } else {

                    Toast.makeText(context, "  not found. Please try again.",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();


                }


            }
        });



    }



    // CHANGE TEACHER
    private void changeTeacherList() {


        final String[] teachers = getResources().getStringArray(R.array.teachers);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change teacher");
        builder.setItems(teachers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                newTeacher = teachers[item];

                oldTeacher = teacher.getText().toString();

                Log.i(TAG, "OLD TEACHER: " + oldTeacher);

                query.whereEqualTo("teacher", oldTeacher);
                parseTeacher();



            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }



}
