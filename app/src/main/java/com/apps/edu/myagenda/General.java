package com.apps.edu.myagenda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Edu on 22/03/2017.
 */

public class General {

    public static void setActivity(Class c1, Context c) {
        Intent intent = new Intent(c, c1);
        c.startActivity(intent);

    }

    public static void pasarAgenda(ArrayList<Contacto> agenda, Class c1, Context context){
        Intent intent = new Intent(context, c1);
        intent.putExtra("agenda", agenda);
        context.startActivity(intent);
    }

}
