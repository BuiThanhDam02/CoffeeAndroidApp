package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.CoffeeImage;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.CoffeeStar;

import java.util.List;

@Repository
public interface CoffeeStarRepository extends JpaRepository<CoffeeStar, Long> {
    // Các phương thức truy vấn khác nếu cần
    List<CoffeeStar> findByCoffeeId(Long coffeeId);
    void deleteByCoffeeId(Long id);

}