package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.UserRepository;

import java.math.BigInteger;
import java.security.MessageDigest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        // Thực hiện đăng ký người dùng
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        // Tìm người dùng dựa trên username
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        // Tìm người dùng dựa trên username
        return userRepository.findByEmail(email);
    }
    public String hashPassword(String pass){
        try {
            MessageDigest sha256Digest=MessageDigest.getInstance("SHA-256");
            byte[] inputBytes = pass.getBytes();
            byte[] output=sha256Digest.digest(inputBytes);
            BigInteger num=new BigInteger(1,output);
            return num.toString(16);
        }catch (Exception e){
            e.printStackTrace();
            return pass;
        }
    }
    public User authenticateUser(String email, String password) {
        User user = findByEmail(email);
//        System.out.println(user.toString());
        String hashP = hashPassword(password);
//        System.out.println(hashP);
        if (user != null && user.getPassword().equals(hashP)) {
//            System.out.println("login success");
            return user;
        } else {
            return null;
        }
    }
    // Các phương thức và logic khác liên quan đến người dùng
    // ...
}
