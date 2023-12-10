package com.example.smartcoffeecourt.Model;

import com.google.gson.annotations.SerializedName;

public class CoffeeOrderDetail {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("imageLink")
    private String imageLink;
    @SerializedName("supplierName")
    private String supplierName;
    @SerializedName("quantity")
    private Integer quantity;
    @SerializedName("price")
    private String price;

    @SerializedName("supplierID")
    private Integer supplierID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return "CoffeeOrderDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", quantity=" + quantity +
                ", price='" + price + '\'' +
                ", supplierID=" + supplierID +
                '}';
    }
}
