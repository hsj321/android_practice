package com.hsjnetworkproject.networkproject;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hsjnetworkproject.networkproject.R;


public class MainActivity extends AppCompatActivity {

    Button searchButton;
    EditText nameText;
    EditText areaText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.search_name);
        areaText = findViewById(R.id.search_area);


        View.OnClickListener listener = new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.search_button:
                        Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                        searchIntent.putExtra("name", nameText.getText().toString());
                        searchIntent.putExtra("area", areaText.getText().toString());
                        startActivity(searchIntent);
                        break;
                }
            }
        };


        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(listener);
    }
}
