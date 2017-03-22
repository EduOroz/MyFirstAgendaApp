package com.apps.edu.myagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {

    private ArrayList<Contacto> agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        System.out.println("ACTIVITY-ViewAll: OnCreate");

        //Recupero el intent
        Intent intent = getIntent();
        //Recupero la agenda
        agenda = (ArrayList<Contacto>)intent.getSerializableExtra("Agenda");
        TextView salida = (TextView) findViewById(R.id.tvSalida);
        String aux="";

        if(agenda.isEmpty()){
            salida.setText("No hay contactos en la agenda");
        } else {
            for (int i = 0; i < agenda.size(); i++) {
                System.out.println("ACTIVITY-ViewAll - Contacto " + i + ":" + agenda.get(i).toString());
                aux += "Contacto " + i + ":" + agenda.get(i).toString() +"\n";
            }

            salida.setText(aux);
        }

    }

    public void cambiarActivityMain (View vi) {
        System.out.println("ACTIVITY-ViewAll: Presionado el boton Volver");
        this.finish();
    }

}
