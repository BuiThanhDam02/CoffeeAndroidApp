package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models;
import jakarta.persistence.*;

@Entity
@Table(name = "coffee_images")
public class CoffeeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    private String imageLink;

    private Integer status;

    public CoffeeImage() {

    }

    // Getter và Setter cho các thuộc tính

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    // Constructor

    public CoffeeImage(Long id, Coffee coffee, String imageLink, Integer status) {
        this.id = id;
        this.coffee = coffee;
        this.imageLink = imageLink;
        this.status = status;
    }

    // Các phương thức khác nếu cần
}