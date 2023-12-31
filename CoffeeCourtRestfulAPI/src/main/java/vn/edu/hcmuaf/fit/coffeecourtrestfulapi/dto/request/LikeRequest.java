package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class LikeRequest {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long user_id;
    @ManyToOne
    @JoinColumn(name = "coffee_id")
    private Long coffee_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCoffee_id() {
        return coffee_id;
    }

    public void setCoffee_id(Long coffee_id) {
        this.coffee_id = coffee_id;
    }
}
