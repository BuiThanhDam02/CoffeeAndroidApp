package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order;

public class OrderDTO {
    private Long id;

    private String name;

    private String phone;

    private String password;

    private String email;

    private String status;

    private float totalPrice;

    private String StatusBg;

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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatusBg() {
        return StatusBg;
    }

    public void setStatusBg(String statusBg) {
        StatusBg = statusBg;
    }
}
