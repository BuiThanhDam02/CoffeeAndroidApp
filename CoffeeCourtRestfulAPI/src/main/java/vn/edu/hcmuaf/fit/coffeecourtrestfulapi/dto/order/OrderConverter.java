package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order;

import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Order;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {

    public OrderDTO toDto(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setName(order.getName());
        orderDTO.setEmail(order.getUser().getEmail());
        orderDTO.setPhone(order.getUser().getPhone());
        orderDTO.setStatus(order.getStatus() == 1?"Đã Giao":"Đang Giao");
        orderDTO.setStatusInt(order.getStatus());
        orderDTO.setTotalPrice(String.valueOf(order.getTotalPrice()));
        return orderDTO;
    }

    public List<OrderDTO> orderDTOList(List<Order> orders){
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(Order order : orders){
            orderDTOS.add(toDto(order));
        }
        return orderDTOS;
    }

}
