package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order;

import java.sql.Timestamp;

public class OrderDTO {
    private Long id;

    private String name;

    private Long user_id;

    private String phone;

    private String password;

    private String address;

    private String email;

    private String status;

    private Integer statusInt;

    private String totalPrice;

    private String StatusBg;

    private String type;
    private Timestamp created_at;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatusBg() {
        return StatusBg;
    }

    public void setStatusBg(String statusBg) {
        StatusBg = statusBg;
    }

    public Integer getStatusInt() {
        return statusInt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatusInt(Integer statusInt) {
        this.statusInt = statusInt;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_id=" + user_id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", statusInt=" + statusInt +
                ", totalPrice='" + totalPrice + '\'' +
                ", StatusBg='" + StatusBg + '\'' +
                ", type='" + type + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
