package com.example.modernfurniture;

import java.io.Serializable;

public class getWishlistData implements Serializable {
    private String id, image, type,name;
    private double price;

    public getWishlistData(){}

    public getWishlistData(String id, String image, String type, String name, double price) {
        this.id = id;
        this.image = image;
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
