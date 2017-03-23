package com.apps.edu.myagenda;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("ACTIVITY-MAIN: Estamos en MAIN onCreate");
        agenda = new ArrayList<Contacto>();
        Contacto Edu = new Contacto("Edu", 34, "edu@gmail.com");
        agenda.add(Edu);
        //System.out.println("ACTIVITY-MAIN-CREATE: Contacto Edu " +agenda.get(0).toString());

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("ACTIVITY-MAIN: Estamos en MAIN onStart");

        for (int i=0; i<agenda.size();i++){
            System.out.println("ACTIVITY-MAIN-START: " +agenda.get(i).toString());
        }

    }

    public void cambiarActivityAñadir (View vi) {
        System.out.println("ACTIVITY-MAIN: Presionado el boton 1");
        setActivityForResult(AddActivity.class, 0);
    }

    public void cambiarActivityBuscar (View vi) {
        System.out.println("ACTIVITY-MAIN: Presionado el boton 2");
        setActivity(SearchActivity.class);
    }

    public void cambiarActivityVerTodos (View vi) {
        System.out.println("ACTIVITY-MAIN: Presionado el boton 3");
        setActivity(ViewAllActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("ACTIVITY-MAIN: Estamos en OnActivityResult");
        switch(requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                   agenda = (ArrayList<Contacto>) data.getSerializableExtra("Agenda");
                }
                break;
        }
    }

    private void setActivityForResult(Class clase,int codigoResultado){
        Intent intent = new Intent(this,clase);
        intent.putExtra("Agenda", agenda);
        startActivityForResult(intent,codigoResultado);
    }

    private void setActivity(Class clase){
        Intent intent = new Intent(this,clase);
        intent.putExtra("Agenda", agenda);
        startActivity(intent);
    }
}
