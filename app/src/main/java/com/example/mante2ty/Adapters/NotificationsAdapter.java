package com.example.mante2ty.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Models.NotificationsModel;
import com.example.mante2ty.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    private List<NotificationsModel.ResultBean> notList;
    private Context context;

    public NotificationsAdapter(List<NotificationsModel.ResultBean> notList, Context context) {
        this.notList = notList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_notifications,parent,false);
        NotificationsViewHolder vH = new NotificationsViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {

        holder.body.setText(notList.get(position).getBody());

        holder.createdAt.setText(notList.get(position).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return notList.size();
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder{

        TextView body,createdAt;

        public NotificationsViewHolder(@NonNull View itemView) {
            super(itemView);

            body = itemView.findViewById(R.id.notification_body);
            createdAt = itemView.findViewById(R.id.notification_created_at);
        }
    }
}
