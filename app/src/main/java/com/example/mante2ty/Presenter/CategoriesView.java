package com.example.mante2ty.Presenter;

import com.example.mante2ty.Models.ConfigsModel;

import java.util.List;

public interface CategoriesView {

    void onGetCategories(List<ConfigsModel.ResultBean.CategoriesBean> categoriesList,List<ConfigsModel.ResultBean.SlidersBean> sliderList);
}
