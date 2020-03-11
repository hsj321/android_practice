package com.example.lifecycle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CardActivity extends AppCompatActivity {

    CountDownTimer timer = null;
    Button pauseButton;
    Button resetButton;
    TextView timeView;
    TextView scoreView;
    int check =0;
    int score = 0;
    long timeInt = 5000;
    boolean timeStop = false;
    boolean finish = false;
    int cardNumber = (int)(Math.random()*16)+1;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;


    public void game(int num){
        //int num = (int)Math.random()*16+1;
        switch(num) {
            case 1:
                button1.setBackgroundResource(R.color.black);
                break;
            case 2:
                button2.setBackgroundResource(R.color.black);
                break;
            case 3:
                button3.setBackgroundResource(R.color.black);
                break;
            case 4:
                button4.setBackgroundResource(R.color.black);
                break;
            case 5:
                button5.setBackgroundResource(R.color.black);
                break;
            case 6:
                button6.setBackgroundResource(R.color.black);
                break;
            case 7:
                button7.setBackgroundResource(R.color.black);
                break;
            case 8:
                button8.setBackgroundResource(R.color.black);
                break;
            case 9:
                button9.setBackgroundResource(R.color.black);
                break;
            case 10:
                button10.setBackgroundResource(R.color.black);
                break;
            case 11:
                button11.setBackgroundResource(R.color.black);
                break;
            case 12:
                button12.setBackgroundResource(R.color.black);
                break;
            case 13:
                button13.setBackgroundResource(R.color.black);
                break;
            case 14:
                button14.setBackgroundResource(R.color.black);
                break;
            case 15:
                button15.setBackgroundResource(R.color.black);
                break;
            case 16:
                button16.setBackgroundResource(R.color.black);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        scoreView = findViewById(R.id.score);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);



        game(cardNumber);



        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()){
                    case R.id.pause_button :
                        check = 1;
                        onBackPressed();
                        break;
                    case R.id.reset:
                        timer.cancel();
                        timeInt = 5000;
                        scoreView.setText(String.format(Locale.getDefault(), "%d 점", 0));
                        timer.start();
                    case R.id.button:
                        if(cardNumber == 1){
                            button1.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button2:
                        if(cardNumber == 2){
                            button2.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button3:
                        if(cardNumber == 3){
                            button3.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button4:
                        if(cardNumber ==4){
                            button4.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button5:
                        if(cardNumber == 5){
                            button5.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button6:
                        if(cardNumber == 6){
                            button6.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button7:
                        if(cardNumber == 7){
                            button7.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button8:
                        if(cardNumber == 8){
                            button8.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button9:
                        if(cardNumber == 9){
                            button9.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button10:
                        if(cardNumber == 10){
                            button10.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button11:
                        if(cardNumber == 11){
                            button11.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button12:
                        if(cardNumber == 12){
                            button12.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button13:
                        if(cardNumber == 13){
                            button13.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button14:
                        if(cardNumber == 14){
                            button14.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button15:
                        if(cardNumber == 15){
                            button15.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                    case R.id.button16:
                        if(cardNumber == 16){
                            button16.setBackgroundResource(R.color.white);
                            score++;
                            scoreView.setText(String.format(Locale.getDefault(), "%d 점", score));
                            cardNumber = (int)(Math.random()*16)+1;
                            game(cardNumber);
                        }
                        break;
                }
            }
        };


        timeView = findViewById(R.id.time);
        pauseButton = findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(listener);
        resetButton = findViewById(R.id.reset);
        resetButton.setOnClickListener(listener);

        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        button10.setOnClickListener(listener);
        button11.setOnClickListener(listener);
        button12.setOnClickListener(listener);
        button13.setOnClickListener(listener);
        button14.setOnClickListener(listener);
        button15.setOnClickListener(listener);
        button16.setOnClickListener(listener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        timer = new CountDownTimer(timeInt, 1000) {
            @Override
            public void onTick(long l) {
                timeInt = l;
                timeView.setText(String.format(Locale.getDefault(), "%d sec.", l/1000L));

                if(timeStop == true){

                    timeStop = false;
                    //cancel();
                }
            }

            @Override
            public void onFinish() {

                finish = true;

                finish();

            }

        }.start();

        timeView = findViewById(R.id.time);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timeStop = true;
        timer.cancel();
        timeView = findViewById(R.id.time);
        timeView.setText(String.format(Locale.getDefault(), "%d sec.", timeInt/1000L));

        if(finish == true){

            SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String text = String.valueOf(score);
            editor.putString("bestScore",text);
            editor.commit();

            Intent resultintent = new Intent(getApplicationContext(), ResultActivity.class);
            resultintent.putExtra("score_result", score);
            startActivity(resultintent);
            finish = false;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(check == 1) {
            check = 0;
        } else {
            onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent pauseintent = new Intent(getApplicationContext(),PauseActivity.class);
        pauseintent.putExtra("score", score);
        startActivityForResult(pauseintent,2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

            finish();
        }
    }
}
