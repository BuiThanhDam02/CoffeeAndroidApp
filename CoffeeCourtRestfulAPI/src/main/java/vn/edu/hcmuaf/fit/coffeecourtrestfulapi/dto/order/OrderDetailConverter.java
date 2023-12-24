package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.CoffeeImage;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Order;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.OrderDetail;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeImageRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeRepository;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class OrderDetailConverter {
    @Autowired
    private CoffeeImageRepository coffeeImageRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    OrderConverter orderConverter;

    public OrderDetailDTO toDto(List<OrderDetail> orders){
        //List<OrderDetailDTO> orderDTOS = new ArrayList<>();
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        if(orders.isEmpty()) return orderDetailDTO;
        orderDetailDTO.setOrderDTO(orderConverter.toDto(orders.get(0).getOrder()));

        List<CoffeeDTO> coffeeDTOS = new ArrayList<>();
        //Map<Long,List<CoffeeDTO>> map = new HashMap<>();
        for(OrderDetail orderDetail : orders){
            coffeeDTOS.add(toCoffeeDTO(orderDetail.getCoffee(),orderDetail.getQuantity()));
          /*  Long id =  orderDetail.getOrder().getId();
            if(map.containsKey(id)){
                map.get(id).add(toCoffeeDTO(orderDetail.getCoffee()));
            } else {
                map.put(id,new ArrayList<>());
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setId(orderDetail.getId());
                orderDTOS.add(orderDetailDTO);
            }*/
        }
     /*   for(OrderDetailDTO orderDetailDTO : orderDTOS){
            orderDetailDTO.setCoffeeDTOS(map.get(orderDetailDTO.getId()));
        }*/
        orderDetailDTO.setCoffeeDTOS(coffeeDTOS);
        return orderDetailDTO;
    }

    public CoffeeDTO toCoffeeDTO(Coffee coffee, Integer quantity){
        CoffeeDTO coffeeDTO = new CoffeeDTO();
        coffeeDTO.setName(coffee.getName());
        List<CoffeeImage> coffeeImages = coffeeImageRepository.findByCoffeeId(coffee.getId());
        if (!coffeeImages.isEmpty()) {
            coffeeDTO.setImageLink(coffeeImages.get(0).getImageLink());
        }
        coffeeDTO.setPrice(String.valueOf(coffee.getPrice()));
        coffeeDTO.setSupplierName(coffee.getSupplier().getName());
        coffeeDTO.setId(coffee.getId());
        coffeeDTO.setQuantity(quantity);
        return coffeeDTO;
    }
}
