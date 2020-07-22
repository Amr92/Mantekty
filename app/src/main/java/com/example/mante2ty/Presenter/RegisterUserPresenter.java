package com.example.mante2ty.Presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.ui.Home;
import com.example.mante2ty.ui.Register;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class RegisterUserPresenter {

    private RegisterView view;
    private AsyncHttpClient client;
    private RequestParams params;
    private final String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/register";

    public RegisterUserPresenter(RegisterView view) {
        this.view = view;
    }

    public void registerNewUser(String email,String name,String phone,String password){

        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/register";
        params = new RequestParams();
        params.put("email", email);
        params.put("mobile", phone);
        params.put("name",name);
        params.put("password", password);
        params.put("device_token",22);
        params.put("device_type", 1);

        client = new AsyncHttpClient();
        client.addHeader("version","v1");
        client.addHeader("Accept","application/json");
        client.post(SERVER_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                view.onRegisterUser(responseString);
            }
        });

    }
}
