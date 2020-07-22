package com.example.mante2ty.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mante2ty.Models.StoresModel;
import com.example.mante2ty.R;
import com.example.mante2ty.ui.StoreDetails;

import java.util.ArrayList;
import java.util.List;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.PaginationViewHolder> {

    private ArrayList<StoresModel.ResultBean> stList;
    private Context context;

    public StoresAdapter(ArrayList<StoresModel.ResultBean> stList, Context context) {
        this.stList = stList;
        this.context = context;
    }

    @NonNull
    @Override
    public PaginationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rec_stores,parent,false);
        PaginationViewHolder vHolder = new PaginationViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaginationViewHolder holder, final int position) {

        holder.storeTitle.setText(stList.get(position).getTitle());

        if(stList.get(position).getLogo().isEmpty()){
            holder.storeImg.setImageResource(R.drawable.placeholder_image);
        }
        else{
            Glide.with(context).load(stList.get(position).getLogo()).into(holder.storeImg);
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, StoreDetails.class);
                intent.putExtra("storeId",stList.get(position).getId());
                intent.putExtra("storeName",stList.get(position).getTitle());
                intent.putExtra("latitude",stList.get(position).getLat());
                intent.putExtra("longitude",stList.get(position).getLng());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stList.size();
    }

    public class PaginationViewHolder extends RecyclerView.ViewHolder{
        TextView storeTitle;
        ImageView storeImg;
        LinearLayout layout;

        public PaginationViewHolder(@NonNull View itemView) {
            super(itemView);

            storeTitle = itemView.findViewById(R.id.title_store);
            storeImg = itemView.findViewById(R.id.img_store);
            layout = itemView.findViewById(R.id.linear_stores);
        }
    }

    public void add(StoresModel.ResultBean resultList){
        stList.add(resultList);
        notifyItemInserted(stList.size()-1);
    }

    public void addAll(List<StoresModel.ResultBean>stores){
        for(StoresModel.ResultBean st:stores){
            add(st);
        }
    }

    public void addBottomItem(){
        add(new StoresModel.ResultBean());
    }



}
