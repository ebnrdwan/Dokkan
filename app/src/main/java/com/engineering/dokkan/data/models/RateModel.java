package com.engineering.dokkan.data.models;

public class RateModel {

    private float Rate ;
    private String customerId ;
    private boolean isProduct ;
    private String key ;

    public RateModel() {
    }

    public RateModel(float rate, String customerId, boolean isProduct, String key) {
        Rate = rate;
        this.customerId = customerId;
        this.isProduct = isProduct;
        this.key = key;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public boolean isProduct() {
        return isProduct;
    }

    public void setProduct(boolean product) {
        isProduct = product;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
