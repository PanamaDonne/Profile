package com.momo.profile;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;



public class TeacherDetails extends Activity {

    private final String TAG = "TeacherDetail";
    private Context context;
    private TextView teacherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;


        setContentView(R.layout.activity_teacher_details);

        teacherTextView = (TextView) findViewById(R.id.teacherName);
        teacherTextView.setText((getIntent().getStringExtra("teacherName")));

    }

}
