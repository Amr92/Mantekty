package com.example.mante2ty.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mante2ty.R;

public class AreaRecyclerAdapter extends ArrayAdapter<String> {

    private String[] proTxt;
    private Context context;
   // private static onItemClickListener mListener;

   /* public interface onItemClickListener{
        void OnItemClick(int position);
    }

    public static void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }*/

    public AreaRecyclerAdapter(@NonNull Context context, String[] proTxt) {
        super(context, R.layout.item_area_rec,proTxt);
        this.proTxt = proTxt;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_area_rec,null);
        }
        TextView areaName = convertView.findViewById(R.id.txt_item_area);
        areaName.setText(proTxt[position]);
        return convertView;
    }

    /*public class AreaViewHolder extends RecyclerView.ViewHolder{

        TextView provinceText;
        LinearLayout linear;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);

            provinceText = itemView.findViewById(R.id.txt_item_area);
            linear = itemView.findViewById(R.id.area_click_linear);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }*/
}
