package com.example.mante2ty.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mante2ty.Adapters.ContactUsAdapter;
import com.example.mante2ty.Adapters.HomeRecyclerAdapter;
import com.example.mante2ty.Adapters.ViewPagerAdapter;
import com.example.mante2ty.Classes.SavedData;
import com.example.mante2ty.Models.ConfigsModel;
import com.example.mante2ty.R;
import com.example.mante2ty.ui.Home;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import cz.msebera.android.httpclient.Header;

public class ContactUsFragment extends Fragment {


    TextView contactMail;
    TextView contactMobile,contactName;
    EditText writeMessage;
    Button sendMessage;

    private RecyclerView recContactUs;
    private ContactUsAdapter adapter;
    private ArrayList<String> contactList = new ArrayList<>();
    private AsyncHttpClient client;
    private RequestParams params;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);

        recContactUs = v.findViewById(R.id.rec_contact_us);
        contactMobile = v.findViewById(R.id.contact_mobile);
        contactMail = v.findViewById(R.id.contact_mail);
        contactName = v.findViewById(R.id.contact_name);
        writeMessage = v.findViewById(R.id.write_message);
        sendMessage = v.findViewById(R.id.contact_send);

        SavedData data = new SavedData();
        String email = data.getSavedData(getActivity()).getResult().getEmail();
        String mobile = data.getSavedData(getActivity()).getResult().getMobile();
        String name = data.getSavedData(getActivity()).getResult().getName();

        contactMail.setText(email);
        contactMobile.setText(mobile);
        contactName.setText(name);

        parseJSON();

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        return v;
    }

    private void parseJSON() {

        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/configs";
        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<ConfigsModel>() {
                }.getType();
                ConfigsModel model = new Gson().fromJson(responseString, dataType);

                contactList.addAll(model.getResult().getContactUsTypes());

                adapter = new ContactUsAdapter(contactList, getActivity());
                recContactUs.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                recContactUs.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }

    private void sendMessage() {
        String message = writeMessage.getText().toString();
        String SERVER_URL = "https://www.onnety-solutions.com/mante2tyNew/api/contactus";

        params = new RequestParams();
        params.put("_METHOD","POST");
        params.put("message",message);

        SavedData data = new SavedData();
        String access_token = data.getSavedData(getActivity()).getAccess_token();

        client = new AsyncHttpClient();
        client.addHeader("version", "v1");
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization","Bearer "+access_token);

        client.post(SERVER_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), "Failed to send", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getActivity(), "Your Message is successfully sent", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
