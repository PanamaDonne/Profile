package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class mural extends Activity {

    private Button button1;
    private Button button2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mural);

        button1 = (Button) findViewById(R.id.button1);
        button1.setText("Reunião de Condomínio (22 de agosto)");
        button1.setTextColor(Color.WHITE);
        button1.setTextSize(16.0f);

        button2 = (Button) findViewById(R.id.button2);
        button2.setText("Início das obras de melhoria na garagem        (31 de agosto a 10 de setembro)");
        button2.setTextColor(Color.WHITE);
        button2.setTextSize(16.0f);


    }
}
