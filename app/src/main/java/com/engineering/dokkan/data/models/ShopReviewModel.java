package com.engineering.dokkan.data.models;

public class ShopReviewModel {
    private String comment ;
    private String productID ;
    private String rate;
    private String reviewID ;
    private String shopID ;
    private String userID;
    private String  date ;


    public ShopReviewModel() {

    }

    public ShopReviewModel(String comment, String productID, String rate, String reviewID, String shopID, String userID, String date) {
        this.comment = comment;
        this.productID = productID;
        this.rate = rate;
        this.reviewID = reviewID;
        this.shopID = shopID;
        this.userID = userID;
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
