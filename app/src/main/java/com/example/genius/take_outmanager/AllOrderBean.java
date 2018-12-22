package com.example.genius.take_outmanager;

import java.util.List;

public class AllOrderBean {

    private List<OrderArrBean> orderArr;

    public List<OrderArrBean> getOrderArr() {
        return orderArr;
    }

    public void setOrderArr(List<OrderArrBean> orderArr) {
        this.orderArr = orderArr;
    }

    public static class OrderArrBean {
        /**
         * id : 1211827898
         * time : 2018-04-06 12:32:00
         * deliveryFee : 2
         * boxFee : 2
         * sumFee : 90
         * shopPhone : 15364719590
         * deliveryPhone : 18266598316
         * userPhone : 18406533326
         * comment : 不知道第几次吃了。。。但是今天的是真的不好吃，越吃越难吃，而且说好的不加醋，我天，超酸。。。
         * comment_grade : 4
         */

        private String id;
        private String time;
        private String deliveryFee;
        private String boxFee;
        private String sumFee;
        private String shopPhone;
        private String deliveryPhone;
        private String userPhone;
        private String comment;
        private String comment_grade;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDeliveryFee() {
            return deliveryFee;
        }

        public void setDeliveryFee(String deliveryFee) {
            this.deliveryFee = deliveryFee;
        }

        public String getBoxFee() {
            return boxFee;
        }

        public void setBoxFee(String boxFee) {
            this.boxFee = boxFee;
        }

        public String getSumFee() {
            return sumFee;
        }

        public void setSumFee(String sumFee) {
            this.sumFee = sumFee;
        }

        public String getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(String shopPhone) {
            this.shopPhone = shopPhone;
        }

        public String getDeliveryPhone() {
            return deliveryPhone;
        }

        public void setDeliveryPhone(String deliveryPhone) {
            this.deliveryPhone = deliveryPhone;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getComment_grade() {
            return comment_grade;
        }

        public void setComment_grade(String comment_grade) {
            this.comment_grade = comment_grade;
        }
    }
}
