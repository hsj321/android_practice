package com.example.threadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Button start1Button;
    Button start2Button;
    TextView timeTextView1;
    TextView workingTextView1;
    ListView listView;
    String[] name = {"cow", "chicken", "pork"};
    int[] count = {0,0,0};
    int[] time = {0,0,0};


    private ArrayList<MainItem> itemList = new ArrayList<MainItem>() ;

    private static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainAdapter adapter;

        adapter = new MainAdapter(itemList);

        for(int i = 0; i < 3; i++){
            adapter.addItem(name[i], "채집시작", time[i], count[i]);
        }

        listView = findViewById(R.id.cage);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
