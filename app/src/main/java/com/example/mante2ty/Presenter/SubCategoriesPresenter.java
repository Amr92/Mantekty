package com.example.mante2ty.Presenter;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mante2ty.Adapters.SubCategoriesAdapter;
import com.example.mante2ty.Classes.RetrofitClient;
import com.example.mante2ty.Models.SubCategoryModel;
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

public class SubCategoriesPresenter {

    private SubCategoriesView view;
    private List<SubCategoryModel.ResultBean.SubCategoriesBean> subCatList = new ArrayList<>();

    public SubCategoriesPresenter(SubCategoriesView view) {
        this.view = view;
    }

    public void parseJSON(int id) {

         Call<SubCategoryModel> call = RetrofitClient.getInstance().getApi().getSubCategories(id);
         call.enqueue(new Callback<SubCategoryModel>() {
             @Override
             public void onResponse(Call<SubCategoryModel> call, Response<SubCategoryModel> response) {
                 if(response.isSuccessful()){
                     SubCategoryModel model = response.body();
                     subCatList.addAll(model.getResult().getSubCategories());
                     view.onGetSubCategories(subCatList);
                 }
             }

             @Override
             public void onFailure(Call<SubCategoryModel> call, Throwable t) {

             }
         });

    }

}


    /*String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/categories/" + id;
        client = new AsyncHttpClient();
                client.addHeader("version","v1");
                client.get(SERVER_URL, null, new TextHttpResponseHandler() {
@Override
public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

        }

@Override
public void onSuccess(int statusCode, Header[] headers, String responseString) {

        Type dataType = new TypeToken<SubCategoryModel>(){}.getType();
        SubCategoryModel model = new Gson().fromJson(responseString,dataType);
        subCatList.addAll(model.getResult().getSubCategories());
        view.onGetSubCategories(subCatList);
        }
        });*/
