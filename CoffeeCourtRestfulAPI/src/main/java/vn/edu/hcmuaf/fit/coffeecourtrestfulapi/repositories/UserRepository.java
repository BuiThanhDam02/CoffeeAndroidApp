package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Các phương thức truy vấn khác nếu cần
    User findOneById(long id);
}