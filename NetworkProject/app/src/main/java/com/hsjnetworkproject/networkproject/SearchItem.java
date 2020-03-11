package com.hsjnetworkproject.networkproject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class SearchItem {
    private String name;
    private String area;
    private String image;
    private String number;
    private String id;
    private String date;
    private String catalog;
    private String group;

    public SearchItem(String name, String area, String image, String number, String id, String date, String catalog, String group){
        this.name = name;
        this.area = area;
        this.image = image;
        this.number = number;

        this.id = id;
        this.date = date;
        this.catalog = catalog;
        this.group = group;
    }


    public String getName(){
        return name;
    }

    public String getArea(){
        return area;
    }

    public String getImage(){
        return image;
    }

    public String getNumber(){
        return number;
    }

    public String getId(){
        return id;
    }


    public String getDate(){
        return date;
    }

    public String getCatalog(){
        return catalog;
    }

    public String getGroup(){
        return group;
    }


}
