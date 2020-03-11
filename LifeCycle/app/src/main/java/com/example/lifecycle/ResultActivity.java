package com.example.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {


    Button backButton;
    TextView scoreResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        backButton = findViewById(R.id.backMenu);
        scoreResultView = findViewById(R.id.score_result);


        Intent fromCard = getIntent();
        int player = fromCard.getIntExtra("score_result",0);


        scoreResultView.setText(String.format(Locale.getDefault(), "%d 점", player));



        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                onBackPressed();


            }
        };

        backButton = findViewById(R.id.backMenu);
        backButton.setOnClickListener(listener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
