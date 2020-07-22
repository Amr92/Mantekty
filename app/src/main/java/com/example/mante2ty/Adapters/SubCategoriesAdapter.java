package com.example.mante2ty.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Fragments.StoresFragment;
import com.example.mante2ty.Fragments.SubCategoriesFragment;
import com.example.mante2ty.Models.SubCategoryModel;
import com.example.mante2ty.R;
import com.example.mante2ty.ui.Home;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.SubCategoriesViewHolder> {

    private List<SubCategoryModel.ResultBean.SubCategoriesBean> subCatList;
    private Home home;
    private SubCategoryModel model;

    public SubCategoriesAdapter(List<SubCategoryModel.ResultBean.SubCategoriesBean> subCatList, Home home) {
        this.subCatList = subCatList;
        this.home = home;
    }

    @NonNull
    @Override
    public SubCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_sub_categories, parent, false);
        SubCategoriesViewHolder holder = new SubCategoriesViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoriesViewHolder holder, final int position) {

        holder.subCatTitle.setText(subCatList.get(position).getTitle());
        if(subCatList.get(position).getImage() != null){
            Picasso.get().load((String) subCatList.get(position).getImage()).into(holder.subCatImg);
        }else{
            holder.subCatImg.setImageResource(R.drawable.placeholder_image);
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Home activity = (Home) v.getContext();
                StoresFragment fragment = new StoresFragment();

                String title = subCatList.get(position).getTitle();
                int id = subCatList.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putString("subCategoryTitle", title);
                bundle.putInt("subCategoryId", id);
                fragment.setArguments(bundle);

                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

    }


    @Override
    public int getItemCount() {
        return subCatList.size();
    }

    public class SubCategoriesViewHolder extends RecyclerView.ViewHolder {

        ImageView subCatImg;
        TextView subCatTitle;
        ConstraintLayout relativeLayout;

        public SubCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            subCatImg = itemView.findViewById(R.id.img_sub_category);
            subCatTitle = itemView.findViewById(R.id.title_sub_category);
            relativeLayout = itemView.findViewById(R.id.relative_sub_category);
        }

    }
}
