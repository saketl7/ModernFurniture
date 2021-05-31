package com.example.modernfurniture;

public class getOrderData {

    private String name, method;
    private String date;
    private String time;
    private double price, quantity;

    public getOrderData() {
    }

    public getOrderData(String name, String method, String date, String time, double price, double quantity) {
        this.name = name;
        this.method = method;
        this.date = date;
        this.time = time;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getMethod() {
        return method;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }
}



