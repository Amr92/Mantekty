package com.example.mante2ty.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Adapters.CommentRecAdapter;
import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.Models.GetCommentsModel;
import com.example.mante2ty.Models.StoreDetailsModel;
import com.example.mante2ty.Presenter.StoreDetailsPresenter;
import com.example.mante2ty.Presenter.StoreDetailsView;
import com.example.mante2ty.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StoreDetails extends AppCompatActivity implements StoreDetailsView {

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
    @BindView(R.id.call_imageView)
    ImageView callImageView;

    private RecyclerView recComments;
    private CommentRecAdapter adapter;
    private int storeId;
    private String lat, lng, storeName, accessToken, mobileNumbers;
    private float rate;
    private String comment;
    private StoreDetailsPresenter presenter;
    public static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        ButterKnife.bind(this);

        storeId = getIntent().getIntExtra("storeId", 0);
        storeName = getIntent().getStringExtra("storeName");
        lat = getIntent().getStringExtra("latitude");
        lng = getIntent().getStringExtra("longitude");

        Toolbar toolbar = findViewById(R.id.toolbar_details_page);
        setSupportActionBar(toolbar);
        storeToolbarTitle.setText(storeName);

        SavedData data = new SavedData();
        accessToken = data.getSavedData(this).getAccess_token();

        presenter = new StoreDetailsPresenter(this);
        presenter.getStoreDetails(storeId);

        recComments = findViewById(R.id.rec_view_comments);
        recComments.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        imgBackDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        favouriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToFavoriteList(storeId, accessToken);
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
                launchGoogleMap(lat, lng, storeName);
            }
        });

        presenter.getComments(storeId);

        viewPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StoreDetails.this, "No Available Photos", Toast.LENGTH_SHORT).show();
            }
        });

        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StoreDetails.this, "No Available Videos", Toast.LENGTH_SHORT).show();
            }
        });

        callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog2();
            }
        });
    }

    @Override
    public void onGetStoreDetails(StoreDetailsModel model) {

        if (model != null) {

            storeDetailTitle.setText(model.getResult().getTitle());
            textViewStoreTitle.setText(model.getResult().getTitle());
            storeAddress.setText(model.getResult().getAddress_description());
            workingHours.setText(model.getResult().getWorkDaysText());
            String logo = model.getResult().getLogo();
            if (logo.isEmpty()) {
                circleImgStoreLogo.setImageResource(R.drawable.placeholder_image);
            } else {
                Picasso.get().load(logo).into(circleImgStoreLogo);
            }
            mobileNumbers = model.getResult().getMobile();
        }

    }

    @Override
    public void onAddToFavorite() {
        Toast.makeText(StoreDetails.this, "Added successfully to Favorite list", Toast.LENGTH_SHORT).show();
    }

    private void openDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(StoreDetails.this);
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

                if (TextUtils.isEmpty(comment)) {
                    Toast.makeText(StoreDetails.this, "Add comment first..", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.postComment(storeId, comment, rate, accessToken);
                    alertDialog.dismiss();
                }

            }
        });
        alertDialog.show();
    }

    @Override
    public void postComments() {
        Toast.makeText(StoreDetails.this, "your Comment is posted successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetComments(List<GetCommentsModel.ResultBean> commentsList) {
        adapter = new CommentRecAdapter(commentsList, StoreDetails.this);
        recComments.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void launchGoogleMap(String latitude, String longitude, String storeName) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" + latitude + "," + longitude + "?q=" + storeName));
        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void openDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StoreDetails.this);
        View view = getLayoutInflater().inflate(R.layout.custom_dialog_call_numbers, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        AppCompatTextView numbers = view.findViewById(R.id.call_details_number);

        if (mobileNumbers == null) {
            Toast.makeText(StoreDetails.this, "No Available Mobile Numbers", Toast.LENGTH_SHORT).show();
        } else {
            numbers.setText(mobileNumbers);
            alertDialog.show();
        }

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });


    }

    private void makePhoneCall() {

        if (ContextCompat.checkSelfPermission(StoreDetails.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(StoreDetails.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + mobileNumbers;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            }else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
