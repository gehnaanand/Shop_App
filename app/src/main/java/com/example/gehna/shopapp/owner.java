package com.example.gehna.shopapp;

/**
 * Created by GEHNA on 8/2/2018.
 */

public class owner {
    String brand,style,color,store_name,email;
    double longitude,latitude;

    public owner(String brand, String color, String style ){
        this.brand = brand;
        this.style = style;
        this.color = color;
    }

    public owner(String store_name, String email,double longitude,double latitude) {
        this.store_name = store_name;
        this.email = email;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
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

