package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Coffee, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public Map<Coffee, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Coffee, Integer> items) {
        this.items = items;
    }

    public void addItem(Coffee coffee, int quantity) {
        items.put(coffee, quantity);
    }

    public void removeItem(Coffee coffee) {
        items.remove(coffee);
    }

    public void updateItemQuantity(Coffee coffee, int quantity) {
        if (items.containsKey(coffee)) {
            items.put(coffee, quantity);
        }
    }

    public void clearCart() {
        items.clear();
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (int quantity : items.values()) {
            totalQuantity += quantity;
        }
        return totalQuantity;
    }

    public float getTotalPrice() {
        float totalPrice = 0.0f;
        for (Map.Entry<Coffee, Integer> entry : items.entrySet()) {
            Coffee coffee = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += coffee.getPrice() * quantity;
        }
        return totalPrice;
    }
}