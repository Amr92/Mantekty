package com.example.mante2ty.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mante2ty.Fragments.AreaDialogFragment;
import com.example.mante2ty.Fragments.NeighborhoodDialogFragment;
import com.example.mante2ty.Fragments.ProvinceDialogFragment;
import com.example.mante2ty.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeArea extends AppCompatActivity implements NeighborhoodDialogFragment.onInputListener{

    RelativeLayout provinceLayout, areaLayout, neighborhoodLayout;
    public TextView areaText;
    public TextView provinceText;
    public TextView neighborText;
    @BindView(R.id.back_area)
    ImageView backArea;
    @BindView(R.id.select_area_button)
    Button selectAreaButton;
    String nText;
    public static SharedPreferences shPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_area);
        ButterKnife.bind(this);

        shPrefs = getSharedPreferences("shPrefs",MODE_PRIVATE);

        provinceText = findViewById(R.id.province);
        areaText = findViewById(R.id.area);
        neighborText = findViewById(R.id.neighborhood);

        backArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final FragmentManager fm = getSupportFragmentManager();
        final ProvinceDialogFragment pFragment = new ProvinceDialogFragment();

        provinceLayout = findViewById(R.id.select_province);
        provinceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pFragment.show(fm, "PF_tag");
            }
        });

        final AreaDialogFragment aFragment = new AreaDialogFragment();
        areaLayout = findViewById(R.id.select_area);
        areaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aFragment.show(fm, "AF_tag");
            }
        });

        final NeighborhoodDialogFragment nFragment = new NeighborhoodDialogFragment();
        neighborhoodLayout = findViewById(R.id.select_neighborhood);
        neighborhoodLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nFragment.show(fm, "NF_tag");
            }
        });


        selectAreaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ChangeArea.this,Home.class);
                intent.putExtra("neighborhood Text",nText);
                startActivity(intent);
            }
        });

    }

    @Override
    public void sendInput(String input) {
        nText = input;
        neighborText.setText(nText);

        SharedPreferences.Editor myEditor = shPrefs.edit();
        myEditor.putString("areaName", nText);
        myEditor.apply();
    }
}
