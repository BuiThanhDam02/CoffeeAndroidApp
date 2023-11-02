package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    // Các phương thức truy vấn khác nếu cần
}