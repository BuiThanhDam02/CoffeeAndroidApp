package com.example.smartcoffeecourt.Model;

import com.google.gson.annotations.SerializedName;

public class Stall {
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("supplierID")
    private Integer supplierID;

    public Stall() {
    }

    public Stall(String image, String name, Integer supplierID) {
        this.image = image;
        this.name = name;
        this.supplierID = supplierID;
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
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return "Stall{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", supplierID=" + supplierID +
                '}';
    }
}
