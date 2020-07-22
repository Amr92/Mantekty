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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {

    @BindView(R.id.img_back_login)
    ImageView imgBackLogin;
    @BindView(R.id.phone_login)
    EditText phoneLogin;
    @BindView(R.id.password_login)
    EditText passwordLogin;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    @BindView(R.id.skip_button)
    Button skipButton;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.new_registration)
    TextView newRegistration;

    private ProgressDialog pDialog;

    public static String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/login";
    private AsyncHttpClient client;
    private RequestParams params;
    private static String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        newRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        imgBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(Login.this);
                pDialog.show();
                pDialog.setContentView(R.layout.progress_dialog_layout);
                pDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                loginIntoAccount();
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    private void loginIntoAccount() {

        String phone = phoneLogin.getText().toString();
        String password = passwordLogin.getText().toString();

        if (validatePhone() && validatePassword()) {

            params = new RequestParams();
            params.put("username", phone);
            params.put("password", password);
            params.put("device_token", 22);
            params.put("device_type", 1);

            client = new AsyncHttpClient();
            client.addHeader("version", "v1");
            client.addHeader("Accept", "application/json");
            client.post(SERVER_URL, params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {

                    SharedPreferences.Editor myEdit = preferences.edit();
                    myEdit.putString("data", responseString);
                    myEdit.apply();

                    pDialog.dismiss();
                    final SavedData common = new SavedData();
                    common.getSavedData(Login.this);
                    Toast.makeText(Login.this, "User is logged in successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Login.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }

    }

    private boolean validatePassword() {

        String password = passwordLogin.getText().toString();
        if (password.isEmpty()) {

            passwordLogin.setError("please..Enter your password");
            return false;
        } else if (!Pattern.compile(PASSWORD_PATTERN).matcher(password).matches()) {
            passwordLogin.setError("Password is too weak");
            return false;
        } else {
            passwordLogin.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {

        String phone = phoneLogin.getText().toString();
        if (phone.isEmpty()) {

            phoneLogin.setError("please..Enter your Phone number");
            return false;
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            phoneLogin.setError("Phone number is not valid");
            return false;
        } else {
            phoneLogin.setError(null);
            return true;
        }
    }
}
