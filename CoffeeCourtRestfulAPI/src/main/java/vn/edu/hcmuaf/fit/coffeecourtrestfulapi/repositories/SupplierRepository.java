package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    // Các phương thức truy vấn khác nếu cần
    Supplier findOneById(Long id);
}