package com.example.smartcoffeecourt.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailModel {

    @SerializedName("id")
    private Long id;
    @SerializedName("status")
    private int status;
    @SerializedName("address")
    private String address;

    @SerializedName("coffeeDTOS")
    private List<CoffeeOrderDetail> coffees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CoffeeOrderDetail> getCoffees() {
        return coffees;
    }

    public void setCoffees(List<CoffeeOrderDetail> coffees) {
        this.coffees = coffees;
    }
}
