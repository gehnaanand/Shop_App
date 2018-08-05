package com.example.gehna.shopapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by GEHNA on 7/30/2018.
 */

public class ShopAdapter extends ArrayAdapter<ShopItem> {

    public ShopAdapter(Context context, ArrayList<ShopItem> shopList){
        super(context,0,shopList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private  View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.shop_spinner_row, parent, false
            );
        }

        ImageView imageViewShop = convertView.findViewById(R.id.image_view_logo);
        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        ShopItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewShop.setImageResource(currentItem.getShopImage());
            textViewName.setText(currentItem.getShopName());
        }

        return convertView;
    }
}
