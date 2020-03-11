package com.example.listviewproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MypageAdapter extends BaseAdapter {





    private ArrayList<MainItem> mainList;



    public MypageAdapter(ArrayList<MainItem> itemList) {
        if (itemList == null) {
            mainList = new ArrayList<MainItem>() ;
        } else {
            mainList = itemList ;
        }
    }

    //private ArrayList<MainItem> filterList = mainList;

    //Filter listFilter;


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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();
        




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

        final MainItem mainItem = mainList.get(position);



        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.poster:

                    case R.id.star1:
                        //count = 1;
                        if(mainItem.getCount() == 1){
                            mainItem.setCount(0);
                            star1.setImageDrawable(mainItem.getNoStar());
                            star2.setImageDrawable(mainItem.getNoStar());
                            star3.setImageDrawable(mainItem.getNoStar());
                            star4.setImageDrawable(mainItem.getNoStar());
                            star5.setImageDrawable(mainItem.getNoStar());
                            break;
                        } else {

                            mainItem.setCount(1);

                            star1.setImageDrawable(mainItem.getYesStar());
                            star2.setImageDrawable(mainItem.getNoStar());
                            star3.setImageDrawable(mainItem.getNoStar());
                            star4.setImageDrawable(mainItem.getNoStar());
                            star5.setImageDrawable(mainItem.getNoStar());
                            break;
                        }


                    case R.id.star2:
                        //count = 2;
                        if(mainItem.getCount() == 2){
                            mainItem.setCount(0);
                            star1.setImageDrawable(mainItem.getNoStar());
                            star2.setImageDrawable(mainItem.getNoStar());
                            star3.setImageDrawable(mainItem.getNoStar());
                            star4.setImageDrawable(mainItem.getNoStar());
                            star5.setImageDrawable(mainItem.getNoStar());
                            break;
                        } else {

                            mainItem.setCount(2);

                            star1.setImageDrawable(mainItem.getYesStar());
                            star2.setImageDrawable(mainItem.getYesStar());
                            star3.setImageDrawable(mainItem.getNoStar());
                            star4.setImageDrawable(mainItem.getNoStar());
                            star5.setImageDrawable(mainItem.getNoStar());
                            break;
                        }
                    case R.id.star3:
                        //count = 3;
                        if(mainItem.getCount() == 3){
                            mainItem.setCount(0);
                            star1.setImageDrawable(mainItem.getNoStar());
                            star2.setImageDrawable(mainItem.getNoStar());
                            star3.setImageDrawable(mainItem.getNoStar());
                            star4.setImageDrawable(mainItem.getNoStar());
                            star5.setImageDrawable(mainItem.getNoStar());
                            break;
                        } else {

                            mainItem.setCount(3);

                            star1.setImageDrawable(mainItem.getYesStar());
                            star2.setImageDrawable(mainItem.getYesStar());
                            star3.setImageDrawable(mainItem.getYesStar());
                            star4.setImageDrawable(mainItem.getNoStar());
                            star5.setImageDrawable(mainItem.getNoStar());
                            break;
                        }
                    case R.id.star4:
                        //count = 4;
                        if(mainItem.getCount() == 4){
                            mainItem.setCount(0);
                            star1.setImageDrawable(mainItem.getNoStar());
                            star2.setImageDrawable(mainItem.getNoStar());
                            star3.setImageDrawable(mainItem.getNoStar());
                            star4.setImageDrawable(mainItem.getNoStar());
                            star5.setImageDrawable(mainItem.getNoStar());
                            break;
                        } else {

                            mainItem.setCount(4);

                            star1.setImageDrawable(mainItem.getYesStar());
                            star2.setImageDrawable(mainItem.getYesStar());
                            star3.setImageDrawable(mainItem.getYesStar());
                            star4.setImageDrawable(mainItem.getYesStar());
                            star5.setImageDrawable(mainItem.getNoStar());
                            break;
                        }
                    case R.id.star5:
                        //count = 5;
                        if(mainItem.getCount() == 5){
                            mainItem.setCount(0);
                            star1.setImageDrawable(mainItem.getNoStar());
                            star2.setImageDrawable(mainItem.getNoStar());
                            star3.setImageDrawable(mainItem.getNoStar());
                            star4.setImageDrawable(mainItem.getNoStar());
                            star5.setImageDrawable(mainItem.getNoStar());
                            break;
                        } else {

                            mainItem.setCount(5);

                            star1.setImageDrawable(mainItem.getYesStar());
                            star2.setImageDrawable(mainItem.getYesStar());
                            star3.setImageDrawable(mainItem.getYesStar());
                            star4.setImageDrawable(mainItem.getYesStar());
                            star5.setImageDrawable(mainItem.getYesStar());
                            break;
                        }
                }
            }
        };

        star1.setOnClickListener(listener);
        star2.setOnClickListener(listener);
        star3.setOnClickListener(listener);
        star4.setOnClickListener(listener);
        star5.setOnClickListener(listener);


        switch (mainItem.getCount()){

            case 1:
                //count = 1;
                //mainItem.setCount(1);

                star1.setImageDrawable(mainItem.getYesStar());
                star2.setImageDrawable(mainItem.getNoStar());
                star3.setImageDrawable(mainItem.getNoStar());
                star4.setImageDrawable(mainItem.getNoStar());
                star5.setImageDrawable(mainItem.getNoStar());
                break;
            case 2:
                //count = 2;
                //mainItem.setCount(2);
                star1.setImageDrawable(mainItem.getYesStar());
                star2.setImageDrawable(mainItem.getYesStar());
                star3.setImageDrawable(mainItem.getNoStar());
                star4.setImageDrawable(mainItem.getNoStar());
                star5.setImageDrawable(mainItem.getNoStar());
                break;
            case 3:
                //count = 3;
                //mainItem.setCount(3);
                star1.setImageDrawable(mainItem.getYesStar());
                star2.setImageDrawable(mainItem.getYesStar());
                star3.setImageDrawable(mainItem.getYesStar());
                star4.setImageDrawable(mainItem.getNoStar());
                star5.setImageDrawable(mainItem.getNoStar());
                break;
            case 4:
                //count = 4;
                //mainItem.setCount(4);
                star1.setImageDrawable(mainItem.getYesStar());
                star2.setImageDrawable(mainItem.getYesStar());
                star3.setImageDrawable(mainItem.getYesStar());
                star4.setImageDrawable(mainItem.getYesStar());
                star5.setImageDrawable(mainItem.getNoStar());
                break;
            case 5:
                //count = 5;
                //mainItem.setCount(5);
                star1.setImageDrawable(mainItem.getYesStar());
                star2.setImageDrawable(mainItem.getYesStar());
                star3.setImageDrawable(mainItem.getYesStar());
                star4.setImageDrawable(mainItem.getYesStar());
                star5.setImageDrawable(mainItem.getYesStar());
                break;
        }

        posterButton.setImageDrawable(mainItem.getPoster());
        nameView.setText(mainItem.getName());
        //System.out.println(position);

        return convertView;
    }


    public void addItem(Drawable poster, String name, Drawable yesStar, Drawable noStar, int count){
        MainItem mainItem = new MainItem();

        mainItem.setPoster(poster);
        mainItem.setName(name);
        mainItem.setCount(count);
        mainItem.setYesStar(yesStar);
        mainItem.setNoStar(noStar);

        mainList.add(mainItem);
    }

    public ArrayList<MainItem> getItemList(){
        return mainList;
    }
}
