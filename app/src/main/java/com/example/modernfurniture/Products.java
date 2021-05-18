package com.example.modernfurniture;

public class Products {
    private String name,type,imageUrl;
    private double price;

    public Products(String name1, String type1, String imageUrl1, double price1){
        this.name = name1;
        this.type = type1;
        this.imageUrl = imageUrl1;
        this.price = price1;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }



}
