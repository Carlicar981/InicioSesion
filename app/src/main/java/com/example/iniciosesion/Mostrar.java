package com.example.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {
ListView lista;
darUsuario dar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);

        lista = (ListView) findViewById(R.id.lista);
        dar = new darUsuario(this);
        ArrayList<Usuario> lUsuarios = dar.selecUsuarios();
        ArrayList<String> list = new ArrayList<>();

        for (Usuario u: lUsuarios){
            list.add(u.getNombre()+" "+u.getApellidos());

        }
        ArrayAdapter<String>a=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        lista.setAdapter(a);


    }
}