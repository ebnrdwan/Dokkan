package com.engineering.dokkan.data.models;

public class ShopReviewModel {
    private String comment;
    private String image;
    private String name;
    private float rate;
    private Boolean Fav_comment;
    private int likesOfComment;
    private String time;
    private String Key;
    private String itemImage;
    private String itemName;
    private String itemDesc ;


    public ShopReviewModel() {

    }

    public ShopReviewModel(String comment, String image, String name, float rate,Boolean Fav_comment, String time) {
        this.comment = comment;
        this.image = image;
        this.name = name;
        this.rate = rate;
        this.Fav_comment =Fav_comment;
        this.time = time;

    }

    public ShopReviewModel(String comment, String image, String name, float rate,
                           Boolean fav_comment, int likesOfComment, String time,
                           String key, String itemImage, String itemName, String itemDesc) {
        this.comment = comment;
        this.image = image;
        this.name = name;
        this.rate = rate;
        Fav_comment = fav_comment;
        this.likesOfComment = likesOfComment;
        this.time = time;
        Key = key;
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getLikesOfComment() {
        return likesOfComment;
    }

    public void setLikesOfComment(int likesOfComment) {
        this.likesOfComment = likesOfComment;
    }

    public Boolean getFav_comment() {
        return Fav_comment;
    }

    public void setFav_comment(Boolean fav_comment) {
        Fav_comment = fav_comment;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
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


}
