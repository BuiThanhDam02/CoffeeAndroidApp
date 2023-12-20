package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Supplier;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.SupplierImage;

@Repository
public interface SupplierImageRepository extends JpaRepository<SupplierImage, Long> {
    // Các phương thức truy vấn khác nếu cần
    SupplierImage findBySupplier(Supplier supplier);

    SupplierImage findBySupplierId(Long id);
}