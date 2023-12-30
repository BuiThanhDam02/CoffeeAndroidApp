package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.CoffeeImage;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.CoffeeStar;

import java.util.List;

@Repository
public interface CoffeeStarRepository extends JpaRepository<CoffeeStar, Long> {
    // Các phương thức truy vấn khác nếu cần
    List<CoffeeStar> findByCoffeeId(Long coffeeId);
    @Modifying
    @Query("UPDATE CoffeeStar cs SET cs.status = -1 WHERE cs.coffee.id = :id")
    void deleteByCoffeeId(@Param("id") Long id);

    @Query("SELECT cs FROM CoffeeStar cs WHERE cs.status = 0 ORDER BY cs.star DESC")
    List<CoffeeStar> getGreatCoffee();
}