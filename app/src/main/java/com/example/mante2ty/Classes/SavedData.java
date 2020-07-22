package com.example.mante2ty.Classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mante2ty.Models.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SavedData {

    public static SharedPreferences prefs;
    public static boolean checkData;

    public UserInfo getSavedData(Context context) {

        prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String shPrefsString = prefs.getString("data", "userData");

        UserInfo user = new UserInfo();

        if (shPrefsString.equals("userData")) {

            checkData = false;

        } else {

            checkData = true;

            Type dataType = new TypeToken<UserInfo>() {
            }.getType();
            user = new Gson().fromJson(shPrefsString, dataType);

        }
        return user;


    }


}
