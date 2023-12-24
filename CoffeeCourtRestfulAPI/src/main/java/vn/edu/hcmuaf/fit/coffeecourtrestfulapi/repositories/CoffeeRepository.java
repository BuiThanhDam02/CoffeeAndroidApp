package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;

import java.util.List;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    // Các phương thức truy vấn khác nếu cần
    List<Coffee> findByNameContaining(String name);
    List<Coffee> findBySupplierId(Long supplier_id);
    Coffee findByName(String name);
    Coffee findOneById(Long id);

    @Modifying
    @Query("UPDATE Coffee c SET c.status = -1 WHERE c.id = :id")
    void deleteCoffeeById(@Param("id") Long id);
}