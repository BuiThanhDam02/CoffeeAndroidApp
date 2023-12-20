package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Các phương thức truy vấn khác nếu cần
    Admin findOneById(long id);

    Admin findByEmail(String email);
}