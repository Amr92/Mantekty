package com.example.mante2ty.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mante2ty.Adapters.CommentRecAdapter;
import com.example.mante2ty.Classes.RetrofitClient;
import com.example.mante2ty.Models.FavouriteItemInfo;
import com.example.mante2ty.Models.GetCommentsModel;
import com.example.mante2ty.Models.StoreDetailsModel;
import com.example.mante2ty.ui.StoreDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreDetailsPresenter {

    private StoreDetailsView view;
    private AsyncHttpClient client;
    private RequestParams params;
    private List<GetCommentsModel.ResultBean> commentsList = new ArrayList<>();

    public StoreDetailsPresenter(StoreDetailsView view) {
        this.view = view;
    }

    public void getStoreDetails(int storeId) {
        Call<StoreDetailsModel> call = RetrofitClient.getInstance().getApi().getStoreDetails(storeId);
        call.enqueue(new Callback<StoreDetailsModel>() {
            @Override
            public void onResponse(Call<StoreDetailsModel> call, Response<StoreDetailsModel> response) {
                if (response.isSuccessful()) {
                    StoreDetailsModel model = response.body();
                    view.onGetStoreDetails(model);
                }
            }

            @Override
            public void onFailure(Call<StoreDetailsModel> call, Throwable t) {

            }
        });
    }

    public void addToFavoriteList(int storeId, String accessToken) {

        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/favorites";
        params = new RequestParams();
        params.put("store_id", storeId);

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization", "Bearer " + accessToken);
        client.post(SERVER_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                view.onAddToFavorite();
            }
        });
    }

    public void postComment(int id, String comment, float rate, String accessToken) {

        String storeID = String.valueOf(id);
        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/stores/comments/" + storeID;

        params = new RequestParams();
        params.put("comment", comment);
        params.put("rate", rate);

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization", "Bearer " + accessToken);
        client.post(SERVER_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Log.e("dear", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                view.postComments();
            }
        });
    }

    public void getComments(int storeId) {
        Call<GetCommentsModel> call = RetrofitClient.getInstance().getApi().getCommentsList(storeId);
        call.enqueue(new Callback<GetCommentsModel>() {
            @Override
            public void onResponse(Call<GetCommentsModel> call, Response<GetCommentsModel> response) {
                GetCommentsModel model = response.body();
                commentsList.addAll(model.getResult());
                view.onGetComments(commentsList);
            }

            @Override
            public void onFailure(Call<GetCommentsModel> call, Throwable t) {

            }
        });
    }

}

