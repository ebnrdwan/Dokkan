package com.engineering.dokkan.data.models;

public class FavShopModel {
    private String shopCoverImage;
    private String shop_name_image;
    private String shopName;
    private String shopLocation;
    String mKey;
    float rate ;

    public FavShopModel(String shopCoverImage, String shop_name_image, String shopName, String shopLocation) {
        this.shopCoverImage = shopCoverImage;
        this.shop_name_image = shop_name_image;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
    }

    public FavShopModel() {

    }

    public String getShopCoverImage() {
        return shopCoverImage;
    }

    public void setShopCoverImage(String shopCoverImage) {
        this.shopCoverImage = shopCoverImage;
    }

    public String getShop_name_image() {
        return shop_name_image;
    }

    public void setShop_name_image(String shop_name_image) {
        this.shop_name_image = shop_name_image;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
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

