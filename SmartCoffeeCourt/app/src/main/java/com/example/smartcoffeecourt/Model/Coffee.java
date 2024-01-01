package com.example.smartcoffeecourt.Model;

public class Coffee {
    private int id;
    private Stall supplier;
    private String name;
    private String description;
    private String status;
    private String price;
    private String imageLink;

    private int star;


    public Coffee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stall getSupplier() {
        return supplier;
    }

    public void setSupplier(Stall supplier) {
        this.supplier = supplier;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", supplier=" + supplier +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", star=" + star +
                '}';
    }
}
