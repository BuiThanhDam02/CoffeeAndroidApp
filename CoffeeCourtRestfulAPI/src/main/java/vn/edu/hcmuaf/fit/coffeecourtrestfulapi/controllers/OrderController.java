package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Order;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.OrderDetail;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.OrderDetailRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.OrderRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getByUser")
    public List<Order> getByUser(@RequestBody User user) {
        return orderRepository.findByUser(userRepository.findOneById(user.getId()));
    }

    @GetMapping("/getById")
    public OrderDetail getById(@RequestBody Order order) {
        return orderDetailRepository.findOneById(order.getId());
    }
}
