package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
