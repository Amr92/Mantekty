package com.example.mante2ty.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Fragments.StoresFragment;
import com.example.mante2ty.Fragments.SubCategoriesFragment;
import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.R;
import com.example.mante2ty.ui.Home;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder> {

    private List<ConfigsModel.ResultBean.CategoriesBean> categoriesList;
    private Home home;
    /*private static onItemClickListener mListener;

    public interface onItemClickListener{
        void OnItemClick(int position);
    }

    public static void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }*/

    public HomeRecyclerAdapter(List<ConfigsModel.ResultBean.CategoriesBean> categoriesList, Home home) {
        this.categoriesList = categoriesList;
        this.home = home;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_home, parent, false);
        HomeViewHolder viewHolder = new HomeViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, final int position) {
        holder.catTitle.setText(categoriesList.get(position).getTitle());
        if (categoriesList.get(position).getImage() == null) {
            holder.catImg.setImageResource(R.drawable.placeholder_image);
        } else {
            Picasso.get().load(String.valueOf(categoriesList.get(position).getImage())).into(holder.catImg);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Home activity = (Home) v.getContext();
                SubCategoriesFragment fragment = new SubCategoriesFragment();

                String title = categoriesList.get(position).getTitle();
                int id = categoriesList.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putString("categoryTitle", title);
                bundle.putInt("categoryId", id);
                fragment.setArguments(bundle);

                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        ImageView catImg;
        TextView catTitle;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            catImg = itemView.findViewById(R.id.img_categories);
            catTitle = itemView.findViewById(R.id.title_categories);

           /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });*/
        }
    }
}
