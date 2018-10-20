package com.example.abl.algeriancitiesapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abl on 29/04/2018.
 */

public class sessionManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private final static String PREFS_NAME="app_prefs" ;
    private final static int PRIVATE_MODE =0;
    private final static String IS_LOGGED="is_logged";
    private final static String EMAIL="email";
    private final static String Type="typeevt";
    private Context context;

    public sessionManager(Context context){
        this.context=context;
        prefs=context.getSharedPreferences(PREFS_NAME,PRIVATE_MODE);
        editor=prefs.edit();
    }
    public Boolean islogged(){
        return prefs.getBoolean(IS_LOGGED,false);
    }
    public String  getEmail(){
        return prefs.getString(EMAIL,null);
    }
    public String getType(){
        return prefs.getString(Type,null);
    }
    public void insertUser(String pseudo){
        editor.putBoolean(IS_LOGGED,true);
        editor.putString(EMAIL,pseudo);
        editor.commit();

    }
    public void logout(){
        editor.clear().commit();
    }

}
