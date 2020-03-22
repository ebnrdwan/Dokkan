package com.engineering.dokkan.data;

public class ChatModel {
    private int shop_logo;
    private String shop_name, text_message, text_time;

    public ChatModel(int shop_logo, String shop_name, String text_message, String text_time) {
        this.shop_logo = shop_logo;
        this.shop_name = shop_name;
        this.text_message = text_message;
        this.text_time = text_time;
    }

    public int getShop_logo() {
        return shop_logo;
    }

    public void setShop_logo(int shop_logo) {
        this.shop_logo = shop_logo;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getText_message() {
        return text_message;
    }

    public void setText_message(String text_message) {
        this.text_message = text_message;
    }

    public String getText_time() {
        return text_time;
    }

    public void setText_time(String text_time) {
        this.text_time = text_time;
    }
}
