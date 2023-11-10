package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services;


import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua
    User save(User user);
}
