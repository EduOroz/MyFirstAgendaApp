package com.apps.edu.myagenda;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by Edu on 24/03/2017
 * Añadimos esta clase para que la agenda guarde en fichero la información de contactos
 */

public class AccesoFichero {
    private Context contexto;
    private String nombre;
    //se proporciona el contexto y el nombre del fichero
    //durante la creación del objeto
    public AccesoFichero(Context contexto, String nombre){
        this.contexto=contexto;
        this.nombre=nombre;
    }
    public void guardarContacto(Contacto contacto ){
        FileOutputStream fos= null;
        PrintStream out=null;
        try {
            //guardamos la nota en modo append para
            //añadirla a las ya existentes
            fos = contexto.openFileOutput(nombre, Context.MODE_APPEND);
            out=new PrintStream(fos);
            out.println(contacto.ContactoToFichero());
            //System.out.println("guardarContacto: " +contacto.ContactoToFichero());
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if(out!=null){
                out.close();
            }
        }
    }//fin método

    public ArrayList<Contacto> recuperarContactos(){
        ArrayList<Contacto> contactos=new ArrayList<>();
        FileInputStream fis=null;
        BufferedReader bf=null;
        try{
            fis=contexto.openFileInput(nombre);
            bf=new BufferedReader(new InputStreamReader(fis));
            String linea;
            String[] linea_separated;
            Contacto contacto;
            //recorremos linea por línea y extraemos los datos del contacto que estan separados por |
            //como texto y los convertimos a un objeto contacto
            while((linea=bf.readLine())!=null){
                linea_separated = linea.split(",");
                //System.out.println("RecuperarContactos, Nombre: " +linea_separated[0]);
                contacto = new Contacto(linea_separated[0],Integer.parseInt(linea_separated[1]),linea_separated[2]);
                contactos.add((contacto));
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return contactos;
    }//fin método

    public void limpiarFichero(){
        FileOutputStream fos= null;
        //para limpiar el fichero, basta con abrirlo
        //en modo sobrescritura y se eliminará todo
        //su contenido
        try {
            fos = contexto.openFileOutput(nombre, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }//fin método
} //fin clase
