package com.hsjnetworkproject.networkproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hsjnetworkproject.networkproject.R;

import java.util.ArrayList;


public class SearchAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<SearchItem> data;
    private int layout;

    public SearchAdapter(Context context, int layout, ArrayList<SearchItem> data){
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
        WebSettings webSettings = imageWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if(imageWeb != null){

            imageWeb.setFocusable(false);
            imageWeb.loadUrl(searchItem.getImage());
        }

        TextView nameText = view.findViewById(R.id.name);
        nameText.setText(searchItem.getName());

        TextView areaText = view.findViewById(R.id.area);
        areaText.setText(searchItem.getArea());

        TextView numberText = view.findViewById(R.id.number);
        numberText.setText(searchItem.getNumber());

        return view;
    }
}
