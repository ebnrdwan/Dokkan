package com.engineering.dokkan.view.Favourite;

public class FavShopModel {
    private int shopImage ;
    private int shop_name_image ;
    private String shop_name ;
    private String shop_location ;

    public FavShopModel(int shopImage, int shop_name_image, String shop_name, String shop_location) {
        this.shopImage = shopImage;
        this.shop_name_image = shop_name_image;
        this.shop_name = shop_name;
        this.shop_location = shop_location;
    }

    public int getShopImage() {
        return shopImage;
    }

    public void setShopImage(int shopImage) {
        this.shopImage = shopImage;
    }

    public int getShop_name_image() {
        return shop_name_image;
    }

    public void setShop_name_image(int shop_name_image) {
        this.shop_name_image = shop_name_image;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_location() {
        return shop_location;
    }

    public void setShop_location(String shop_location) {
        this.shop_location = shop_location;
    }
}

