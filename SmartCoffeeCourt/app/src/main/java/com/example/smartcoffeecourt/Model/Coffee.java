package com.example.smartcoffeecourt.Model;

public class Coffee {
    private String name;
    private String description;
    private String discount;
    private String image;
    private String price;
    private String star;
    private String status;
    private Integer supplierID;

    public Coffee() {
    }

    public Coffee(String description, String discount, String image, String name, String price, String star, String status, Integer supplierID) {
        this.description = description;
        this.discount = discount;
        this.image = image;
        this.name = name;
        this.price = price;
        this.star = star;
        this.status = status ;
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getStatus(){ return status; }

    public void setStatus(String status) { this.status = status; }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }
}
