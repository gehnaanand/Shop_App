package com.example.gehna.shopapp;

/**
 * Created by GEHNA on 8/2/2018.
 */

public class owner {
    String brand,style,color;

    public owner(String brand, String color, String style ){
        this.brand = brand;
        this.style = style;
        this.color = color;
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

