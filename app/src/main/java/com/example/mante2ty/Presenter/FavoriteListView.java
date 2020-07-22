package com.example.mante2ty.Presenter;

import com.example.mante2ty.Models.FavouriteItemInfo;

import java.util.List;

public interface FavoriteListView {

    void onGetFavoriteList(List<FavouriteItemInfo.ResultBean> favList);
}
