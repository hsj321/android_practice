package com.example.threadproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Locale;

public class MainAdapter extends BaseAdapter {





    private ArrayList<MainItem> mainList;
    private static Handler mHandler;



    public MainAdapter(ArrayList<MainItem> itemList) {
        if (itemList == null) {
            mainList = new ArrayList<MainItem>() ;
        } else {
            mainList = itemList ;
        }
    }

    @Override
    public int getCount() {
        return mainList.size();
    }

    @Override
    public MainItem getItem(int i) {
        return mainList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_main, parent, false);
        }

        final TextView nameView = convertView.findViewById(R.id.name);
        final TextView timeView = convertView.findViewById(R.id.time);
        final Button startButton = convertView.findViewById(R.id.start);

        final MainItem mainItem = mainList.get(position);

        nameView.setText(mainItem.getName());
        startButton.setText(mainItem.getStart());





        mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                int time = msg.arg1;
                mainItem.setTime(time);
                //System.out.println(position);


                if(time == 0){
                    timeView.setText("");
                } else{
                    System.out.println(mainItem.getTime());
                    timeView.setText(String.format(Locale.getDefault(), "%d 초", mainItem.getTime()));
                }

            }
        };

        class NewRunnable implements Runnable {

            public NewRunnable(){

            }
            @Override
            public void run() {


                for(int j = 5; j >= 0; j--) {
                    Message message = mHandler.obtainMessage();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace() ;
                    }
                    message.arg1 = j;
                    mHandler.sendMessage(message);
                }
            }
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.start:
                        NewRunnable hand1 = new NewRunnable();
                        Thread t1 = new Thread(hand1);
                        t1.start();
                        break;
                }
            }
        };


        startButton.setOnClickListener(listener);



        return convertView;
    }

    public void addItem(String name, String start, int time, int count){
        MainItem mainItem = new MainItem();

        mainItem.setName(name);
        mainItem.setStart(start);
        mainItem.setTime(time);
        mainItem.setCount(count);


        mainList.add(mainItem);
    }
}
