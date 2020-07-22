package com.example.mante2ty.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mante2ty.Classes.RetrofitClient;
import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.Models.UserInfo;
import com.example.mante2ty.Presenter.RegisterUserPresenter;
import com.example.mante2ty.Presenter.RegisterView;
import com.example.mante2ty.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    @BindView(R.id.img_back_register)
    ImageView imgBackRegister;
    @BindView(R.id.email_register)
    EditText emailRegister;
    @BindView(R.id.phone_register)
    EditText phoneRegister;
    @BindView(R.id.password_register)
    EditText passwordRegister;
    Button registerButton;
    @BindView(R.id.username_register)
    EditText usernameRegister;
    @BindView(R.id.confirm_password_register)
    EditText confirmPasswordRegister;
    private ProgressDialog progressDialog;

    private static String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private AsyncHttpClient client;
    private RequestParams params;
    public static SharedPreferences prefs;
    private final String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        registerButton = findViewById(R.id.register_button);
        prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        imgBackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(Register.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog_layout);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {

        String email = emailRegister.getText().toString();
        String name = usernameRegister.getText().toString();
        String phone = phoneRegister.getText().toString();
        String password = passwordRegister.getText().toString();

        if (validateEmail() && validateUserName() && validatePhone() && validatePassword()) {

            params = new RequestParams();
            params.put("email", email);
            params.put("mobile", phone);
            params.put("name",name);
            params.put("password", password);
            params.put("device_token",22);
            params.put("device_type", 1);

            client = new AsyncHttpClient();
            client.addHeader("version","v1");
            client.addHeader("Accept","application/json");
            client.post(SERVER_URL, params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {

                    SharedPreferences.Editor myEditor = prefs.edit();
                    myEditor.putString("data", responseString);
                    myEditor.apply();

                    SavedData common = new SavedData();
                    common.getSavedData(Register.this);
                    progressDialog.dismiss();
                    Toast.makeText(Register.this, "User is registered successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Register.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });

        }

    }

    private boolean validateEmail() {

        String email = emailRegister.getText().toString();
        if (email.isEmpty()) {
            emailRegister.setError("Please enter your Email...");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailRegister.setError("please..Enter a valid E-Mail address");
            return false;
        } else {
            emailRegister.setError(null);
            return true;
        }

    }

    private boolean validateUserName() {

        String name = usernameRegister.getText().toString();
        if (name.isEmpty()) {

            usernameRegister.setError("please..Enter your name");
            return false;
        } else if (name.length() <= 5) {
            usernameRegister.setError("User name is too short");
            return false;
        } else {
            usernameRegister.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {

        String password = passwordRegister.getText().toString();
        String confirmedPassword = confirmPasswordRegister.getText().toString();
        if (password.isEmpty()) {

            passwordRegister.setError("please..Enter your password");
            return false;
        } else if (!Pattern.compile(PASSWORD_PATTERN).matcher(password).matches()) {
            passwordRegister.setError("Password is too weak");
            return false;
        }

       else if(!confirmedPassword.equals(password)){

            confirmPasswordRegister.setError("confirmed password should be identical to original password");
            return false;
        }
        else {
            passwordRegister.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {

        String phone = phoneRegister.getText().toString();
        if (phone.isEmpty()) {

            phoneRegister.setError("please..Enter your Phone number");
            return false;
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            phoneRegister.setError("Phone number is not valid");
            return false;
        } else {
            phoneRegister.setError(null);
            return true;
        }
    }

}


    /*Map<String,Object> map = new HashMap<>();
            map.put("email", email);
                    map.put("mobile", phone);
                    map.put("name",name);
                    map.put("password", password);
                    map.put("device_token",22);
                    map.put("device_type", 1);

                    Call<UserInfo> call = RetrofitClient.getInstance().getApi().registerUser(map);
        call.enqueue(new Callback<UserInfo>() {
@Override
public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

        SharedPreferences.Editor myEditor = prefs.edit();
        myEditor.putString("data", response.body().toString());
        myEditor.apply();

        SavedData common = new SavedData();
        common.getSavedData(Register.this);
        progressDialog.dismiss();
        Toast.makeText(Register.this, "User is registered successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Register.this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        }

@Override
public void onFailure(Call<UserInfo> call, Throwable t) {

        }
        });*/


