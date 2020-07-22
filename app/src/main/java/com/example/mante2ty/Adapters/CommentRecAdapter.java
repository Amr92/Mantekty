package com.example.mante2ty.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.Models.GetCommentsModel;
import com.example.mante2ty.R;

import java.util.ArrayList;
import java.util.List;

public class CommentRecAdapter extends RecyclerView.Adapter<CommentRecAdapter.CommentViewHolder> {

    private List<GetCommentsModel.ResultBean> comList;
    private Context context;

    public CommentRecAdapter(List<GetCommentsModel.ResultBean> comList, Context context) {
        this.comList = comList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_comments,parent,false);
        CommentViewHolder vH = new CommentViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        SavedData data = new SavedData();
        String name = data.getSavedData(context).getResult().getName();
        String image = data.getSavedData(context).getResult().getImage();
        holder.username.setText(name);
        holder.body.setText(comList.get(position).getComment());
        holder.rating.setText(String.valueOf(comList.get(position).getRate()));
        holder.createdAt.setText(comList.get(position).getCreated_at());

        if(image.contains("")){
            holder.userImg.setImageResource(R.drawable.placeholder_image);
        }else {
            Glide.with(context).load(comList.get(position).getImage()).into(holder.userImg);
        }
    }

    @Override
    public int getItemCount() {
        return comList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{

        TextView username,body,rating,createdAt;
        ImageView userImg;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            userImg = itemView.findViewById(R.id.comment_img);
            username = itemView.findViewById(R.id.comment_username);
            body = itemView.findViewById(R.id.comment_body);
            rating = itemView.findViewById(R.id.comment_rate);
            createdAt = itemView.findViewById(R.id.comment_created_at);
        }
    }
}
