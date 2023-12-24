package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order;

import java.util.List;

public class OrderDetailDTO {

    private OrderDTO orderDTO;
    private List<CoffeeDTO> coffeeDTOS;


    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public List<CoffeeDTO> getCoffeeDTOS() {
        return coffeeDTOS;
    }

    public void setCoffeeDTOS(List<CoffeeDTO> coffeeDTOS) {
        this.coffeeDTOS = coffeeDTOS;
    }
}
