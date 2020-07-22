package com.example.mante2ty.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mante2ty.Adapters.SubCategoriesAdapter;
import com.example.mante2ty.Models.SubCategoryModel;
import com.example.mante2ty.Presenter.SubCategoriesPresenter;
import com.example.mante2ty.Presenter.SubCategoriesView;
import com.example.mante2ty.R;
import com.example.mante2ty.ui.Home;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SubCategoriesFragment extends Fragment implements SubCategoriesView {
    private RecyclerView subCatRecycler;
    private TextView deptTitle;
    private SubCategoriesAdapter adapter;
    private ProgressBar progressBar;
    private SubCategoriesPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sub_categories, container, false);

        subCatRecycler = rootView.findViewById(R.id.recycler_view_sub_category);
        deptTitle = rootView.findViewById(R.id.title_dep);
        progressBar = rootView.findViewById(R.id.sub_cat_progress_bar);

        Bundle b1 = getArguments();
        String d = b1.getString("categoryTitle");
        int catId = b1.getInt("categoryId");


        if(b1 != null){

            deptTitle.setText(d);
        }

        presenter = new SubCategoriesPresenter(this);
        presenter.parseJSON(catId);

        return rootView;
    }

    @Override
    public void onGetSubCategories(List<SubCategoryModel.ResultBean.SubCategoriesBean> subCatList) {
        progressBar.setVisibility(View.INVISIBLE);

        adapter = new SubCategoriesAdapter(subCatList,(Home)getActivity());
        subCatRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        subCatRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    /*public void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.nav_host_fragment, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }*/


}
