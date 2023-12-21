package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order.OrderConverter;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order.OrderDTO;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order.OrderDetailConverter;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order.OrderDetailDTO;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    @Autowired
    OrderConverter orderConverter;
    @Autowired
    OrderDetailConverter orderDetailConverter;

    @GetMapping("/getByUser")
    public List<OrderDTO> getByUser(@RequestParam("idUser") Long idUser) {
        return orderConverter.orderDTOList(orderRepository.findByUser(userRepository.findOneById(idUser)));
    }

    @GetMapping("/detail/getByOrderId")
    public OrderDetailDTO getById(@RequestParam("id") Long id) {
        List<OrderDetail> orderDetails = orderDetailRepository.findOneOrderId(id);
        return orderDetailConverter.toDto(orderDetails);
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

    @GetMapping("/getAll")
    public List<OrderDTO> getAllOrderDung() {
        return orderConverter.orderDTOList(orderRepository.findAll());
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        orderDetailRepository.deleteByOrder(orderRepository.findOneById(id));
        orderRepository.deleteById(id);
    }

}
