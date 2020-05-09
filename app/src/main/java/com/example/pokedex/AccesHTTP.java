package com.example.pokedex;

import android.os.AsyncTask;
import android.util.Log;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AccesHTTP extends AsyncTask<String, Integer,Long> {

    private ArrayList<NameValuePair> parametres;
    private String ret = null; // variable de retour
    public AsyncResponse delegate = null; // par délégation on va exécuter une méthode à l'extérieur du Thread alors qu'on est dans un Thread DONC PASSAGE PAR INTERFACE

    /**
     * Constructeur
     */
    public AccesHTTP(){

        parametres = new ArrayList<NameValuePair>();

    }


    /**
     * Ajout d'un parametre POST
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur){

        parametres.add(new BasicNameValuePair(nom,valeur));
    }


    /**
     * Connexion en tâche de fond dans un Thread séparé, l'application ne se bloque pas
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {

        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]); // TP10 YT, 12min


        try {
            //Encodage des parametres
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
            //Connexion et envoie de l'URL complete (de parametres) et attente de réponse
            HttpResponse reponse = cnxHttp.execute(paramCnx); //tant qu'il n'y a pas de réponse l'ordi bloque

            //Transformation de la chaine de la réponse
            ret = EntityUtils.toString(reponse.getEntity());

        } catch (UnsupportedEncodingException e) {
            Log.d("erreur encodage", "***********************"+e.toString());
        } catch (ClientProtocolException e) {
            Log.d("erreur protocole", "***********************"+e.toString());
        } catch (IOException e) {
            Log.d("erreur I/O", "***********************"+e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result){
        if(ret != null)
            delegate.processFinish((ret.toString())); //TP10 YT 24min
    }



}//fin classe
