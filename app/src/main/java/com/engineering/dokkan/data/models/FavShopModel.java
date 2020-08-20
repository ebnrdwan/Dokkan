package com.engineering.dokkan.data.models;

import java.util.ArrayList;

public class FavShopModel {
    private String key ;
    private String location;
    private String shopImage;
    private String shopName;
    private boolean fav  ;
    private float rate ;

    public FavShopModel() {
    }

    public FavShopModel( String key, String location, String shopImage, String shopName, boolean fav, float rate) {
        this.key = key;
        this.location = location;
        this.shopImage = shopImage;
        this.shopName = shopName;
        this.fav = fav;
        this.rate = rate;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}

