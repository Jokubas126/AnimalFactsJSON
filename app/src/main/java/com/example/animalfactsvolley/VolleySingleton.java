package com.example.animalfactsvolley;

import android.content.Context;


import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

//singleton for the Volley request queue
public class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private static Context cont;

    private VolleySingleton(Context context){
        cont = context;
        requestQueue = getRequestQueue();
    }

    // could be synchronized if it would need to work on a different thread
    public static  VolleySingleton getInstance(Context context){
        if(instance == null)
            instance = new VolleySingleton(context);
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(cont.getApplicationContext());
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
