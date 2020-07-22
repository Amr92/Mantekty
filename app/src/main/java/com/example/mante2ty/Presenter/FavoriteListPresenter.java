package com.example.mante2ty.Presenter;

import com.example.mante2ty.Classes.RetrofitClient;
import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.Models.FavouriteItemInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteListPresenter {

    private FavoriteListView view;
    private List<FavouriteItemInfo.ResultBean> favList = new ArrayList<>();

    public FavoriteListPresenter(FavoriteListView view) {
        this.view = view;
    }

    public void getFavoriteList(String accessToken){
        Call<FavouriteItemInfo> call = RetrofitClient.getInstance().getApi().getFavoriteList(accessToken);
        call.enqueue(new Callback<FavouriteItemInfo>() {
            @Override
            public void onResponse(Call<FavouriteItemInfo> call, Response<FavouriteItemInfo> response) {
                if(response.isSuccessful()){
                    FavouriteItemInfo model = response.body();
                    favList.addAll(model.getResult());
                    view.onGetFavoriteList(favList);
                }
            }

            @Override
            public void onFailure(Call<FavouriteItemInfo> call, Throwable t) {

            }
        });
    }
}
