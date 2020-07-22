package com.example.mante2ty.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class Settings extends AppCompatActivity {

    @BindView(R.id.log_out)
    TextView logOut;
    @BindView(R.id.img_back_fav)
    ImageView imgBackFav;

    private AsyncHttpClient client;
    private RequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        imgBackFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void logout() {

        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/logout";
        SavedData data = new SavedData();
        String accessToken = data.getSavedData(this).getAccess_token();
        String phone = data.getSavedData(this).getResult().getMobile();
        String password = data.getSavedData(this).getResult().getEmail();

        params = new RequestParams();
        params.put("username", phone);
        params.put("password", password);
        params.put("device_token", 22);
        params.put("device_type", 1);

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization", "Bearer " + accessToken);
        client.get(SERVER_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear().apply();
                Intent intent = new Intent(Settings.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                Log.d("steady", responseString);
            }
        });


    }
}
