package com.example.mante2ty.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Adapters.CommentRecAdapter;
import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.Models.GetCommentsModel;
import com.example.mante2ty.Models.StoreDetailsModel;
import com.example.mante2ty.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class OfferStoreDetails extends AppCompatActivity {

    @BindView(R.id.img_back_details)
    ImageView imgBackDetails;
    @BindView(R.id.favourite_img)
    ImageView favouriteImg;
    @BindView(R.id.circle_img_store_logo)
    CircleImageView circleImgStoreLogo;
    @BindView(R.id.store_detail_title)
    TextView storeDetailTitle;
    @BindView(R.id.view_photos)
    ImageView viewPhotos;
    @BindView(R.id.videos)
    ImageView videos;
    @BindView(R.id.go_to_map)
    TextView goToMap;
    @BindView(R.id.location_on_google_maps)
    ImageView locationOnGoogleMaps;
    @BindView(R.id.write_your_opinion)
    Button writeYourOpinion;
    @BindView(R.id.store_address)
    TextView storeAddress;
    @BindView(R.id.store_description)
    TextView storeDescription;
    @BindView(R.id.store_toolbar_title)
    TextView storeToolbarTitle;
    @BindView(R.id.text_view_store_title)
    TextView textViewStoreTitle;
    @BindView(R.id.working_hours)
    TextView workingHours;

    private RecyclerView recComments;
    private CommentRecAdapter adapter;
    private int storeId;
    private String lat,lng,storeName;
    private AsyncHttpClient client;
    private RequestParams params;
    private ArrayList<GetCommentsModel.ResultBean> commentsList = new ArrayList<>();
    private float rate;
    private String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        ButterKnife.bind(this);

        storeId = getIntent().getIntExtra("id", 0);
        storeName = getIntent().getStringExtra("title");
        lat = getIntent().getStringExtra("lat");
        lng = getIntent().getStringExtra("lng");

        Toolbar toolbar = findViewById(R.id.toolbar_details_page);
        setSupportActionBar(toolbar);
        storeToolbarTitle.setText(storeName);

        recComments = findViewById(R.id.rec_view_comments);

        imgBackDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        favouriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavouriteList();
            }
        });

        writeYourOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        goToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGoogleMap(lat,lng,storeName);
            }
        });


        parseJSON(storeId);
        getComments(storeId);
    }

    private void parseJSON(int id) {
        String stId = String.valueOf(id);
        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/stores/" + stId;

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");

        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<StoreDetailsModel>() {
                }.getType();
                StoreDetailsModel stModel = new Gson().fromJson(responseString, dataType);

                storeDetailTitle.setText(stModel.getResult().getTitle());
                textViewStoreTitle.setText(stModel.getResult().getTitle());
                storeAddress.setText(stModel.getResult().getAddress_description());
                workingHours.setText(stModel.getResult().getWorkDaysText());
                String logo = stModel.getResult().getLogo();
                if(logo.isEmpty()){
                    circleImgStoreLogo.setImageResource(R.mipmap.ic_launcher_round);
                }
                else{
                    Picasso.get().load(logo).into(circleImgStoreLogo);
                }

            }
        });
    }

    private void addToFavouriteList() {

        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/favorites";
        params = new RequestParams();
        params.put("store_id", storeId);

        SavedData data = new SavedData();
        String accessToken = data.getSavedData(this).getAccess_token();

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization", "Bearer " + accessToken);
        client.post(SERVER_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Toast.makeText(OfferStoreDetails.this, "Added successfully to favourite list", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(OfferStoreDetails.this);
        View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        final EditText addComment = view.findViewById(R.id.edit_text_comment);
        Button confirm = view.findViewById(R.id.post_comment);
        Button cancel = view.findViewById(R.id.cancel_comment);
        RatingBar bar = view.findViewById(R.id.comment_rating_bar);

        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                rate = rating;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comment = addComment.getText().toString();

                if (comment.isEmpty()) {
                    Toast.makeText(OfferStoreDetails.this, "Add comment first..", Toast.LENGTH_SHORT).show();
                } else {
                    postComment(storeId);
                    alertDialog.dismiss();
                }

            }
        });
        alertDialog.show();
    }


    private void postComment(int id) {

        String storeID = String.valueOf(id);
        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/stores/comments/" + storeID;

        params = new RequestParams();
        params.put("comment", comment);
        params.put("rate",rate);

        SavedData data = new SavedData();
        String accessToken = data.getSavedData(this).getAccess_token();

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization", "Bearer " + accessToken);
        client.post(SERVER_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Log.e("dear", responseString );
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Toast.makeText(OfferStoreDetails.this, "your Comment is posted successfully", Toast.LENGTH_SHORT).show();
                Log.d("dear", responseString);
            }
        });
    }

    private void getComments(int storeId) {

        String id = String.valueOf(storeId);
        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/stores/comments/" + id;

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<GetCommentsModel>(){}.getType();
                GetCommentsModel model = new Gson().fromJson(responseString,dataType);
                commentsList.addAll(model.getResult());

                adapter = new CommentRecAdapter(commentsList, OfferStoreDetails.this);
                recComments.setLayoutManager(new LinearLayoutManager(OfferStoreDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recComments.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void launchGoogleMap(String latitude,String longitude,String storeName) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:"+latitude+","+longitude+"?q="+storeName));
        intent.setPackage("com.google.android.apps.maps");

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }



}
