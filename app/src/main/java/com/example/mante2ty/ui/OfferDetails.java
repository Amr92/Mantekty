package com.example.mante2ty.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mante2ty.Models.OfferDetailsModel;
import com.example.mante2ty.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class OfferDetails extends AppCompatActivity {

    @BindView(R.id.offer_detail_title)
    TextView offerDetailTitle;
    @BindView(R.id.date_from)
    TextView dateFrom;
    @BindView(R.id.date_to)
    TextView dateTo;
    @BindView(R.id.offer_detail_img)
    ImageView offerDetailImg;
    @BindView(R.id.offer_detail_body)
    TextView offerDetailBody;
    @BindView(R.id.img_back_offer_detail)
    ImageView imgBackOfferDetail;
    @BindView(R.id.offer_store_img)
    ImageView offerStoreImg;
    @BindView(R.id.offer_store_title)
    TextView offerStoreTitle;
    LinearLayout storeOfferLinearLayout;

    private AsyncHttpClient client;
    OfferDetailsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        ButterKnife.bind(this);

        storeOfferLinearLayout = findViewById(R.id.store_offer_linear_layout);
        int offerId = getIntent().getIntExtra("offer_Id", 0);

        getDataFromJSON(offerId);

        imgBackOfferDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        storeOfferLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfferDetails.this,OfferStoreDetails.class);
                intent.putExtra("id",model.getResult().getStore().getId());
                intent.putExtra("title",model.getResult().getStore().getTitle());
                intent.putExtra("lat",model.getResult().getStore().getLat());
                intent.putExtra("lng",model.getResult().getStore().getLng());
                intent.putExtra("logo",model.getResult().getStore().getLogo());
                intent.putExtra("address_description",model.getResult().getStore().getAddress_description());
                startActivity(intent);
            }
        });

    }

    private void getDataFromJSON(int offerId) {

        String id = String.valueOf(offerId);
        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/offers/" + offerId;

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<OfferDetailsModel>() {
                }.getType();
                model = new Gson().fromJson(responseString, dataType);

                if (model.getResult().getImage() != null) {
                    Picasso.get().load(model.getResult().getImage()).into(offerDetailImg);
                } else {
                    offerDetailImg.setImageResource(R.mipmap.ic_launcher_round);
                }
                offerDetailTitle.setText(model.getResult().getTitle());
                offerDetailBody.setText(model.getResult().getBody());
                dateFrom.setText(model.getResult().getStart_date());
                dateTo.setText(model.getResult().getEnd_date());
                offerStoreTitle.setText(model.getResult().getStore().getTitle());
//              Picasso.get().load(model.getResult().getStore().getLogo()).into(offerStoreImg);
            }
        });
    }
}
