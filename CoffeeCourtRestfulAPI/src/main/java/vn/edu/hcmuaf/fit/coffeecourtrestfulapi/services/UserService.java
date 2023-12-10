package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        // Thực hiện đăng ký người dùng
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        // Tìm người dùng dựa trên username
        return userRepository.findByUsername(username);
    }

    // Các phương thức và logic khác liên quan đến người dùng
    // ...
}
