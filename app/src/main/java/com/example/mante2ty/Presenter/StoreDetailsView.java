package com.example.mante2ty.Presenter;

import com.example.mante2ty.Models.GetCommentsModel;
import com.example.mante2ty.Models.StoreDetailsModel;

import java.util.List;

public interface StoreDetailsView {

    void onGetStoreDetails(StoreDetailsModel model);
    void onAddToFavorite();
    void postComments();
    void onGetComments(List<GetCommentsModel.ResultBean> commentsList);
}
