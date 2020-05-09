package com.example.pokedex.controleur;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Classe permettant de faire les requetes aux serveurs de base de donnÃ©es
 */
public class MyRequest {

    private Context context;
    private RequestQueue queue;

    // METTRE LE CHEMIN !!!!!!!!!!!!!!!
    private final String cheminServeur = "";

    /**
     * Constructeur de la classe
     * @param context, le contexte de l'application
     * @param queue, la file de requete
     */
    public MyRequest(Context context, RequestQueue queue){
        this.context=context;
        this.queue=queue;
    }

    /**
     * Methode permettant d'inscrire un nouvel utilisateur
     * @param pseudo, le pseudo de l'utilisateur
     * @param email, le mail entrÃ© par l'utilisateur
     * @param password, le mot de passe de l'utilisateur
     * @param password2, la confirmation de son mot de passe
     * @param callback, le traitement de la rÃ©ponse du serveur
     */
    public void register(final String pseudo, final String email, final String password, final String password2, final RegisterCallBack callback){

        String url = cheminServeur+"register.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Map<String,String> errors = new HashMap<>();
                Log.d("APP","**********************"+response);
                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if(!error){
                        //L'inscription s'est bien dÃ©roulÃ© si notre boolean error du PHP est Ã  faux
                        callback.onSuccess("Vous Ãªtes bien inscrit");
                    }else{
                        JSONObject messages = json.getJSONObject("message"); //on rÃ©cupÃ¨re la clÃ© message

                        //ON AJOUTE A MAP LES DIFFERENTS MESSAGES D'ERREURS
                        if(messages.has("pseudo")){
                            errors.put("pseudo",messages.getString("pseudo"));
                        }

                        if(messages.has("email")){
                            errors.put("email",messages.getString("email"));
                        }

                        if(messages.has("password")){
                            errors.put("password",messages.getString("password"));
                        }

                        callback.inputErrors(errors);
                    }

                } catch (JSONException e) {
                    Log.d("APP","**********************ERREUR DE PARSE JSON DE LA REPONSE PHP ");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("APP","**********************ERREUR"+error);

                if(error instanceof NetworkError){
                    callback.onError("Impossible de se connecter"); //s'il n'y a pas de rÃ©seau par exemple
                }else if (error instanceof VolleyError){
                    callback.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("pseudo", pseudo); // en php la clÃ© sert pour : $_POST['pseudo']
                map.put("email", email);
                map.put("password", password);
                map.put("password2", password2);
                return map;
            }
        };

        queue.add(request);

    }

    /**
     * Interface utilisÃ© par la mÃ©thode d'inscription pour gÃ©rer le retour du serveur
     */
    public interface RegisterCallBack{
        void onSuccess(String message);
        void inputErrors(Map<String, String> errors);//erreur liÃ© aux infos rentrÃ© par l'utilisateur
        void onError(String message); //erreur de connexion de json etc
    }


    /**
     * Methode permettant Ã  un utilisateur de se connecter
     * @param pseudo, pseudo de l'utilisateur
     * @param password, mot de passe entrÃ© par l'utilisateur
     * @param callback, le traitement de la rÃ©ponse du serveur
     */
    public void connection(final String pseudo, final String password, final LoginCallback callback){

        String url = cheminServeur+"login.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("APP","**********************"+response);

                JSONObject json = null;

                try {
                    json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if(!error){
                        String id = json.getString("id");
                        String pseudo = json.getString("pseudo");

                        //SI UNE CLASSE POUR USER
                        // User user = new User(id,pseudo);

                        callback.onSuccess(id,pseudo);


                    }else{
                        callback.onError(json.getString("message"));
                    }



                } catch (JSONException e) {
                    callback.onError("Une erreur s'est produite");
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("APP","**********************ERREUR"+error);

                if(error instanceof NetworkError){
                    callback.onError("Impossible de se connecter"); //s'il n'y a pas de rÃ©seau par exemple
                }else if (error instanceof VolleyError){
                    callback.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map <String,String> map = new HashMap<>();
                map.put("pseudo", pseudo); // en php la clÃ© sert pour : $_POST['pseudo']
                map.put("password", password);

                return map;
            }
        };

        queue.add(request);

    }

    /**
     * Interface utilisÃ© par la mÃ©thode de connection pour gÃ©rer le retour du serveur
     */
    public interface LoginCallback{
        void onSuccess(String id, String pseudo);
        //SI UNE CLASSE POUR USER
        // void onSuccess(User user);
        void onError(String message);
    }


}
