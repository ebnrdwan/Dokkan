package com.engineering.dokkan.data.models;

public class HomeItemModel {

    private String itemName;
    private String price ;
    private int imageResourceId ;

    public HomeItemModel(String itemName, String price, int imageResourceId) {
        this.itemName = itemName;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
