package com.engineering.dokkan.data.models;

public class RecProdModel {

    private String productId;
    private String userId ;

    public RecProdModel() {
    }

    public RecProdModel(String productId, String userId) {
        this.productId = productId;
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
