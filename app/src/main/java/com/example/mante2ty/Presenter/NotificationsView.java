package com.example.mante2ty.Presenter;

import com.example.mante2ty.Models.NotificationsModel;

import java.util.List;

public interface NotificationsView {

    void onGetNotifications(List<NotificationsModel.ResultBean> notList);
}
