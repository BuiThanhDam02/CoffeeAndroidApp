package com.example.smartcoffeecourt.Model;

public class CartItem {
    private Long coffeeId;
    private String name;
    private String price;
    private String quantity;
    private String discount;

    public CartItem() {
    }

    public CartItem(String name, String price, String quantity, String discount) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public CartItem(Long coffeeId,String name, String price, String quantity, String discount) {
        this.coffeeId = coffeeId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(Long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "coffeeId=" + coffeeId +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", discount='" + discount + '\'' +
                '}';
    }
}
