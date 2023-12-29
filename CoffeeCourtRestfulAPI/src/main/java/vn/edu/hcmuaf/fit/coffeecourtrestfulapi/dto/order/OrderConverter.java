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
        orderDTO.setUser_id(order.getUser().getId());
        orderDTO.setName(order.getName());
        orderDTO.setEmail(order.getUser().getEmail());
        orderDTO.setPhone(order.getUser().getPhone());
        if (order.getType() == 1){
            orderDTO.setType("Tại chỗ");
            orderDTO.setStatus(order.getStatus() == 0?"Chờ xác nhận":"Đã hoàn thành");
        }else{
            orderDTO.setType("Đặt hàng");
            orderDTO.setStatus(order.getStatus() == 0?"Chờ xác nhận":order.getStatus() == 1?"Đang giao":order.getStatus() == 2?"Đã giao hàng":"Đã hủy");
        }
        orderDTO.setStatusInt(order.getStatus());
        orderDTO.setTotalPrice(String.valueOf(order.getTotalPrice()));
        orderDTO.setCreated_at(order.getCreated_at());
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
