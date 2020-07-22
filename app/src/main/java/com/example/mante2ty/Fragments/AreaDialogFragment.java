package com.example.mante2ty.Fragments;

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

public class AreaDialogFragment extends DialogFragment {

    private String[] areaList = new String[]{"El Mokattam"};
    private ListView areaListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_dialog_fragment_area,container);

        areaListView = rootView.findViewById(R.id.area_list_view);
        AreaRecyclerAdapter adapter = new AreaRecyclerAdapter(getActivity(),areaList);
        areaListView.setAdapter(adapter);

        areaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameArea = areaList[position];
                ((ChangeArea)getActivity()).areaText.setText(nameArea);
                getDialog().dismiss();
            }
        });

        return rootView;

    }

}
