package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order processPayment(Cart cart, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setName(user.getName());
        order.setPhone(user.getPhone());
        order.setAddress(user.getAddress());
        order.setNote("");

        List<OrderDetail> orderDetails = convertCartToOrderDetails(cart, order);

        order.setOrderDetails(orderDetails);

        calculateTotalPrice(order);
        order = orderRepository.save(order);
        cart.clearCart();
        return order;
    }

    private List<OrderDetail> convertCartToOrderDetails(Cart cart, Order order) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (Map.Entry<Coffee, Integer> entry : cart.getItems().entrySet()) {
            Coffee coffee = entry.getKey();
            Integer quantity = entry.getValue();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setCoffee(coffee);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(coffee.getPrice());
            // Các thông tin khác của OrderDetail

            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
    private void calculateTotalPrice(Order order) {
        float total = 0.0f;

        for (OrderDetail orderDetail : order.getOrderDetails()) {
            float itemTotal = orderDetail.getSubtotal();
            total += itemTotal;
        }
        order.setTotalPrice(total);
    }
}
