package com.example.mante2ty.Presenter;

import com.example.mante2ty.Models.SubCategoryModel;

import java.util.List;

public interface SubCategoriesView {

    void onGetSubCategories(List<SubCategoryModel.ResultBean.SubCategoriesBean> subCatList);
}
