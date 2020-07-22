package com.example.mante2ty.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.R;

import java.util.ArrayList;

public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsAdapter.ContactUsViewHolder> {

    private ArrayList<String> contactUsTypes;
    private Context context;

    public ContactUsAdapter(ArrayList<String> contactUsTypes, Context context) {
        this.contactUsTypes = contactUsTypes;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactUsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_contact_us, parent, false);
        ContactUsViewHolder vH = new ContactUsViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactUsViewHolder holder, int position) {

        holder.contactTypeTitle.setText(contactUsTypes.get(position));

    }

    @Override
    public int getItemCount() {
        return contactUsTypes.size();
    }

    public class ContactUsViewHolder extends RecyclerView.ViewHolder{

        TextView contactTypeTitle;

        public ContactUsViewHolder(@NonNull View itemView) {
            super(itemView);

            contactTypeTitle = itemView.findViewById(R.id.contact_us_type_title);
        }
    }
}
