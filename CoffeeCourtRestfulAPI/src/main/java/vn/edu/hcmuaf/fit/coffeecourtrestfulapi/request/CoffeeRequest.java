package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.request;

public class CoffeeRequest {
    private Long id;
    private Long supplierId;

    private String name;

    private String description;

    private Integer status;

    private Float price;

    private String imageLink;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public CoffeeRequest() {
    }

    public CoffeeRequest(Long supplierId, String name, String description, Integer status, Float price, String imageLink) {
        this.supplierId = supplierId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.price = price;
        this.imageLink = imageLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CoffeeRequest{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
