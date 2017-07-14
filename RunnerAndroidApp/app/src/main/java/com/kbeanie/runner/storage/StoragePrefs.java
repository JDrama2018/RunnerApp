package com.kbeanie.runner.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.kbeanie.runner.RunnerApplication;

/**
 * Created by vidushi on 19/6/17.
 */

public class StoragePrefs {

    private SharedPreferences sp;
    private final static String FILE = "sp";

    private final static String VENUE_ID = "venue_id";
    private final static String PASSWORD = "password";
    private final static String LOGGED_IN = "logged_in";

    public StoragePrefs(){
        sp = RunnerApplication.getContext().getSharedPreferences(FILE, Context.MODE_PRIVATE);
    }

    public String getVenueId(){
        return sp.getString(VENUE_ID, null);
    }

    public void setVenueId(String venueId){
        sp.edit().putString(VENUE_ID, venueId).apply();
    }

    public void setPassword(String password){
        sp.edit().putString(PASSWORD, password).apply();
    }

    public String getPassword(){
        return sp.getString(PASSWORD, null);
    }

    public boolean isLoggedIn(){
        return sp.getBoolean(LOGGED_IN, false);
    }

    public void setLoggedIn(boolean isLoggedIn){
        sp.edit().putBoolean(LOGGED_IN, isLoggedIn).apply();
    }
}
