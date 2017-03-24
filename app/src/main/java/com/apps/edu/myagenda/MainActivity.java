package com.apps.edu.myagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> agenda;
    AccesoFichero af;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        af = new AccesoFichero(this, "contactos.txt");

        System.out.println("ACTIVITY-MAIN: Estamos en MAIN onCreate");
        /*Incluimos este código para crear 2 primeros contactos sobre un fichero vacío
        af.limpiarFichero();
        //Añadimos 2 contactos por defecto a la aplicacion
        Contacto Edu = new Contacto("Edu", 34, "edu@gmail.com");
        af.guardarContacto(Edu);
        Contacto Juan = new Contacto("Juan", 30, "juan@gmail.com");
        af.guardarContacto(Juan);
        //Mostramos los contactos añadidos
        agenda = af.recuperarContactos();
        for (int i=0; i<agenda.size();i++){
            System.out.println("ACTIVITY-MAIN-START: " +agenda.get(i).toString());
        }
        */
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("ACTIVITY-MAIN: Estamos en MAIN onStart");
        //Mostramos los contactos por consola
        agenda = af.recuperarContactos();
        for (int i=0; i<agenda.size();i++){
            System.out.println("ACTIVITY-MAIN-START: " +agenda.get(i).ContactoToString());
        }

    }

    public void cambiarActivityAñadir (View vi) {
        System.out.println("ACTIVITY-MAIN: Presionado el boton 1");
        //setActivityForResult(AddActivity.class, 0);
        setActivity(AddActivity.class);
    }

    public void cambiarActivityBuscar (View vi) {
        System.out.println("ACTIVITY-MAIN: Presionado el boton 2");
        setActivity(SearchActivity.class);
    }

    public void cambiarActivityVerTodos (View vi) {
        System.out.println("ACTIVITY-MAIN: Presionado el boton 3");
        setActivity(ViewAllActivity.class);
    }

    private void setActivity(Class clase){
        Intent intent = new Intent(this,clase);
        //intent.putExtra("Agenda", agenda);
        startActivity(intent);
    }

    public void borrarAgenda (View vi){
        System.out.println("ACTIVITY-MAIN: Presionado el boton Borrar Agenda");
        af = new AccesoFichero(this, "contactos.txt");
        af.limpiarFichero();
    }

}
