package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.CartItemRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;

import java.util.List;

public class OrderRequest {
    private User user;
    private String phone;
    private String total;
    private String status;
    private String address;
    private Integer supplierID;
    private String type;
    private List<CartItemRequest> foods;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<CartItemRequest> getFoods() {
        return foods;
    }

    public void setFoods(List<CartItemRequest> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "user=" + user +
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
