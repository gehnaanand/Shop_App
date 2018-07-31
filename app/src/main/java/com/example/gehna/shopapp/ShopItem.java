package com.example.gehna.shopapp;

/**
 * Created by GEHNA on 7/30/2018.
 */

public class ShopItem {
    private String mShopName;
    private int mShopImage;

    public ShopItem(String shopName,int shopImage){
        mShopName=shopName;
        mShopImage=shopImage;
    }

    public String getShopName(){
        return mShopName;
    }
    public int getShopImage(){
        return mShopImage;
    }
}
