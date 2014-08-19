package com.momo.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class sauna extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauna);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new String[] {"Segunda-feira, 18 de agosto 10:00", "Segunda-feira, 18 de agosto 15:00", "Segunda-feira, 18 de agosto 21:00", "Terça-Feira 19 de agosto 08:30", "Terça-Feira 19 de agosto 18:30", "Terça-Feira 19 de agosto 10:15"}));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String sText = ((TextView) view).getText().toString();
                if(sText != null) {
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
        });
    }

}
