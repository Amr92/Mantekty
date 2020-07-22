package com.example.mante2ty.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.R;
import com.example.mante2ty.ui.Home;
import com.example.mante2ty.ui.OfferDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersVH> {

    private ArrayList<ConfigsModel.ResultBean.OffersBean> offersBeans;
    private Context context;

    public OffersAdapter(ArrayList<ConfigsModel.ResultBean.OffersBean> offersBeans, Context context) {
        this.offersBeans = offersBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public OffersVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_offers,parent,false);
        OffersVH viewHolder = new OffersVH(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OffersVH holder, final int position) {

        holder.offerTitle.setText(offersBeans.get(position).getTitle());

        if(offersBeans.get(position).getImage() != null){
            Picasso.get().load(offersBeans.get(position).getImage()).into(holder.offerImg);
        }
        else{
            holder.offerImg.setImageResource(R.drawable.placeholder_image);
        }

        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, OfferDetails.class);
                intent.putExtra("offer_Id",offersBeans.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offersBeans.size();
    }

    public class OffersVH extends RecyclerView.ViewHolder{

        CircleImageView offerImg;
        TextView offerTitle,offerBody;
        CardView relative;

        public OffersVH(@NonNull View itemView) {
            super(itemView);

            offerImg = itemView.findViewById(R.id.img_offer);
            offerTitle = itemView.findViewById(R.id.offer_title);
            offerBody = itemView.findViewById(R.id.offer_body);
            relative = itemView.findViewById(R.id.relative_offers);
        }
    }
}
