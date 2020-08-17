package com.engineering.dokkan.data.models;

public class ShopProductModel {
    private String productId ;
    private String name ;
    private String price ;
    private String categoryid ;
    private String shopId;
    private String image1;
    private String image2 ;
    private  String image3;
    private  boolean fav  ;
    private float rate ;

    public ShopProductModel() {
    }

    public ShopProductModel(String productId, String name, String price, String categoryid, String shopId, String image1, String image2, String image3, boolean fav, float rate) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.categoryid = categoryid;
        this.shopId = shopId;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.fav = fav;
        this.rate = rate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
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
