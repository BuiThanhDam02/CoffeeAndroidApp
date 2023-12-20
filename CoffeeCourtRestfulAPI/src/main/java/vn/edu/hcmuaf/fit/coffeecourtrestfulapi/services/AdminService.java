package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.AdminRepository;


import java.math.BigInteger;
import java.security.MessageDigest;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findByEmail(String email) {
        // Tìm người dùng dựa trên username
        return adminRepository.findByEmail(email);
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
    public Admin authenticateUser(String email, String password) {
        Admin admin = findByEmail(email);
//        System.out.println(user.toString());
        String hashP = hashPassword(password);
//        System.out.println(hashP);
        if (admin != null && admin.getPassword().equals(hashP)) {
//            System.out.println("login success");
            return admin;
        } else {
            return null;
        }
    }
    // Các phương thức và logic khác liên quan đến người dùng
    // ...
}
