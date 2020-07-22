package com.example.mante2ty.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Models.FavouriteItemInfo;
import com.example.mante2ty.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavouriteRecyclerAdapter extends RecyclerView.Adapter<FavouriteRecyclerAdapter.FavViewHolder> {

    private List<FavouriteItemInfo.ResultBean> favList;
    private Context context;
    private onItemClickListener mListener;

    public interface onItemClickListener{
        void OnItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.mListener = listener;
    }

    public FavouriteRecyclerAdapter(List<FavouriteItemInfo.ResultBean> favList, Context context) {
        this.favList = favList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_favourite,parent,false);
        FavViewHolder vH = new FavViewHolder(view,mListener);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {

        holder.favStoreTitle.setText(favList.get(position).getTitle());

        if(favList.get(position).getLogo().isEmpty()){
            holder.favStoreImg.setImageResource(R.drawable.placeholder_image);
        }else{
            Picasso.get().load(favList.get(position).getLogo()).into(holder.favStoreImg);
        }
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    public static class FavViewHolder extends RecyclerView.ViewHolder{

        ImageView favStoreImg;
        TextView favStoreTitle;
        ImageView deleteImage;

        public FavViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);

            favStoreImg = itemView.findViewById(R.id.store_img_fav);
            favStoreTitle = itemView.findViewById(R.id.store_title_fav);
            deleteImage = itemView.findViewById(R.id.remove_from_fav_list);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }

                }
            });

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
