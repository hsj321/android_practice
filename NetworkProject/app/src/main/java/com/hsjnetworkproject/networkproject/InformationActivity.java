package com.hsjnetworkproject.networkproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.hsjnetworkproject.networkproject.R;

import org.w3c.dom.Text;


public class InformationActivity extends AppCompatActivity {

    TextView nameText;
    WebView imageWeb;
    TextView idText;
    TextView dateText;
    TextView areaText;
    Button shopButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        final Intent intent = getIntent();

        nameText = findViewById(R.id.inf_name);
        imageWeb = findViewById(R.id.inf_image);
        idText = findViewById(R.id.inf_id);
        dateText = findViewById(R.id.inf_date);
        areaText = findViewById(R.id.inf_area);
        shopButton = findViewById(R.id.inf_shop);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.inf_shop:
                        Intent shopIntent = new Intent(getApplicationContext(), ShopActivity.class);
                        shopIntent.putExtra("catalog", intent.getStringExtra("catalog"));
                        startActivity(shopIntent);
                        break;
                }
            }
        };

        shopButton.setOnClickListener(listener);

        nameText.setText(intent.getStringExtra("name"));

        imageWeb.setFocusable(false);
        WebSettings webSettings = imageWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (imageWeb != null) {
            imageWeb.setFocusable(false);
            imageWeb.loadUrl(intent.getStringExtra("image"));
        }
        idText.setText(String.format("관리ID : %s", intent.getStringExtra("id")));
        dateText.setText(String.format("습득날짜 : %s", intent.getStringExtra("date")));
        areaText.setText(String.format("보관장소 : %s", intent.getStringExtra("area")));



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
