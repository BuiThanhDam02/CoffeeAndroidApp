package com.example.smartcoffeecourt.Model;

public class Stall {
    private String name;
    private String image;
    private Integer id;

    public Stall() {
    }

    public Stall(String image, String name, Integer id) {
        this.image = image;
        this.name = name;
        this.id = id;
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

    @Override
    public String toString() {
        return "Stall{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", supplierID=" + id +
                '}';
    }

}
