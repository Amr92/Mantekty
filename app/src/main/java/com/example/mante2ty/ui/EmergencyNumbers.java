package com.example.mante2ty.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.mante2ty.Adapters.EmergencyRecyclerAdapter;
import com.example.mante2ty.Adapters.HomeRecyclerAdapter;
import com.example.mante2ty.Adapters.ViewPagerAdapter;
import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class EmergencyNumbers extends AppCompatActivity {

    private ImageView back;
    private RecyclerView emgRecycler;
    private ArrayList<ConfigsModel.ResultBean.EmergencyPlacesBean> emgList = new ArrayList<>();
    private EmergencyRecyclerAdapter adapter;
    private AsyncHttpClient client;
    private String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/configs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_numbers);

        emgRecycler = findViewById(R.id.emergency_recyclerView);

        back = findViewById(R.id.img_back_emergency);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getDataFromJSON();
    }

    private void getDataFromJSON(){

        client = new AsyncHttpClient();
        client.addHeader("version","v1");
        client.get(SERVER_URL,null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<ConfigsModel>(){}.getType();
                ConfigsModel model = new Gson().fromJson(responseString,dataType);
                emgList.addAll(model.getResult().getEmergencyPlaces());
                adapter = new EmergencyRecyclerAdapter(emgList,EmergencyNumbers.this);
                emgRecycler.setLayoutManager(new LinearLayoutManager(EmergencyNumbers.this,
                        LinearLayoutManager.VERTICAL,false));
                emgRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }
}
