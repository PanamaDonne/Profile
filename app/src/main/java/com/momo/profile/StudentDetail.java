package com.momo.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
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
    private Button deleteTeacherBtn;
    private String weekDay;
    private String TAG;
    private Context context;
    private ProgressDialog progress;
    private Spinner spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = "StudentDetail";

        context = this;


        setContentView(R.layout.activity_student_detail);

        student = (TextView)findViewById(R.id.studentName);
        student.setText((getIntent().getStringExtra("studentName")));

        teacher = (TextView)findViewById(R.id.teacherName);


        day1 = (TextView)findViewById(R.id.day1);
        day2 = (TextView)findViewById(R.id.day2);
        day3 = (TextView)findViewById(R.id.day3);

        time1 = (TextView)findViewById(R.id.time1);
        time2 = (TextView)findViewById(R.id.time2);
        time3 = (TextView)findViewById(R.id.time3);


        deleteStudentBtn = (Button) findViewById(R.id.btnDeleteStudent);
        deleteTeacherBtn = (Button) findViewById(R.id.btnDeleteStudent);

        spinner = (Spinner) findViewById(R.id.spinner);











        parseClasses();
        showProgressDialog();

        // CLICK EVENTS
        deleteStudentBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "PRESSED");


            }

        });

        deleteTeacherBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "PRESSED");
                deleteStudentAlertDialog();


            }

        });


        View.OnClickListener onClickListener = new View.OnClickListener() {
            public void onClick(View view) {



                Log.i(TAG, "PRESSED");

            }
        };

        day1.setOnClickListener(onClickListener);








    }


    void showProgressDialog () {

        progress = new ProgressDialog(StudentDetail.this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait...");
        progress.show();
    }



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



    void parseClasses() {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("student");

        String studentString = student.getText().toString();

        query.whereEqualTo("name", studentString);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> classList, ParseException e) {
                if (e == null && classList.size() > 0) {

                    day1.setText(classList.get(0).getString("weekDay").toUpperCase());
                    time1.setText(classList.get(0).getString("time"));
                    teacher.setText("( " + classList.get(0).getString("teacher") + " )");

                    if(classList.size() > 1) {
                        day2.setText(classList.get(1).getString("weekDay").toUpperCase());
                        time2.setText(classList.get(1).getString("time"));
                    }

                    if(classList.size() > 2) {
                        day3.setText(classList.get(2).getString("weekDay").toUpperCase());
                        time3.setText(classList.get(2).getString("time"));
                    }

                    progress.dismiss();


                }else {

                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }


    void deleteStudent() {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("student");

        final String studentString = student.getText().toString();

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



}
