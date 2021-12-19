package com.example.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registar extends AppCompatActivity implements View.OnClickListener {
EditText us,pass,nom,ap;
Button registrar ,cancelar;
darUsuario dar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registar);
        us = (EditText) findViewById(R.id.RegUser);
        pass = (EditText) findViewById(R.id.RegPass);
        nom = (EditText) findViewById(R.id.RegNombre);
        ap = (EditText) findViewById(R.id.RegApellido);
        registrar = (Button) findViewById(R.id.btnRegistrar);
        cancelar = (Button) findViewById(R.id.btnRegCancelar);

        registrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        dar = new darUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrar:
                Usuario u = new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pass.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellidos(ap.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this,"Error: Campos vacios",Toast.LENGTH_SHORT).show();
                }else if(dar.insertUsuario(u)){
                    Toast.makeText(this,"Registro exitoso!",Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(Registar.this,MainActivity.class);
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this,"Usuario ya existente!",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnRegCancelar:
                Intent i = new Intent(Registar.this,MainActivity.class);
                startActivity(i);
                finish();
                break;

        }
    }
}