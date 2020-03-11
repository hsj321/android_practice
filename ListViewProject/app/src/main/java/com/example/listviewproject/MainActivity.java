package com.example.listviewproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mainListView = null;
    private int[] posterNum = {R.drawable.poster1,R.drawable.poster2,R.drawable.poster3,R.drawable.poster4,R.drawable.poster5,R.drawable.poster6, R.drawable.poster7, R.drawable.poster8, R.drawable.poster8, R.drawable.poster8};
    private int[] nameNumber = {R.string.name1, R.string.name2, R.string.name3, R.string.name4, R.string.name5, R.string.name6, R.string.name7, R.string.name8, R.string.name9, R.string.name10};
    private int movieCount = posterNum.length;
    private List<Drawable > posterList;
    private List<Integer> intPosterList;
    private List<String > nameList;
    private EditText searchEdit;
    private MainAdapter mainAdapter;
    private ArrayList<Drawable> copyPoster;
    private ArrayList<String> copyName;
    private ArrayList<Integer> copyCount;
    private ArrayList<Integer> copyIntPoster;
    private  ArrayList<MainItem> itemList = new ArrayList<MainItem>() ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainAdapter = new MainAdapter();

        Button mypageButton;
        System.out.println("a" + posterNum[1]);

        posterList = new ArrayList<Drawable>();
        nameList = new ArrayList<String>();
        intPosterList = new ArrayList<Integer>();

        for(int i = 0; i < posterNum.length; i++){
            posterList.add(ContextCompat.getDrawable(this, posterNum[i]));
            intPosterList.add(posterNum[i]);
            nameList.add(getString(nameNumber[i]));
        }

        copyName = new ArrayList<String>();
        copyPoster = new ArrayList<Drawable>();
        copyCount = new ArrayList<Integer>();
        copyIntPoster = new ArrayList<Integer>();


        for(int i = 0; i < movieCount; i++){

            mainAdapter.addItem(posterList.get(i), nameList.get(i), ContextCompat.getDrawable(getApplicationContext(),R.drawable.yes_star), ContextCompat.getDrawable(getApplicationContext(),R.drawable.no_star), 0, intPosterList.get(i));
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
                    case R.id.mypage_button:
                        int num = 0;
                        Intent pageIntent = new Intent(getApplicationContext(), MypageActivity.class);

                        for(int i = 0; i < posterList.size(); i++){
                            System.out.println(mainAdapter.getItem(i).getCount());
                            if(mainAdapter.getItem(i).getCount() != 0){
                                num++;
                                copyPoster.add(mainAdapter.getItem(i).getPoster());
                                copyName.add(mainAdapter.getItem(i).getName());
                                copyCount.add(mainAdapter.getItem(i).getCount());
                                copyIntPoster.add(mainAdapter.getItem(i).getIntPoster());

                            }
                        }

                        pageIntent.putIntegerArrayListExtra("poster",copyIntPoster);
                        pageIntent.putStringArrayListExtra("name", copyName);
                        pageIntent.putIntegerArrayListExtra("count", copyCount);
                        pageIntent.putExtra("num", num);

                        startActivity(pageIntent);
                        break;
                }
            }
        };

        mypageButton = findViewById(R.id.mypage_button);
        mypageButton.setOnClickListener(listener);

        searchEdit = findViewById(R.id.search_movie);
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable edit) {
                String text = edit.toString();
                if (text.length() > 0) {
                    mainListView.setFilterText(text) ;
                } else {
                    mainListView.clearTextFilter() ;
                }

            }
        });


    }
    public void dataTransfer(int count){
        System.out.println(count);
    }
}
