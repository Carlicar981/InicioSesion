package com.example.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass;
    Button btnEntrar,btnRegistrar;
    darUsuario dar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user= (EditText) findViewById(R.id.user);
        pass=(EditText) findViewById(R.id.pass);
        btnEntrar=(Button) findViewById(R.id.btnEntrar);
        btnRegistrar=(Button) findViewById(R.id.btnInicioRegistrar);
        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dar = new darUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if (u.equals("")&&p.equals("")){
                    Toast.makeText(this,"Error: Campos vacios",Toast.LENGTH_SHORT).show();
                }else if (dar.login(u,p)==1){
                    Usuario user = dar.getUsuario(u,p);
                    Toast.makeText(this,"Datos correctos!",Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(MainActivity.this,Inicio.class);
                    i2.putExtra("id",user.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this,"Datos Incorrectos!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnInicioRegistrar:
                Intent i = new Intent(MainActivity.this,Registar.class);
                startActivity(i);
                break;

        }
    }
}