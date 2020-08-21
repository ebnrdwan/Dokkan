package com.engineering.dokkan.data.models;

import java.util.List;

public class OrderItemModel {

    public viewAddressModel getAddress() {
        return address;
    }

    public void setAddress(viewAddressModel address) {
        this.address = address;
    }

    List <CartItem> cartItem ;
    String  key ;
    viewAddressModel address;
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
