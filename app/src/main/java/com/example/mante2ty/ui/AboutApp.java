package com.example.mante2ty.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mante2ty.Adapters.HomeRecyclerAdapter;
import com.example.mante2ty.Adapters.ViewPagerAdapter;
import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;

import cz.msebera.android.httpclient.Header;

public class AboutApp extends AppCompatActivity {

    private ImageView img;
    private TextView appEmail,appPhone,appFacebook,appGoogle;
    private AsyncHttpClient client;
    private String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/configs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        img = findViewById(R.id.img_back_about);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        appEmail = findViewById(R.id.app_email);
        appPhone = findViewById(R.id.app_phone);
        appFacebook = findViewById(R.id.app_facebook);
        appGoogle = findViewById(R.id.app_google);

        getDataFromJSON();
    }

    private void getDataFromJSON() {

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<ConfigsModel>() {
                }.getType();
                ConfigsModel model = new Gson().fromJson(responseString, dataType);

                appEmail.setText(model.getResult().getConfig().getEmail());
                appPhone.setText(model.getResult().getConfig().getMobile());
                appFacebook.setText(model.getResult().getConfig().getFacebook());
                appGoogle.setText(model.getResult().getConfig().getGoogle());

            }
        });
    }
}
