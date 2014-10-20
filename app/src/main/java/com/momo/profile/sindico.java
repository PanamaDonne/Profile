package com.momo.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class sindico extends Activity {

    private EditText textField;
    private Button buttonSend;

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(com.momo.profile.R.layout.activity_sindico);

        textField = (EditText) findViewById(com.momo.profile.R.id.editText);
        buttonSend = (Button) findViewById(com.momo.profile.R.id.buttonSend);

        buttonSend.setText("ENVIAR MENSAGEM");
        buttonSend.setTextColor(Color.WHITE);

        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sua mensagem foi enviada com sucesso. Em breve entrarei em contato.",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(sindico.this,CondoActivity.class);
                startActivity(intent);
            }
        });

    }
}
