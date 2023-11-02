package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.CoffeeStar;

@Repository
public interface CoffeeStarRepository extends JpaRepository<CoffeeStar, Long> {
    // Các phương thức truy vấn khác nếu cần
}