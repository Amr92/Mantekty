package com.example.mante2ty.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mante2ty.Adapters.StoresAdapter;
import com.example.mante2ty.Models.StoresModel;
import com.example.mante2ty.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class StoresFragment extends Fragment {

    private RecyclerView storesRecycler;
    private StoresAdapter adapter;
    private LinearLayoutManager layoutManager;
    private TextView subCatTitle;
    private ArrayList<StoresModel.ResultBean> storesList = new ArrayList<>();
    private ProgressBar progressBar;
    private AsyncHttpClient client;

    private String subcategoryId;
    private int subCategoryId;
    private int START_PAGE = 1;
    private int CURRENT_PAGE = START_PAGE;
    private int totalItemsCount;
    private int firstVisibleItemPosition;
    private int visibleItemCount;
    private int previousTotal;
    private boolean isScrolling = true;
    private int view_threshold = 10;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stores, container, false);

        subCatTitle = view.findViewById(R.id.subCategory_title);
        storesRecycler = view.findViewById(R.id.rec_stores);
        progressBar = view.findViewById(R.id.stores_fragment_progressBar);

        Bundle b2 = getArguments();
        String title = b2.getString("subCategoryTitle");
        subCategoryId = b2.getInt("subCategoryId");

        if (b2 != null) {

            subCatTitle.setText(title);
        }
        pagination();
        parseJSON(subCategoryId);

        return view;
    }

    private void parseJSON(int subCatId) {

        subcategoryId = String.valueOf(subCatId);
        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/stores/category/44?subCategoryID=" +subcategoryId+ "&page" + START_PAGE;

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<StoresModel>() {
                }.getType();
                StoresModel sModel = new Gson().fromJson(responseString, dataType);
                storesList.addAll(sModel.getResult());

                progressBar.setVisibility(View.INVISIBLE);
                adapter = new StoresAdapter(storesList, getActivity());
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                storesRecycler.setLayoutManager(layoutManager);
                storesRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void pagination() {

        storesRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemsCount = layoutManager.getItemCount();
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if(dy > 0){

                    if (isScrolling) {
                        if (totalItemsCount > previousTotal) {
                            previousTotal = totalItemsCount;
                            isScrolling = false;
                        }
                    }

                    if (!isScrolling && (totalItemsCount-visibleItemCount) <= (firstVisibleItemPosition + view_threshold)) {
                        CURRENT_PAGE++;
                        getNext(subCategoryId);
                        isScrolling = true;
                    }
                }

            }
        });
    }

    private void getNext(int subCatId) {

        subcategoryId = String.valueOf(subCatId);
        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/stores/category/44?subCategoryID=" + subcategoryId + "&page=" + CURRENT_PAGE;

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<StoresModel>() {
                }.getType();
                StoresModel sModel = new Gson().fromJson(responseString, dataType);
                storesList.addAll(sModel.getResult());

                isScrolling = true;

                adapter = new StoresAdapter(storesList, getActivity());
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                storesRecycler.setLayoutManager(layoutManager);
                storesRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }

}
