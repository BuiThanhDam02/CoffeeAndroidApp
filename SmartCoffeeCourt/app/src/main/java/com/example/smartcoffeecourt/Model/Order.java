package com.example.smartcoffeecourt.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {


    private int id;
    private User user;
    private String phone;
    @SerializedName("totalPrice")
    private String total;

    private String status;
    @SerializedName("address")
    private String address;
    private Integer supplierID;
    private String type;
    private List<CartItem> foods;

    public Order() {
    }

    public Order(String phone, CartGroupItem t) {
        this.phone = phone;
        this.total = t.getTotal().toString();
        this.supplierID = t.getSupplierID();
        this.foods = t.getCartItemList();
        this.type = t.getType();
        this.status = "0"; // 0: preparing, 1: completed, 2: received
    }

    public Order(User user, CartGroupItem t) {
        this.user = user;
        this.total = t.getTotal().toString();
        this.supplierID = t.getSupplierID();
        this.foods = t.getCartItemList();
        this.type = t.getType();
        this.status = "0"; // 0: preparing, 1: completed, 2: received
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CartItem> getFoods() {
        return foods;
    }

    public void setFoods(List<CartItem> foods) {
        this.foods = foods;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", phone='" + phone + '\'' +
                ", total='" + total + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", supplierID=" + supplierID +
                ", type='" + type + '\'' +
                ", foods=" + foods +
                '}';
    }
}