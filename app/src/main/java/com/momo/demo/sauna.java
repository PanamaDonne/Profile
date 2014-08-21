package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class sauna extends Activity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauna);

        button1 = (Button) findViewById(R.id.button1);
        button1.setTextColor(Color.WHITE);

        button2 = (Button) findViewById(R.id.button2);
        button2.setTextColor(Color.WHITE);

        button3 = (Button) findViewById(R.id.button3);
        button3.setTextColor(Color.WHITE);

        button4 = (Button) findViewById(R.id.button4);
        button4.setTextColor(Color.WHITE);

        button5 = (Button) findViewById(R.id.button5);
        button5.setTextColor(Color.WHITE);

        button6 = (Button) findViewById(R.id.button6);
        button6.setTextColor(Color.WHITE);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog();





            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog();





            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog();





            }
        });

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog();





            }
        });

        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog();





            }
        });

        button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog();





            }
        });

    }

    void dialog () {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(sauna.this);
        builder1.setMessage("Você quer agendar esta atividade?");

        builder1.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "Atividade marcada! Você receberá uma notificação duas horas antes da sua atividade. Bem-vindo!",
                                Toast.LENGTH_LONG).show();

                    }
                });
        builder1.setCancelable(true);
        builder1.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
