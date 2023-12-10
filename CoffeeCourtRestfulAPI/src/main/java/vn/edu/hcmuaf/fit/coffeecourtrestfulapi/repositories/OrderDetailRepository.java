package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Order;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    // Các phương thức truy vấn khác nếu cần
    List<OrderDetail> findByOrder(Order order);

    void deleteByOrder(Order order);

    @Query("SELECT or FROM OrderDetail or WHERE or.order.id = ?1")
    List<OrderDetail> findOneOrderId(Long id);

}