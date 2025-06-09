package com.example.uap_pam;

public class Plant {
    private String plant_name;
    private String price;
    private String description;

    public Plant(String plant_name, String price, String description) {
        this.plant_name = plant_name;
        this.price = price;
        this.description = description;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}