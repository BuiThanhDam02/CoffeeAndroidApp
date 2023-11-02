package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models;

import jakarta.persistence.*;
@Entity
@Table(name = "coffee_discounts")
public class CoffeeDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    private Float discount;

    private Integer status;

    // Getter và Setter cho các thuộc tính

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    // Constructor

    // Các phương thức khác nếu cần
}