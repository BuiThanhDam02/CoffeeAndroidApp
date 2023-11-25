package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request;

public class CartItemRequest {
    private Long coffeeId;
    private int quantity;

    // Getters and setters

    public Long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(Long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
