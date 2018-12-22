package com.example.genius.take_outmanager;

import java.util.List;

public class AllUserBean {

    private List<UserArrBean> userArr;

    public List<UserArrBean> getUserArr() {
        return userArr;
    }

    public void setUserArr(List<UserArrBean> userArr) {
        this.userArr = userArr;
    }

    public static class UserArrBean {
        /**
         * id : 2015662
         * name : 韩宇
         * phone : 18406533326
         * address : 太原市万柏林区下元街道大王社区3号楼2单元1101
         */

        private String id;
        private String name;
        private String phone;
        private String address;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
