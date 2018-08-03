package com.example.gehna.shopapp;

/**
 * Created by GEHNA on 8/2/2018.
 */

public class owner {
    String brand,style,color,store_name,email;

    public owner(String brand, String color, String style ){
        this.brand = brand;
        this.style = style;
        this.color = color;
    }

    public owner(String store_name, String email) {
        this.store_name = store_name;
        this.email = email;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getEmail() {
        return email;
    }

    public owner(){

    }
    public String getBrand() {
        return brand;
    }

    public String getStyle() {
        return style;
    }

    public String getColor() {
        return color;
    }
}

