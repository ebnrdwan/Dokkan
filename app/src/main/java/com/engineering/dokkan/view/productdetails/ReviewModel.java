package com.engineering.dokkan.view.productdetails;

public class ReviewModel {
    private String comment;
    private String image;
    private String name;
    private float rate;
    private String time;
    private String Key;
    private String productID ;


    public ReviewModel() {
    }

    public ReviewModel(String comment, String image, String name, float rate, String time, String key, String productID) {
        this.comment = comment;
        this.image = image;
        this.name = name;
        this.rate = rate;
        this.time = time;
        Key = key;
        this.productID = productID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}


