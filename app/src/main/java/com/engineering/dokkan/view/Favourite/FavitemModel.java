package com.engineering.dokkan.view.Favourite;

public class FavitemModel {
    String name ;
    String price ;
    int image ;
    int icon ;

    public FavitemModel(String name, String price, int image, int icon) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.icon = icon;
    }

    public int getIcon()
    {
        return icon;
    }

    public void setIcon(int icon)
    {
        this.icon = icon;
    }

    public int getImage() {

        return image;
    }

    public void setImage(int image) {

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
