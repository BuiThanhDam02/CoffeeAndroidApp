package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.CoffeeImage;

import java.util.List;

@Repository
public interface CoffeeImageRepository extends JpaRepository<CoffeeImage, Long> {
    // Các phương thức truy vấn khác nếu cần
    List<CoffeeImage> findByCoffeeId(Long coffeeId);
    @Modifying
    @Query("UPDATE CoffeeImage ci SET ci.status = -1 WHERE ci.coffee.id = :id")
    void deleteByCoffeeId(@Param("id") Long id);
}