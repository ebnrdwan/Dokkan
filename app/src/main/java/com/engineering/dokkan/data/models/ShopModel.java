package com.engineering.dokkan.data.models;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopModel{
        private HashMap<String,String> Reviews ;
        private String key ;
        private String location;
        private String bio;
        private String coverImage;
        private String shopImage;
        private String categoryid;
        private String shopName;
        private boolean fav  ;
        private float rate ;
        private  String phoneNum ;
        private  String about ;
        private  String policies;
        private  String fbLink;
        private  String instaLink;




        public ShopModel() {
        }

        public ShopModel(String key, String categoryid,
                             String bio, String coverImage, String shopImage,
                             String shopName,String location,  float rate,
                             boolean fav,String phoneNum,
                             String about, String policies,
                             String fbLink, String instaLink
                            , HashMap<String,String> Reviews ) {
            this.key = key;
            this.location = location;
            this.bio = bio;
            this.coverImage = coverImage;
            this.shopImage = shopImage;
            this.categoryid = categoryid;
            this.shopName = shopName;
            this.fav = fav;
            this.rate = rate;
            this.phoneNum = phoneNum;
            this.about = about;
            this.policies = policies;
            this.fbLink = fbLink;
            this.instaLink = instaLink;
            this.Reviews = Reviews;

        }

    public HashMap<String, String> getReviews() {
        return Reviews;
    }

    public void setReviews(HashMap<String, String> reviews) {
        Reviews = reviews;
    }

    //    public ArrayList<String> getReviews() {
//        return Reviews;
//    }
//
//    public void setReviews(ArrayList<String> reviews) {
//        Reviews = reviews;
//    }

    public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public String getPolicies() {
            return policies;
        }

        public void setPolicies(String policies) {
            this.policies = policies;
        }

        public String getFbLink() {
            return fbLink;
        }

        public void setFbLink(String fbLink) {
            this.fbLink = fbLink;
        }

        public String getInstaLink() {
            return instaLink;
        }

        public void setInstaLink(String instaLink) {
            this.instaLink = instaLink;
        }


        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        public boolean isFav() {
            return fav;
        }

        public void setFav(boolean fav) {
            this.fav = fav;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public String getShopImage() {
            return shopImage;
        }

        public void setShopImage(String shopImage) {
            this.shopImage = shopImage;
        }

        public String getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(String categoryid) {
            this.categoryid = categoryid;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
}


