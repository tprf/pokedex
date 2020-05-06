package com.example.pokedex;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Singleton pour accéder à la base de donnée lors de l'inscription et/ou la connexion d'un utilisateur
 */
public class VolleySingleton {

    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    /**
     * Constructeur de la classe VolleySingleton
     * @param context, le contexte coura t de l'application
     */
    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    /**
     * Methode permettant de récupérer l'instance de la classe, soit elle existe déjà et on récupere celle qui existe soit on en créé une.
     * "synchronized" = pour spécifier qu'un seul thread peut accéder à cette méthode à la fois
     * @param context
     * @return
     */
    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    /**
     * Permet de récupérer la liste des requetes de l'instance
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

}
