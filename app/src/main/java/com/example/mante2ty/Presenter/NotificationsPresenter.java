package com.example.mante2ty.Presenter;

import com.example.mante2ty.Classes.RetrofitClient;
import com.example.mante2ty.Models.NotificationsModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsPresenter {

    private NotificationsView view;
    private List<NotificationsModel.ResultBean> notList = new ArrayList<>();

    public NotificationsPresenter(NotificationsView view) {
        this.view = view;
    }

    public void getAllNotifications(String token){

        Call<NotificationsModel> call = RetrofitClient.getInstance().getApi().getNotifications(token);
        call.enqueue(new Callback<NotificationsModel>() {
            @Override
            public void onResponse(Call<NotificationsModel> call, Response<NotificationsModel> response) {
                NotificationsModel model = response.body();
                notList.addAll(model.getResult());
                view.onGetNotifications(notList);
            }

            @Override
            public void onFailure(Call<NotificationsModel> call, Throwable t) {

            }
        });

    }
}
