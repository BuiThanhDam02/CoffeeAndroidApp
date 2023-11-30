package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.OrderDetailRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.OrderRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.UserRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.CartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CartService cartService;

    @GetMapping("/getByUser")
    public List<Order> getByUser(@RequestBody User user) {
        return orderRepository.findByUser(userRepository.findOneById(user.getId()));
    }

    @GetMapping("/getById")
    public OrderDetail getById(@RequestBody Order order) {
        return orderDetailRepository.findOneById(order.getId());
    }

    private void calculateTotalPrice(Order order) {
        float total = 0.0f;

        for (OrderDetail orderDetail : order.getOrderDetails()) {
            float itemsPrice = orderDetail.getSubtotal();
            total += itemsPrice;
        }
        order.setTotalPrice(total);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestBody Order order) {
//        if(principal == null) {
//            return ResponseEntity.badRequest().body("Inavalid");
//        }
//        String username = principal.getName();
//

        Cart cart = cartService.getCart();
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderDetails(converCartItemsToOrderDetails(cart.getItems()));
        calculateTotalPrice(order);

        Order savedOrder = orderRepository.save(order);

        cartService.clearCart();
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    private List<OrderDetail> converCartItemsToOrderDetails(Map<Coffee, Integer> cartItems) {
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (Map.Entry<Coffee, Integer> entry : cartItems.entrySet()) {
            Coffee coffee = entry.getKey();
            int quantity = entry.getValue();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCoffee(coffee);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(coffee.getPrice());
            orderDetail.setDiscount(0.0f);
            orderDetail.setName(coffee.getName());

            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
}
