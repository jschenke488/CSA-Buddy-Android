package com.kenschenke.csabuddy;

import android.content.Context;
import android.content.SharedPreferences;

public class Constants {
    public static SharedPreferences prefs;
    public static boolean isPrefsLoaded = false;

    public static void loadPrefs(Context ctx) {
        try {
            if (!Constants.isPrefsLoaded) {
                if (ctx == null) {
                    throw new Exception("Context is null");
                }
                Constants.prefs = ctx.getSharedPreferences("com.kenschenke.csabuddy", Context.MODE_PRIVATE);
                Constants.isPrefsLoaded = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
