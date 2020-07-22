package com.example.mante2ty.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Adapters.OffersAdapter;
import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import cz.msebera.android.httpclient.Header;

public class OffersFragment extends Fragment {

    private RecyclerView offerRecView;
    private OffersAdapter adapter;
    private AsyncHttpClient client;
    private ArrayList<ConfigsModel.ResultBean.OffersBean> offerList = new ArrayList<>();
    private ProgressBar loadingBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_offers, container, false);

        offerRecView = view.findViewById(R.id.offers_rec_view);
        loadingBar = view.findViewById(R.id.offer_progress_bar);

        parseJSON();

        return view;
    }

    private void parseJSON() {

        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/configs";
        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<ConfigsModel>(){}.getType();
                ConfigsModel model = new Gson().fromJson(responseString,dataType);
                offerList.addAll(model.getResult().getOffers());

                loadingBar.setVisibility(View.INVISIBLE);
                adapter = new OffersAdapter(offerList,getActivity());
                offerRecView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                offerRecView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
