package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Cart;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;

@Service
public class CartService {
    private Cart cart;

    public CartService() {
        cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Coffee coffee, int quantity) {
        cart.addItem(coffee, quantity);
    }

    public void removeFromCart(Coffee coffee) {
        cart.removeItem(coffee);
    }

    public void updateCartItemQuantity(Coffee coffee, int quantity) {
        cart.updateItemQuantity(coffee, quantity);
    }

    public void clearCart() {
        cart.clearCart();
    }
}