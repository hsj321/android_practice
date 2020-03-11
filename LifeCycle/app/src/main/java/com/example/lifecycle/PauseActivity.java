package com.example.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PauseActivity extends AppCompatActivity {


    Button restartButton;
    Button endButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.restart:
                        onBackPressed();
                        finish();
                        break;
                    case R.id.end:
                        Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                        System.out.println("inininiin");
                        setResult(RESULT_OK,intent);
                        finish();
                        break;
                }
            }
        };

        restartButton = findViewById(R.id.restart);
        restartButton.setOnClickListener(listener);
        endButton = findViewById(R.id.end);
        endButton.setOnClickListener(listener);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
