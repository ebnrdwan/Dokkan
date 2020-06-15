package com.engineering.dokkan.data.models;

public class ProductitemModel {
    private String key ;
    private String name ;
    private String price ;
    private String image ;
    private String categoryid ;
    private String img1;
    private String img2 ;
    private  String img3;
    private String descryption;
    private String size ;
    private String materials;
    private static boolean fav = false ;



    public ProductitemModel() {
    }

    public ProductitemModel(String name, String price, String image,
                            String categoryid, String img1, boolean fav ,
                            String img2, String img3, String descryption,
                            String size, String materials , String key) {
        this.key = key;
        this.name = name;
        this.price = price;
        this.image = image;
        this.categoryid = categoryid;
        this.fav=fav;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.descryption = descryption;
        this.size = size;
        this.materials = materials;
    }

    public ProductitemModel(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getDescryption() {
        return descryption;
    }

    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
