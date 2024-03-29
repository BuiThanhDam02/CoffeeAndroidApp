package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    // Các phương thức truy vấn khác nếu cần
    Like findByUserIdAndCoffeeId(Long userId, Long coffeeId);
}