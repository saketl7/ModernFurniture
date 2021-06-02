package com.example.modernfurniture;

import java.io.Serializable;

public class getOrderData implements Serializable {

    private String method, orderId, address, userName;
    private String date;
    private String time;

    public getOrderData() {
    }

    public getOrderData(String method,String name, String orderId, String address, String date, String time) {
        this.method = method;
        this.userName = name;
        this.orderId = orderId;
        this.address = address;
        this.date = date;
        this.time = time;
    }

    public String getUserName(){return userName;}

    public String getMethod() {
        return method;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}



