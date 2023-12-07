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
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.OrderService;

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
    @Autowired
    OrderService orderService;

    @GetMapping("/getByUser")
    public List<Order> getByUser(@RequestBody User user) {
        return orderRepository.findByUser(userRepository.findOneById(user.getId()));
    }

    @GetMapping("/getById")
    public OrderDetail getById(@RequestBody Order order) {
        return orderDetailRepository.findOneById(order.getId());
    }


    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestParam("idUser") Long idUser) {
        Cart cart = cartService.getCart();
        User user = userRepository.findOneById(idUser);
        Order order = orderService.processPayment(cart, user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
}
