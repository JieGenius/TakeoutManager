package com.example.genius.take_outmanager;

public class SendData {
    public String sid;
    public String token;
    public String appid;
    public String templateid;
    public String param;
    public String mobile;

    public SendData(String param, String mobile) {
        sid = "273c5933d67b8fe47e590820e7613432";
        token = "6a32157364efbeaf31748d4c367c0fee";
        appid = "ee1408efd5d244c4bcedf838a7ac69e1";
        templateid = "413394";
        this.param = param;
        this.mobile = mobile;
    }
}
