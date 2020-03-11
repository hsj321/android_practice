package com.example.listviewproject;

import android.graphics.drawable.Drawable;


public class MainItem {
    private Drawable poster;
    private Drawable yesStar;
    private Drawable noStar;
    private int intPoster;
    private String name;
    private int count;

    public Drawable getPoster() {
        return poster;
    }

    public void setPoster(Drawable poster){
        this.poster = poster;
    }

    public Drawable getYesStar() {
        return yesStar;
    }

    public void setYesStar(Drawable yesStar){
        this.yesStar = yesStar;
    }

    public Drawable getNoStar() {
        return noStar;
    }

    public int getIntPoster(){
        return intPoster;
    }

    public void setIntPoster(int intPoster) {
        this.intPoster = intPoster;
    }

    public void setNoStar(Drawable noStar){
        this.noStar = noStar;
    }


    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }
}
