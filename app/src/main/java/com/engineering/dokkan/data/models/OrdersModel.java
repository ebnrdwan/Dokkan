package com.engineering.dokkan.data.models;
    public class OrdersModel {
        String Image_url,orderDate,orderCount,orderPrice,orderStatus ;

        public OrdersModel() {
        }
        public OrdersModel(String Image_url, String orderDate, String orderCount, String orderPrice, String orderStatus) {
            this.Image_url = Image_url;
            this.orderDate = orderDate;
            this.orderCount = orderCount;
            this.orderPrice = orderPrice;
            this.orderStatus=orderStatus;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getImage_url() {
            return Image_url;
        }

        public void setImage_url(String Image_url) {
            this.Image_url = Image_url;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public String getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(String orderCount) {
            this.orderCount = orderCount;
        }

        public String getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(String orderPrice) {
            this.orderPrice = orderPrice;
        }


    }




