package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order.*;
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
    CoffeeRepository coffeeRepository;

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

    @GetMapping("/detail/{id}")
    public OrderDetailDTO getById(@PathVariable Long id) {
        List<OrderDetail> orderDetails = orderDetailRepository.findOneOrderId(id);
        return orderDetailConverter.toDto(orderDetails);
    }
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody OrderDetailDTO od) {
        Order o  =  new Order();
        o.setId(od.getOrderDTO().getId());
        o.setUser(userRepository.findOneById(od.getOrderDTO().getUser_id()));
        o.setName(od.getOrderDTO().getName());
        o.setAddress(od.getOrderDTO().getAddress());
        o.setCreated_at(od.getOrderDTO().getCreated_at());
        o.setPhone(od.getOrderDTO().getPhone());
        o.setStatus(od.getOrderDTO().getStatusInt());
        o.setTotalPrice(Float.parseFloat(od.getOrderDTO().getTotalPrice()));
        o.setType(Integer.parseInt(od.getOrderDTO().getType()));

        for(CoffeeDTO cd : od.getCoffeeDTOS()){
            OrderDetail odd = new OrderDetail();
            odd.setOrder(o);
            odd.setCoffee(coffeeRepository.findOneById(cd.getId()));
            odd.setPrice(odd.getPrice());
            odd.setQuantity(odd.getQuantity());
            orderDetailRepository.save(odd);
        }
        orderRepository.save(o);
        return new ResponseEntity<>("Create order successfully",HttpStatus.OK);
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
        return orderConverter.orderDTOList(orderRepository.findAllByStatus(0));
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        orderRepository.updateStatus(id);
    }

}
