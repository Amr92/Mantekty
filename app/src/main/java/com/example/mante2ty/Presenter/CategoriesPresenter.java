package com.example.mante2ty.Presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mante2ty.Adapters.HomeRecyclerAdapter;
import com.example.mante2ty.Adapters.ViewPagerAdapter;
import com.example.mante2ty.Classes.RetrofitClient;
import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.ui.Home;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesPresenter {

    private CategoriesView view;
    private List<ConfigsModel.ResultBean.CategoriesBean> categoryList = new ArrayList<>();
    private List<ConfigsModel.ResultBean.SlidersBean> imgList = new ArrayList<>();

    public CategoriesPresenter(CategoriesView view) {
        this.view = view;
    }

    public void getDataFromJSON() {

        Call<ConfigsModel> call = RetrofitClient.getInstance().getApi().getCategories();
        call.enqueue(new Callback<ConfigsModel>() {
            @Override
            public void onResponse(Call<ConfigsModel> call, Response<ConfigsModel> response) {
                if(response.isSuccessful()){
                    ConfigsModel model = response.body();
                    categoryList.addAll(model.getResult().getCategories());
                    imgList.addAll(model.getResult().getSliders());
                    view.onGetCategories(categoryList,imgList);
                }

            }

            @Override
            public void onFailure(Call<ConfigsModel> call, Throwable t) {

            }
        });
    }
}
