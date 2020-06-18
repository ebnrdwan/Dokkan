package com.engineering.dokkan.data.models;

import com.google.firebase.database.Exclude;

public class FavitemModel {
    String name ;
    String price ;
    String image ;
    String mKey;
    float rate ;

    public FavitemModel() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FavitemModel(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
