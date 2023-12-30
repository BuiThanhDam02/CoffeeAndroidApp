package com.example.smartcoffeecourt.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailModel {

    @SerializedName("orderDTO")
    private Order order;

    @SerializedName("coffeeDTOS")
    private List<CoffeeOrderDetail> coffees;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<CoffeeOrderDetail> getCoffees() {
        return coffees;
    }

    public void setCoffees(List<CoffeeOrderDetail> coffees) {
        this.coffees = coffees;
    }
}
