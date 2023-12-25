package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Order;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Các phương thức truy vấn khác nếu cần

    List<Order> findByUser(User user);

    Order findOneById(Long supplierId);

    @Transactional
    @Modifying
    @Query("UPDATE Order r SET r.status = -1 WHERE r.id = :orderId")
    void updateStatus(@Param("orderId") Long orderId);

    List<Order> findAllByStatus(int i);

}