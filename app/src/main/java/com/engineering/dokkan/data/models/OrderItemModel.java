package com.engineering.dokkan.data.models;

import com.engineering.dokkan.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderItemModel {

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    List<CartItem> cartItem;
    String key;
    String date;

    public void setDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.getDefault());
        this.date = df.format(date);
    }

    public String getDate() {
        return date;
    }

    AddressModel address;

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
