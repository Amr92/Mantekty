package com.example.mante2ty.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class ProvinceDialogFragment extends DialogFragment {

    private String[] provinceList = new String[]{"Cairo"};
   // private RecyclerView provinceRec;
    //private AreaRecyclerAdapter adapter;
    public onInputListener mOnInputListener;
    private ListView provinceListView;

    public interface onInputListener{
        void sendInput(String input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_dialog_fragment_province,container);

        provinceListView = rootView.findViewById(R.id.province_list_view);
        AreaRecyclerAdapter adapter = new AreaRecyclerAdapter(getActivity(),provinceList);
        provinceListView.setAdapter(adapter);

        provinceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String namePro = provinceList[position];
                ((ChangeArea)getActivity()).provinceText.setText(namePro);
               // mOnInputListener.sendInput(name);
                getDialog().dismiss();
            }
        });

        return rootView;

    }

   /* @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mOnInputListener = (onInputListener) getActivity();

        }catch (ClassCastException e){

        }
    }*/

}
