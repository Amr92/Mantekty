
package com.example.mante2ty.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mante2ty.Adapters.HomeRecyclerAdapter;
import com.example.mante2ty.Adapters.ViewPagerAdapter;
import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.Presenter.CategoriesPresenter;
import com.example.mante2ty.Presenter.CategoriesView;
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

public class HomeFragment extends Fragment implements CategoriesView {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView recCategory;
    private HomeRecyclerAdapter homeAdapter;
    private CategoriesPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        recCategory = view.findViewById(R.id.recycler_view_home);
        viewPager = view.findViewById(R.id.viewPager_top_home);
        progressBar = view.findViewById(R.id.home_progress);

        presenter = new CategoriesPresenter(this);
        presenter.getDataFromJSON();

        return view;
    }

    @Override
    public void onGetCategories(List<ConfigsModel.ResultBean.CategoriesBean> categoriesList, List<ConfigsModel.ResultBean.SlidersBean> sliderList) {
        progressBar.setVisibility(View.INVISIBLE);

        homeAdapter = new HomeRecyclerAdapter(categoriesList, (Home) getActivity());
        recCategory.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recCategory.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();

        adapter = new ViewPagerAdapter(getActivity(), sliderList);
        viewPager.setAdapter(adapter);
    }

    /*@Override
    public void OnItemClick(int position) {
        SubCategoriesFragment fragment = new SubCategoriesFragment();
        String title = categoryList.get(position).getTitle();
        int id = categoryList.get(position).getId();

        Bundle b = new Bundle();
        b.putString("categoryTitle", title);
        b.putInt("categoryId", id);
        fragment.setArguments(b);

        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment fragment) {
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
