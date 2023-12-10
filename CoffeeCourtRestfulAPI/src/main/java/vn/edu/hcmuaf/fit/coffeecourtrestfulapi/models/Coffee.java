package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models;


import jakarta.persistence.*;



@Entity
@Table(name = "coffees")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private String name;

    private String description;

    private Integer status;

    private Float price;

    @Transient // Đánh dấu thuộc tính này để không được ánh xạ vào cơ sở dữ liệu
    private String imageLink;


    @Transient
    private int star;
    public Coffee() {

    }

    // Getter và Setter cho các thuộc tính


    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    // Constructor

    public Coffee(Long id, Supplier supplier, String name, String description, Integer status, Float price, String imageLink) {
        this.id = id;
        this.supplier = supplier;
        this.name = name;
        this.description = description;
        this.status = status;
        this.price = price;
        this.imageLink = imageLink;
    }

    // Các phương thức khác nếu cần
}
