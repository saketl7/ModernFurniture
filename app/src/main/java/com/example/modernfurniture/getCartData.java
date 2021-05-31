package com.example.modernfurniture;

public class getCartData {
    private String image, id;
    private String name;
    private double price,quantity;

    public getCartData() {}

    public getCartData(String id,String image2, String name2, double price2, double quantity2) {
        this.id = id;
        this.image = image2;
        this.name = name2;
        this.price = price2;
        this.quantity = quantity2;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }


    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }


    public double getQuantity() {
        return quantity;
    }

}

