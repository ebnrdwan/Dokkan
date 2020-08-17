package com.engineering.dokkan.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.engineering.dokkan.utils.Constants;

public class SharedPreference {

    private SharedPreference() {
    }

    private static SharedPreferences instance;
private static SharedPreference sharedPreferenceInstance;
    public static SharedPreference getInstance(Context context) {

        if (sharedPreferenceInstance == null){
            instance = context.getSharedPreferences(Constants.SHARED_PREF_KEY, Context.MODE_PRIVATE);
            sharedPreferenceInstance = new SharedPreference();
        }


        return sharedPreferenceInstance;
    }


    public boolean isUserSaved() {
        return instance.contains(Constants.USER_KEY);
    }

    public void saveUser(String uuID) {
        instance.edit().putString(Constants.USER_KEY, uuID).apply();
    }

    public String getUser() {
        return instance.getString(Constants.USER_KEY, "");
    }
}
