package com.example.mante2ty.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Adapters.AreaRecyclerAdapter;
import com.example.mante2ty.R;
import com.example.mante2ty.ui.ChangeArea;

import java.util.ArrayList;
import java.util.List;

public class NeighborhoodDialogFragment extends DialogFragment {

    private String[] neigborhoodList = new String[]{"Street 9","El Kesm","El Hadaba El Wosta","Sobhy Hussein","Karim Banona"};
    //private RecyclerView provinceRec;
    //private AreaRecyclerAdapter adapter;
    private ListView neighborhoodListView;
   public onInputListener mOnInputListener;
    public interface onInputListener{
        void sendInput(String input);
    }

    String name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_dialog_fragment_neighborhood,container);

        neighborhoodListView = rootView.findViewById(R.id.neighborhood_list_view);
        AreaRecyclerAdapter adapter = new AreaRecyclerAdapter(getActivity(),neigborhoodList);
        neighborhoodListView.setAdapter(adapter);

        neighborhoodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = neigborhoodList[position];
                //((ChangeArea)getActivity()).neighborText.setText(name);
                mOnInputListener.sendInput(name);
                getDialog().dismiss();
            }
        });

        return rootView;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mOnInputListener = (onInputListener) getActivity();

        }catch (ClassCastException e){

        }
    }
}
