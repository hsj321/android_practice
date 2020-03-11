package com.example.threadproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainItem {
    private String name;
    private int time;
    private String working;
    private String start;
    private int count;

    public String getName(){
        return name;
    }

    public int getTime() {
        return time;
    }

    public String getStart() {
        return start;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTime(int time){
        this.time = time;
    }

    public void setStart(String start){
        this.start = start;
    }


    public void setCount(int count){
        this.count = count;
    }
}