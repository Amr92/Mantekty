package com.example.mante2ty.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mante2ty.Adapters.HomeRecyclerAdapter;
import com.example.mante2ty.Adapters.ViewPagerAdapter;
import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.R;
import com.example.mante2ty.ui.Home;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class DepartmentsFragment extends Fragment {

    private RecyclerView recDept;
    private HomeRecyclerAdapter adapter;
    private AsyncHttpClient client;
    private ArrayList<ConfigsModel.ResultBean.CategoriesBean> allDept = new ArrayList<>();
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_departments, container, false);

        recDept = view.findViewById(R.id.rec_all_dept);
        progressBar = view.findViewById(R.id.all_dept_progress_bar);

        getDataFromJSON();
        return view;
    }

    private void getDataFromJSON() {

        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/configs";
        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<ConfigsModel>() {
                }.getType();
                ConfigsModel model = new Gson().fromJson(responseString, dataType);

                allDept.addAll(model.getResult().getCategories());

                progressBar.setVisibility(View.INVISIBLE);

                adapter = new HomeRecyclerAdapter(allDept, (Home) getActivity());
                recDept.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                recDept.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }

}
