package com.example.gehna.shopapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter{

    Context context;
    LayoutInflater inflater;

    // list of images
    public int[] lst_images = {
            R.mipmap.img1,
            R.mipmap.img2,
            R.mipmap.img3,
            R.mipmap.img4,
            R.mipmap.img5,
            R.mipmap.img6
    };
    //list of titles
    public String[] lst_title = {
            "First, select the brand",
            "Select the color",
            "Select the type of clothing",
            "Select the size",
            "Shops with the item will be displayed",
            "Navigate to the shop"

    };

    //list of description
    public String[] lst_description = {
            "Description 1",
            "Description 2",
            "Description 3"
    };


    //list of background colors
    public int[] lst_background = {
          R.drawable.animation_list,
            R.drawable.animation_list_2,
            R.drawable.animation_list,
            R.drawable.animation_list_2,
            R.drawable.animation_list,
            R.drawable.animation_list_2

    };

    public SlideAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_images.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);

    }

    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        RelativeLayout linearslide = view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView) view.findViewById(R.id.slideimg);
        TextView title = (TextView) view.findViewById(R.id.title);
        //TextView description = (TextView) view.findViewById(R.id.)
        //layoutslide.setBackgroundColor(lst_background[position]);
        imgslide.setImageResource(lst_images[position]);
        title.setText(lst_title[position]);
        //.setText(lst_description(position));
        container.addView(view);
        return view;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(RelativeLayout)object);
    }
}
