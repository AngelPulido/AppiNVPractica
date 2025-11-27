package com.example.appinvpractica.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private static final String PREFS = "nutrivida_prefs";
    private static final String KEY_TOKEN = "key_token";
    private static final String KEY_ROL = "key_rol";

    public static void saveToken(Context ctx, String token, String rol){
        SharedPreferences p = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        p.edit().putString(KEY_TOKEN, token).putString(KEY_ROL, rol).apply();
    }

    public static String getToken(Context ctx){
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    public static String getRol(Context ctx){
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY_ROL, null);
    }

    public static void clear(Context ctx){
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().clear().apply();
    }
}
