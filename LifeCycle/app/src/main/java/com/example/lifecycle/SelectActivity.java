package com.example.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SelectActivity extends AppCompatActivity {

    Button cardButton;
    Button logoutButton;
    TextView playerView;
    TextView scoreView;
    TextView bestView;
    boolean best = false;
    int totalScore = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        System.out.println("onCreate");

        Intent getMainIntent = getIntent();
        String player = getMainIntent.getStringExtra("player");
        playerView = findViewById(R.id.player);
        playerView.setText(player);






        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.card:
                        Intent cardintent = new Intent(getApplicationContext(), CardActivity.class);
                        startActivity(cardintent);
                        break;
                    case R.id.logout:
                        Intent logoutintent = new Intent(getApplicationContext(), MainActivity.class);
                        logoutintent.putExtra("player", playerView.getText().toString());
                        logoutintent.putExtra("count", 1);
                        startActivity(logoutintent);
                        finish();
                }
            }
        };

        cardButton = findViewById(R.id.card);
        cardButton.setOnClickListener(listener);
        logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(listener);
        scoreView = findViewById(R.id.total_score);
        //scoreView.setText("0");
        bestView = findViewById(R.id.best_player);

        SharedPreferences nameMemo = getSharedPreferences("recordFile",MODE_PRIVATE);
        String nameTemp = nameMemo.getString("bestplayer","no");
        System.out.println(nameTemp);
        bestView.setText(nameTemp);




    }




    @Override
    protected void onStart() {
        System.out.println("onStart");
        super.onStart();

        scoreView = findViewById(R.id.total_score);

        SharedPreferences memo = getSharedPreferences("sFile",MODE_PRIVATE);
        String temp = memo.getString("bestScore","0");
        if(Integer.valueOf(temp) > Integer.valueOf(scoreView.getText().toString())){
            scoreView.setText(temp);
        }

        SharedPreferences bestShared = getSharedPreferences("bestFile", MODE_PRIVATE);
        String besttext = bestShared.getString("bestOfBest", "0");
        System.out.println(besttext);
        System.out.println(temp);
        if(Integer.valueOf(temp) > Integer.valueOf(besttext)){
            best = true;
        }



    }

    @Override
    protected void onRestart() {
        System.out.println("onRestart");
        super.onRestart();
        //Intent a = getIntent();
    }

    @Override
    protected void onResume() {
        System.out.println("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
        if(best == true) {

            System.out.println("ininini");
            SharedPreferences nameShared = getSharedPreferences("recordFile", MODE_PRIVATE);
            SharedPreferences.Editor nameEditor = nameShared.edit();
            String text = playerView.getText().toString();
            nameEditor.putString("bestplayer", text);
            nameEditor.commit();

            SharedPreferences bestShared = getSharedPreferences("bestFile", MODE_PRIVATE);
            SharedPreferences.Editor bestEditor = bestShared.edit();
            String besttext = scoreView.getText().toString();
            bestEditor.putString("bestOfBest", besttext);
            bestEditor.commit();
            best = false;
        }
    }

    @Override
    protected void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();


        SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String text = "0";
        editor.putString("bestScore",text);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}