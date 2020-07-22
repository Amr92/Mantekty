package com.example.mante2ty.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.mante2ty.R;

public class AppRating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_rating);

        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +getPackageName()));
            startActivity(intent);

        }catch (ActivityNotFoundException e){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" +getPackageName()));
            startActivity(intent);
        }

    }
}
