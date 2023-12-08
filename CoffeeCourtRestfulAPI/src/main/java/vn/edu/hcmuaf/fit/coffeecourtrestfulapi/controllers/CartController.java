package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.CartItemRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Cart;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    CoffeeRepository coffeeRepository;
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItemRequest cartItemRequest) {
        Long coffeeId = cartItemRequest.getCoffeeId();
        int quantity = cartItemRequest.getQuantity();

        // Retrieve the Coffee from the database using the coffeeId
        Coffee coffee = coffeeRepository.findById(coffeeId).orElse(null);
        if (coffee == null) {
            return ResponseEntity.badRequest().body("Invalid coffeeId");
        }

        cartService.addToCart(coffee, quantity);
        return ResponseEntity.ok("Item added to cart successfully");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCartItem(@RequestBody CartItemRequest cartItemRequest) {
        Long coffeeId = cartItemRequest.getCoffeeId();
        int quantity = cartItemRequest.getQuantity();

        // Retrieve the Coffee from the database using the coffeeId
        Coffee coffee = coffeeRepository.findById(coffeeId).orElse(null);
        if (coffee == null) {
            return ResponseEntity.badRequest().body("Invalid coffeeId");
        }

        cartService.updateCartItemQuantity(coffee, quantity);
        return ResponseEntity.ok("Cart item updated successfully");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestBody CartItemRequest cartItemRequest) {
        Long coffeeId = cartItemRequest.getCoffeeId();

        // Retrieve the Coffee from the database using the coffeeId
        Coffee coffee = coffeeRepository.findById(coffeeId).orElse(null);
        if (coffee == null) {
            return ResponseEntity.badRequest().body("Invalid coffeeId");
        }

        cartService.removeFromCart(coffee);
        return ResponseEntity.ok("Item removed from cart successfully");
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearCart() {
        cartService.clearCart();
        return ResponseEntity.ok("Cart cleared successfully");
    }

    @GetMapping("/view")
    public ResponseEntity<Cart> viewCart() {
        Cart cart = cartService.getCart();
        return ResponseEntity.ok(cart);
    }
}