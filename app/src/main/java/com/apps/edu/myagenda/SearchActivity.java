package com.apps.edu.myagenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<Contacto> agenda;
    AccesoFichero af;

    //Función para crear alertas
    private void crearAlerta(String texto){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setMessage(texto);
        dialogo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                dialogo.cancel();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                finish();
            }
        });
        dialogo.show();
    }

    //Función para mostrar textos emergentes
    private void mostrarTexto(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlinear);
    }

    public void cambiarActivityMain (View vi) {
        System.out.println("ACTIVITY-Search: Presionado el boton Volver");
        this.finish();
    }

    public void buscarEmail (View vi) {
        System.out.println("ACTIVITY-Search: Presionado el boton Buscar");

        //Recupero la agenda que está en fichero
        af = new AccesoFichero(this, "contactos.txt");
        agenda = af.recuperarContactos();
        //Recupero el email a buscar
        EditText email = (EditText) findViewById(R.id.etEmailSearch);

        boolean encontrado = false;
        if(email.getText().length() > 0){
            //Hacemos un for each contacto en la agenda comprobando si el correo es el que buscamos
            for (Contacto contacto : agenda){
                if(contacto.getEmail().equalsIgnoreCase(email.getText().toString())){
                    mostrarTexto(contacto.toString());
                    encontrado = true;
                }
            }
            if(!encontrado){
                crearAlerta("No se ha encontrado el contacto solicitado " + "\n");
            }
        }
        else {
            mostrarTexto("Introduce el correo a buscar");
        }

    }
}
