package com.engineering.dokkan.data.models;

public class SliderItemModel {
    private  String key ;

    private String image;
    private String categoryname;

    public SliderItemModel() {
    }

    public SliderItemModel( String image,String key , String categoryname) {

        this.key = key ;
        this.image = image;
        this.categoryname = categoryname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
