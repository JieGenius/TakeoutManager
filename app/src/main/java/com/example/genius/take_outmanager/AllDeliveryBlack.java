package com.example.genius.take_outmanager;

import java.util.List;

public class AllDeliveryBlack {

    private List<DeliveryBlackArrBean> deliveryBlackArr;

    public List<DeliveryBlackArrBean> getDeliveryBlackArr() {
        return deliveryBlackArr;
    }

    public void setDeliveryBlackArr(List<DeliveryBlackArrBean> deliveryBlackArr) {
        this.deliveryBlackArr = deliveryBlackArr;
    }

    public static class DeliveryBlackArrBean {
        /**
         * id : 1
         * phone : 15512345632
         * name : 黑名单1
         */

        private String id;
        private String phone;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
