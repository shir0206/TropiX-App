package com.shirzabolotnyklein.tropix.gui;

import android.app.Application;
import android.content.Context;

public class ApplicationContextProvider extends Application {

    public static void setContext(Context context) {
        ApplicationContextProvider.context = context;
    }

    /**
     * Keeps a reference of the application context
     */
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }

    /**
     * Returns the application context
     *
     * @return application context
     */
    public static Context getContext() {
        return context;
    }

}