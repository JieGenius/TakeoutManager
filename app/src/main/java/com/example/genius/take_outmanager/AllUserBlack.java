package com.example.genius.take_outmanager;

import java.util.List;

public class AllUserBlack {

    private List<UserBlackArrBean> userBlackArr;

    public List<UserBlackArrBean> getUserBlackArr() {
        return userBlackArr;
    }

    public void setUserBlackArr(List<UserBlackArrBean> userBlackArr) {
        this.userBlackArr = userBlackArr;
    }

    public static class UserBlackArrBean {
        /**
         * id : 1
         * phone : 15124562345
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
