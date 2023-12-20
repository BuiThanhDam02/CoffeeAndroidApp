package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.UserLoginRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.security.sha.SHATokenProvider;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.AdminService;


@RequestMapping("/api/admin/auth")
@RestController
//@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SHATokenProvider shaTokenProvider;


    @PostMapping("/login")
    public ResponseEntity<Admin> loginUser(@RequestBody UserLoginRequest loginRequest) {
        try {
            // Authenticate the user
            Admin u =adminService.authenticateUser(loginRequest.getEmail(),loginRequest.getPassword());
            if (u != null) {
                System.out.println("1");
                // Generate JWT token
                String token = shaTokenProvider.generateToken(loginRequest.getEmail());
                // Create authentication response

                // Set other response properties
                u.setToken(token);
                return ResponseEntity.ok(u);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (Exception e) {
            System.out.println("3");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}

