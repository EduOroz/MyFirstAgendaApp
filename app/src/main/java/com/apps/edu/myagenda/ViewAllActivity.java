package com.apps.edu.myagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {

    private ArrayList<Contacto> agenda;
    private ListView lvViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        System.out.println("ACTIVITY-ViewAll: OnCreate");

        //Recupero el intent
        Intent intent = getIntent();
        //Recupero la agenda
        agenda = (ArrayList<Contacto>)intent.getSerializableExtra("Agenda");

        //Código para introducir el array de contactos en el TextView
        /*TextView salida = (TextView) findViewById(R.id.tvSalida);
        String aux="";

        if(agenda.isEmpty()){
            salida.setText("No hay contactos en la agenda");
        } else {
            for (int i = 0; i < agenda.size(); i++) {
                System.out.println("ACTIVITY-ViewAll - Contacto " + i + ":" + agenda.get(i).toString());
                aux += "Contacto " + i + ":" + agenda.get(i).toString() +"\n";
            }

            salida.setText(aux);
        }*/

        //Código para mostrar el array de contactos en un ListView
        //Creamos un Adapter y le pasamos el array
        ArrayAdapter<Contacto> adapter= new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, agenda);
        lvViewAll = (ListView)this.findViewById(R.id.lvViewAll);
        lvViewAll.setAdapter(adapter);

        //Creamos un listener para cuando hagamos click en un contacto nos muestre en un toast sus datos
        lvViewAll.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        Toast.makeText(ViewAllActivity.this,
                                agenda.get(position).ContactoToString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void cambiarActivityMain (View vi) {
        System.out.println("ACTIVITY-ViewAll: Presionado el boton Volver");
        this.finish();
    }

}
