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
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.CartItemRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.OrderDetailRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.OrderRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.UserRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.OrderRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.CartService;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.OrderService;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping("/getByUser")
    public List<OrderDTO> getByUser(@RequestParam("idUser") Long idUser) {
        return orderConverter.orderDTOList(orderRepository.findByUser(userRepository.findOneById(idUser)));
    }

    @GetMapping("/detail/{id}")
    public OrderDetailDTO getById(@PathVariable Long id) {
        List<OrderDetail> orderDetails = orderDetailRepository.findOneOrderId(id);
        return orderDetailConverter.toDto(orderDetails);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestBody OrderRequest orderRequest) {
        System.out.println("order request: " + orderRequest);
        Order order = new Order();

        order.setUser(orderRequest.getUser());
        order.setPhone(orderRequest.getPhone());
        order.setTotalPrice(Float.parseFloat(orderRequest.getTotal()));
        order.setStatus(Integer.parseInt(orderRequest.getStatus()));
        order.setAddress(orderRequest.getAddress());
        order.setType(Integer.parseInt(orderRequest.getType()));

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartItemRequest cartItemRequest : orderRequest.getFoods()) {
            OrderDetail orderDetail = new OrderDetail();
            Coffee coffee = coffeeRepository.findOneById(cartItemRequest.getCoffeeId());
            orderDetail.setCoffee(coffee);
            orderDetail.setQuantity(cartItemRequest.getQuantity());
            orderDetail.setPrice(coffee.getPrice() * cartItemRequest.getQuantity());
            orderDetail.setName(null);

            orderDetail.setOrder(order);
            orderDetails.add(orderDetail);
        }
        order.setOrderDetails(orderDetails);

        System.out.println("Order: " + order);
        if(!orderDetails.isEmpty()) {
            orderRepository.save(order);
            for(OrderDetail orderDetail : orderDetails) {
                orderDetailRepository.save(orderDetail);
            }
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @GetMapping("/getAll")
    public List<OrderDTO> getAllOrderDung() {
        return orderConverter.orderDTOList(orderRepository.findAllByStatus(0));
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        orderRepository.updateStatus(id);
    }

}
