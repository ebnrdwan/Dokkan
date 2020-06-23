package com.engineering.dokkan.view.productdetails;

public class ReviewModel {
    private String comment;
        private String image;
        private String name;
        private float rate;
        private Boolean Fav_comment;
        private int likesOfComment;
        private String time;
        private String Key;


        public ReviewModel() {

        }

        public ReviewModel(String comment, String image, String name, float rate,Boolean Fav_comment, String time) {
            this.comment = comment;
            this.image = image;
            this.name = name;
            this.rate = rate;
            this.Fav_comment =Fav_comment;
            this.time = time;

        }

        public int getLikesOfComment() {
            return likesOfComment;
        }

        public void setLikesOfComment(int likesOfComment) {
            this.likesOfComment = likesOfComment;
        }

        public Boolean getFav_comment() {
            return Fav_comment;
        }

        public void setFav_comment(Boolean fav_comment) {
            Fav_comment = fav_comment;
        }

        public String getKey() {
            return Key;
        }

        public void setKey(String key) {
            Key = key;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
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

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }


}


