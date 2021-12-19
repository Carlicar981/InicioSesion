package com.example.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity implements View.OnClickListener {
    EditText editUser, editPass,editNombre, editApellido;
    Button btnActualizar,btnCancelar;
    int id = 0;
    Usuario u;
    darUsuario dar;
    Intent x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);
        editUser= (EditText) findViewById(R.id.EditUser);
        editPass= (EditText) findViewById(R.id.EditPass);
        editNombre= (EditText) findViewById(R.id.EditNombre);
        editApellido= (EditText) findViewById(R.id.EditApellido);
        btnActualizar= (Button) findViewById(R.id.btnActualizar);
        btnCancelar= (Button) findViewById(R.id.btnEditCancelar);

        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        dar = new darUsuario(this);
        u=dar.getUsuarioId(id);
        editUser.setText(u.getUsuario());
        editPass.setText(u.getPassword());
        editNombre.setText(u.getNombre());
        editApellido.setText(u.getApellidos());

        btnCancelar.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnActualizar:
                u.setUsuario(editUser.getText().toString());
                u.setPassword(editPass.getText().toString());
                u.setNombre(editNombre.getText().toString());
                u.setApellidos(editApellido.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this,"Error: Campos vacios",Toast.LENGTH_SHORT).show();
                }else if(dar.updateUsuario(u)){
                    Toast.makeText(this,"Actualizacion exitosa!",Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(Editar.this,Inicio.class);
                    i2.putExtra("id",u.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this,"No se puede actualizar!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEditCancelar:
                Intent i = new Intent(Editar.this, Inicio.class);
                i.putExtra("id",u.getId());
                startActivity(i);
                finish();
                break;


        }
    }
}