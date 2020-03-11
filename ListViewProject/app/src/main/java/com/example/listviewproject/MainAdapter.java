package com.example.listviewproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter implements Filterable {





    private ArrayList<MainItem> mainList = new ArrayList<MainItem>();
    private ArrayList<MainItem> filterList = mainList;
    Filter listFilter;

    @Override
    public int getCount() {
        return filterList.size();
    }

    @Override
    public MainItem getItem(int i) {
        return mainList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        System.out.println("asdfasdf");



        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_score, parent, false);
        }

        ImageButton posterButton = convertView.findViewById(R.id.poster);
        TextView nameView = convertView.findViewById(R.id.name);
        final ImageButton star1 = convertView.findViewById(R.id.star1);
        final ImageButton star2 = convertView.findViewById(R.id.star2);
        final ImageButton star3 = convertView.findViewById(R.id.star3);
        final ImageButton star4 = convertView.findViewById(R.id.star4);
        final ImageButton star5 = convertView.findViewById(R.id.star5);

        final MainItem mainItem = filterList.get(position);






        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.poster:
                        Intent intent = new Intent(context, MypageActivity.class);
                        context.startActivity(intent);

                    case R.id.star1:
                        //count = 1;
                        mainItem.setCount(1);

                        star1.setImageDrawable(mainItem.getYesStar());
                        star2.setImageDrawable(mainItem.getNoStar());
                        star3.setImageDrawable(mainItem.getNoStar());
                        star4.setImageDrawable(mainItem.getNoStar());
                        star5.setImageDrawable(mainItem.getNoStar());
                        break;
                    case R.id.star2:
                        //count = 2;
                        mainItem.setCount(2);
                        star1.setImageDrawable(mainItem.getYesStar());
                        star2.setImageDrawable(mainItem.getYesStar());
                        star3.setImageDrawable(mainItem.getNoStar());
                        star4.setImageDrawable(mainItem.getNoStar());
                        star5.setImageDrawable(mainItem.getNoStar());
                        break;
                    case R.id.star3:
                        //count = 3;
                        mainItem.setCount(3);
                        star1.setImageDrawable(mainItem.getYesStar());
                        star2.setImageDrawable(mainItem.getYesStar());
                        star3.setImageDrawable(mainItem.getYesStar());
                        star4.setImageDrawable(mainItem.getNoStar());
                        star5.setImageDrawable(mainItem.getNoStar());
                        break;
                    case R.id.star4:
                        //count = 4;
                        mainItem.setCount(4);
                        star1.setImageDrawable(mainItem.getYesStar());
                        star2.setImageDrawable(mainItem.getYesStar());
                        star3.setImageDrawable(mainItem.getYesStar());
                        star4.setImageDrawable(mainItem.getYesStar());
                        star5.setImageDrawable(mainItem.getNoStar());
                        break;
                    case R.id.star5:
                        //count = 5;
                        mainItem.setCount(5);
                        star1.setImageDrawable(mainItem.getYesStar());
                        star2.setImageDrawable(mainItem.getYesStar());
                        star3.setImageDrawable(mainItem.getYesStar());
                        star4.setImageDrawable(mainItem.getYesStar());
                        star5.setImageDrawable(mainItem.getYesStar());
                        break;
                }
            }
        };

        star1.setOnClickListener(listener);
        star2.setOnClickListener(listener);
        star3.setOnClickListener(listener);
        star4.setOnClickListener(listener);
        star5.setOnClickListener(listener);

        posterButton.setImageDrawable(mainItem.getPoster());
        nameView.setText(mainItem.getName());
        //System.out.println(position);

        return convertView;
    }


    public void addItem(Drawable poster, String name, Drawable yesStar, Drawable noStar, int count, int intPoster){
        MainItem mainItem = new MainItem();

        mainItem.setPoster(poster);
        mainItem.setName(name);
        mainItem.setCount(count);
        mainItem.setYesStar(yesStar);
        mainItem.setNoStar(noStar);
        mainItem.setIntPoster(intPoster);

        mainList.add(mainItem);
    }



    @Override
    public Filter getFilter() {
        if(listFilter == null){
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = mainList ;
                results.count = mainList.size() ;
            } else {
                ArrayList<MainItem> itemList = new ArrayList<MainItem>() ;

                for (MainItem item : mainList) {
                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // update listview by filtered data list.
            filterList = (ArrayList<MainItem>) results.values ;

            // notify
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }

}
