package com.example.mante2ty.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.R;
import com.example.mante2ty.ui.Home;

import java.util.ArrayList;
import java.util.List;

public class EmergencyRecyclerAdapter extends RecyclerView.Adapter<EmergencyRecyclerAdapter.EmergencyViewHolder> {

    private ArrayList<ConfigsModel.ResultBean.EmergencyPlacesBean> emergencyNumbersList;
    private Context context;

    public EmergencyRecyclerAdapter(ArrayList<ConfigsModel.ResultBean.EmergencyPlacesBean> emergencyNumbersList, Context context) {
        this.emergencyNumbersList = emergencyNumbersList;
        this.context = context;
    }

    @NonNull
    @Override
    public EmergencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_emergency,parent,false);
        EmergencyViewHolder viewHolder = new EmergencyViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyViewHolder holder, int position) {

        holder.emgText.setText(emergencyNumbersList.get(position).getTitle());
        holder.emgNumber.setText(emergencyNumbersList.get(position).getPhone());
        holder.img.setImageResource(R.drawable.telephone);

    }

    @Override
    public int getItemCount() {
        return emergencyNumbersList.size();
    }

    public class EmergencyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView emgNumber;
        TextView emgText;

        public EmergencyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_telephone);
            emgNumber = itemView.findViewById(R.id.text_number);
            emgText = itemView.findViewById(R.id.text_word);
        }
    }


}
