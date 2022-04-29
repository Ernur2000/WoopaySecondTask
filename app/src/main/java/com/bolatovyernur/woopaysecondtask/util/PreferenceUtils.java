package com.bolatovyernur.woopaysecondtask.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {
    private final SharedPreferences prefs;
    private final SharedPreferences.Editor prefsEditor;

    public PreferenceUtils(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefsEditor = prefs.edit();
    }

    public void saveString(String key, String value) { prefsEditor.putString(key, value).apply(); }

    public String getString(String key) {
        return prefs.getString(key, null);
    }

    public void deleteData(String s) { prefsEditor.remove(s).commit(); }

    public void deleteAllData() {
        prefsEditor.clear().commit();
    }
}