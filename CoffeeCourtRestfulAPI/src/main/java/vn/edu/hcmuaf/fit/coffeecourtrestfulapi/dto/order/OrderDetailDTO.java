package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order;

import java.util.List;

public class OrderDetailDTO {

    private Long id;
    private int status;
    private String address;
    private List<CoffeeDTO> coffeeDTOS;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CoffeeDTO> getCoffeeDTOS() {
        return coffeeDTOS;
    }

    public void setCoffeeDTOS(List<CoffeeDTO> coffeeDTOS) {
        this.coffeeDTOS = coffeeDTOS;
    }
}
