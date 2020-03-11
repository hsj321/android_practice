package com.hsjnetworkproject.networkproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ShopAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<SearchItem>data;
    private int layout;

    public ShopAdapter(Context context, int layout, ArrayList<SearchItem> data){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(layout, viewGroup, false);
        }

        SearchItem searchItem = data.get(position);

        WebView imageWeb = view.findViewById(R.id.image);
        imageWeb.setFocusable(false);
        if(imageWeb != null){
            imageWeb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            imageWeb.loadUrl(searchItem.getImage());
        }

        TextView nameText = view.findViewById(R.id.name);
        nameText.setText(searchItem.getName());

        TextView areaText = view.findViewById(R.id.area);
        areaText.setText(searchItem.getArea());


        return view;
    }
}
