package com.example.mante2ty.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Adapters.FavouriteRecyclerAdapter;
import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.Models.FavouriteItemInfo;
import com.example.mante2ty.Presenter.FavoriteListPresenter;
import com.example.mante2ty.Presenter.FavoriteListView;
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

public class FavouriteList extends AppCompatActivity implements FavoriteListView {

    @BindView(R.id.img_back_fav)
    ImageView imgBackFav;
    @BindView(R.id.progress_bar_fav)
    ProgressBar progressBarFav;
    private RecyclerView recFav;
    private FavouriteRecyclerAdapter adapter;
    private FavoriteListPresenter presenter;
    private ArrayList<FavouriteItemInfo.ResultBean> favList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_list);
        ButterKnife.bind(this);

        imgBackFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recFav = findViewById(R.id.rec_fav);

        SavedData data = new SavedData();
        String token = data.getSavedData(this).getAccess_token();

        presenter = new FavoriteListPresenter(this);
        presenter.getFavoriteList("Bearer " + token);

        //getFavouriteStores();

    }

    @Override
    public void onGetFavoriteList(List<FavouriteItemInfo.ResultBean> favList) {

        progressBarFav.setVisibility(View.INVISIBLE);

        adapter = new FavouriteRecyclerAdapter(favList, FavouriteList.this);
        recFav.setLayoutManager(new LinearLayoutManager(FavouriteList.this, LinearLayoutManager.VERTICAL, false));
        recFav.setAdapter(adapter);

        adapter.setOnItemClickListener(new FavouriteRecyclerAdapter.onItemClickListener() {
            @Override
            public void OnItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                favList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
    }

    /*private void getFavouriteStores() {

        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/favorites";

        SavedData data = new SavedData();
        String token = data.getSavedData(this).getAccess_token();

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization", "Bearer " + token);

        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<FavouriteItemInfo>() {
                }.getType();
                FavouriteItemInfo item = new Gson().fromJson(responseString, dataType);
                favList.addAll(item.getResult());

                progressBarFav.setVisibility(View.INVISIBLE);

                adapter = new FavouriteRecyclerAdapter(favList, FavouriteList.this);
                recFav.setLayoutManager(new LinearLayoutManager(FavouriteList.this, LinearLayoutManager.VERTICAL, false));
                recFav.setAdapter(adapter);

                adapter.setOnItemClickListener(new FavouriteRecyclerAdapter.onItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {

                    }

                    @Override
                    public void onDeleteClick(int position) {
                        favList.remove(position);
                        adapter.notifyItemRemoved(position);
                    }
                });
            }
        });
    }*/

}
