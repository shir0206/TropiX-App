package com.shirzabolotnyklein.tropix.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.shirzabolotnyklein.tropix.gui.ApplicationContextProvider;
import com.shirzabolotnyklein.tropix.model.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import static android.content.Context.MODE_PRIVATE;
import static com.shirzabolotnyklein.tropix.gui.ApplicationContextProvider.getContext;

public class Settings {


    //------------------------------------- Singleton ------------------------------------------
    private static Settings settings = null;

    private Settings() {
        this.context = ApplicationContextProvider.getContext();
    }

    public static Settings getSettings() {
        if (settings == null) {
            settings = new Settings();
        }
        return settings;
    }

    //------------------------------------- Language ------------------------------------------

    Context context;

    /**
     * Write to SharedPreferences file all Players status".
     * @param language
     */
    public void writeToFileLanguage(String language) {

        SharedPreferences.Editor editor = context.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("language", language);
        editor.apply();
    }

    // Load language saved in shared preference
    public String readFromFileLanguage() {
        SharedPreferences pref = context.getSharedPreferences("Settings", MODE_PRIVATE);
        String language = pref.getString("language", "");
        return language;
    }

}
