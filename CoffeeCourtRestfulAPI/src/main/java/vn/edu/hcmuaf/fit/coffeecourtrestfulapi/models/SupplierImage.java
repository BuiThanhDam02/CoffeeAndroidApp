package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "supplier_images")
public class SupplierImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // Getter và Setter cho các thuộc tính

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    // Constructor

    // Các phương thức khác nếu cần
}