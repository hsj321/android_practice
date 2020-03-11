package com.example.listviewproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MypageActivity extends AppCompatActivity {

    private ListView mainListView = null;
    private int[] posterNum = {R.drawable.poster1,R.drawable.poster2,R.drawable.poster3,R.drawable.poster4,R.drawable.poster5,R.drawable.poster6, R.drawable.poster7, R.drawable.poster8};
    private int[] nameNumber = {R.string.name1, R.string.name2, R.string.name3, R.string.name4, R.string.name5, R.string.name6, R.string.name7, R.string.name8};
    private int[] starNumber = {1,2,3,4,5,1,2,3};
    private int movieCount = posterNum.length;
    private List<Drawable > posterList;
    private List<String > nameList;
    private List<Integer> countList;
    private MypageAdapter mainAdapter;
    private Button removeButton;
    private Button starAbcButton;
    private Button mainButton;
    private Button returnButton;
    private Button resetButton;
    private  ArrayList<MainItem> itemList = new ArrayList<MainItem>() ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        mainAdapter = new MypageAdapter(itemList);

        Intent getMovie = getIntent();


        //System.out.println(getMovie.getStringExtra("name"));

        posterList = new ArrayList<Drawable>();
        nameList = new ArrayList<String>();
        countList = new ArrayList<Integer>();


        /*
        for(int i = 0; i < posterNum.length; i++){
            posterList.add(ContextCompat.getDrawable(this,posterNum[i]));
            nameList.add(getString(nameNumber[i]));
        }
        */



        for(int i = 0; i < getMovie.getIntExtra("num", 0); i++){
            posterList.add(ContextCompat.getDrawable(this, getMovie.getIntegerArrayListExtra("poster").get(i)));
            //posterList.add(ContextCompat.getDrawable(this,posterNum[i]));
            nameList.add(getMovie.getStringArrayListExtra("name").get(i));
            countList.add(getMovie.getIntegerArrayListExtra("count").get(i));

        }

        for(int i = 0; i < getMovie.getIntExtra("num",0); i++){

            mainAdapter.addItem(posterList.get(i), nameList.get(i), ContextCompat.getDrawable(getApplicationContext(),R.drawable.yes_star), ContextCompat.getDrawable(getApplicationContext(),R.drawable.no_star), countList.get(i));
        }



        mainListView = findViewById(R.id.main_list);
        mainListView.setAdapter(mainAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.back_button:
                        onBackPressed();
                        break;

                    case R.id.all_button:

                        finish();
                        startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                        break;

                    case R.id.reset_button:
                        for(int i = 0; i < mainAdapter.getCount(); i++){
                            if(mainAdapter.getItem(i).getCount() == 0){
                                itemList.remove(i);
                                i--;
                                mainAdapter.notifyDataSetChanged();
                            }

                        }
                        break;

                    case R.id.starAbc_button:
                        Comparator<MainItem> starAbc = new Comparator<MainItem>() {
                            @Override
                            public int compare(MainItem item1, MainItem item2) {
                                return (item2.getCount() - item1.getCount());
                            }
                        };
                        Collections.sort(itemList, starAbc);
                        mainAdapter.notifyDataSetChanged();
                        break;

                }




            }
        };
        //Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
        //intent.putExtra("name", itemList);

        resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(listener);
        returnButton = findViewById(R.id.all_button);
        returnButton.setOnClickListener(listener);
        starAbcButton = findViewById(R.id.starAbc_button);
        starAbcButton.setOnClickListener(listener);
        removeButton = findViewById(R.id.remove_button);
        removeButton.setOnClickListener(listener);
        mainButton = findViewById(R.id.back_button);
        mainButton.setOnClickListener(listener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
