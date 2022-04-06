package com.bolatovyernur.woopaysecondtask.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor prefsEditor;

    public PreferenceUtils(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefsEditor = prefs.edit();
    }
    public static void deleteData(){
        prefsEditor.clear().commit();
    }
    public static void saveString(String key, String value) {
        prefsEditor.putString(key, value).apply();
    }

    public static String getString(String key) {
        return prefs.getString(key, null);
    }
}