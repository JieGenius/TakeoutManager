package com.example.genius.take_outmanager;

import java.util.List;

public class AllShopBlack {

    private List<ShopBlackArrBean> shopBlackArr;

    public List<ShopBlackArrBean> getShopBlackArr() {
        return shopBlackArr;
    }

    public void setShopBlackArr(List<ShopBlackArrBean> shopBlackArr) {
        this.shopBlackArr = shopBlackArr;
    }

    public static class ShopBlackArrBean {
        /**
         * id : 1
         * phone : 15512342345
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
