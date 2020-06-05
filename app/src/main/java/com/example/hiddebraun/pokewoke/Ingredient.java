package com.example.hiddebraun.pokewoke;

public class Ingredient {

    public int id;
    public String name;
    public String type;
    public Double price;
    public String imageUrl;

    public Ingredient() {

    }

    public Ingredient(int id, String name, Double price, String type, String image_url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.imageUrl = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
