package com.example.iniciosesion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity implements View.OnClickListener {
    Button btnEditar,btnMostrar,btnEliminar,btnSalir;
    TextView nombre;
int id=0;
Usuario u;
darUsuario dar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        nombre= (TextView) findViewById(R.id.nombreUsuario);
        btnEditar=(Button) findViewById(R.id.btnEditar);
        btnMostrar=(Button) findViewById(R.id.btnMostrar);
        btnEliminar=(Button) findViewById(R.id.btnEliminar);
        btnSalir=(Button) findViewById(R.id.btnSalir);

        btnSalir.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        dar = new darUsuario(this);
        u=dar.getUsuarioId(id);
        nombre.setText("Hola!, "+u.getNombre()+" "+u.getApellidos());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditar:
                Intent e = new Intent(Inicio.this, Editar.class);
                e.putExtra("id",id);
                startActivity(e);
                break;
            case R.id.btnMostrar:
                Intent m = new Intent(Inicio.this, Mostrar.class);
                startActivity(m);
                break;
            case R.id.btnEliminar:
                System.out.println("hola");
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("¿Estas seguro de Eliminar tu cuenta?")
                    .setCancelable(false)
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      if (dar.deleteUsuario(id)){
                          Toast.makeText(Inicio.this,"Se elimino correctamente!",Toast.LENGTH_SHORT).show();
                          Intent i = new Intent(Inicio.this, MainActivity.class);
                          startActivity(i);
                          finish();
                      }else{
                          Toast.makeText(Inicio.this,"No se elimino la cuenta!",Toast.LENGTH_SHORT).show();
                      }
                    }
                })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                    }
                });
                AlertDialog titulo = b.create();
                titulo.setTitle("Eliminar");
                titulo.show();
                break;
            case R.id.btnSalir:
                Intent s = new Intent(Inicio.this, MainActivity.class);
                startActivity(s);
                finish();
                break;
        }
    }
}