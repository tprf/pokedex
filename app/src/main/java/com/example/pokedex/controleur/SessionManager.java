package com.example.pokedex.controleur;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    /**
     * Classe permmettant de gerer la session de l'utilisateur
     */
        private SharedPreferences prefs;
        private SharedPreferences.Editor editor;
        private final static String PREFS_NAME = "app_prefs";
        private final static int PRIVATE_MODE=0;
        private final static String IS_LOGGED = "isLogged";
        private final static String PSEUDO = "pseudo";
        private final static String ID = "id";
        private Context context;


        /**
         * Constructeur de la classe
         * @param context, le contexte de l'application
         */
        public SessionManager(Context context){
            this.context=context;
            prefs= context.getSharedPreferences(PREFS_NAME, PRIVATE_MODE);
            editor = prefs.edit();
        }

        /**
         * Getter permmettant de savoir si un utilisateur est conectÃ©
         * @return true si un utilisateur est connectÃ©, false sinon
         */
        public boolean isLogged(){
            return prefs.getBoolean(IS_LOGGED,false);
        }

        /**
         * Getter retournant le pseudo de l'utilisateur connectÃ©
         * @return
         */
        public String getPseudo(){
            return prefs.getString(PSEUDO,null);
        }

        /**
         * Getter retournant l'id de l'utilisateur connectÃ©
         * @return
         */
        public String getId(){
            return prefs.getString(ID,null);
        }


        /**
         * Methode permettant d'ajouter les informations de l'utilisateur Ã  la session
         * @param id, id de l'utilisateur dont il faut dÃ©marrer la session
         * @param pseudo, pseudo de l'utilisateur dont il faut dÃ©marrer la session
         */
        public void insertUser(String id, String pseudo){
            editor.putBoolean(IS_LOGGED,true);
            editor.putString(ID,id);
            editor.putString(PSEUDO,pseudo);
            editor.commit(); //commit les changements
        }

        /**
         * MÃ©thode permettant de fermer une session correctement
         */
        public void logout(){
            editor.clear().commit();
        }


    }
