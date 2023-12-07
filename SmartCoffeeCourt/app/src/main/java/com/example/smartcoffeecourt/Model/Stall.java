package com.example.smartcoffeecourt.Model;

public class Stall {
    private String name;
    private String image;
    private Integer id;

    public Stall() {
    }

    public Stall(String image, String name, Integer supplierID) {
        this.image = image;
        this.name = name;
        this.id = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSupplierID() {
        return id;
    }

    public void setSupplierID(Integer supplierID) {
        this.id = supplierID;
    }

}
