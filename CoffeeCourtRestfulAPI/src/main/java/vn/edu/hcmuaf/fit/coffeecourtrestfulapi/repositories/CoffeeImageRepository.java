package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.CoffeeImage;

import java.util.List;

@Repository
public interface CoffeeImageRepository extends JpaRepository<CoffeeImage, Long> {
    // Các phương thức truy vấn khác nếu cần
    List<CoffeeImage> findByCoffeeId(Long coffeeId);
    void deleteByCoffeeId(Long id);
}