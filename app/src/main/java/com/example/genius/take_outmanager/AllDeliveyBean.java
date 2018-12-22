package com.example.genius.take_outmanager;

import java.util.List;

public class AllDeliveyBean {

    private List<DeliveryArrBean> deliveryArr;

    public List<DeliveryArrBean> getDeliveryArr() {
        return deliveryArr;
    }

    public void setDeliveryArr(List<DeliveryArrBean> deliveryArr) {
        this.deliveryArr = deliveryArr;
    }

    public static class DeliveryArrBean {
        /**
         * id : 4253664
         * name : 韩辉
         * phone : 18266593781
         * grade : 1.0
         */

        private String id;
        private String name;
        private String phone;
        private String grade;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }
}
