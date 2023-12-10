package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "supplier_images")
public class SupplierImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long supplierId;

    @Column(name = "imageLink") // Đặt tên cột cụ thể cho trường imageLink
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id") // Đặt tên cột cụ thể cho quan hệ và chỉ định cột tham chiếu
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