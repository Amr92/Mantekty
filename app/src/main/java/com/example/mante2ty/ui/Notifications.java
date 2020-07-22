package com.example.mante2ty.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Adapters.NotificationsAdapter;
import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.Models.NotificationsModel;
import com.example.mante2ty.Presenter.NotificationsPresenter;
import com.example.mante2ty.Presenter.NotificationsView;
import com.example.mante2ty.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class Notifications extends AppCompatActivity implements NotificationsView {

    @BindView(R.id.img_back_notifications)
    ImageView imgBackNotifications;
    @BindView(R.id.rec_notification)
    RecyclerView recNotification;
    @BindView(R.id.progress_bar_notification)
    ProgressBar progressBarNotification;

    private NotificationsAdapter adapter;
    private NotificationsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        recNotification.setLayoutManager(new LinearLayoutManager(Notifications.this, LinearLayoutManager.VERTICAL, false));

        SavedData data = new SavedData();
        String token = data.getSavedData(this).getAccess_token();

        presenter = new NotificationsPresenter(this);
        presenter.getAllNotifications("Bearer " + token);

        imgBackNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onGetNotifications(List<NotificationsModel.ResultBean> notList) {

        if(notList != null && notList.size() > 0){

            progressBarNotification.setVisibility(View.INVISIBLE);
            adapter = new NotificationsAdapter(notList, Notifications.this);
            recNotification.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
