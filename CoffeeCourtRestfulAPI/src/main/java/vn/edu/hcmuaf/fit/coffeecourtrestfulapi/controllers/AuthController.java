
package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.UserLoginRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.UserRegistrationRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.response.AuthenticationResponse;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Role;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.RoleName;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.RoleRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.security.sha.SHATokenProvider;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.UserService;

import java.util.HashSet;
import java.util.Set;


@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SHATokenProvider shaTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        // Validate registration request
        String hashPassword = userService.hashPassword(registrationRequest.getPassword());

        // Create a new user
        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(hashPassword); // Password encryption
        user.setUsername(registrationRequest.getUsername());
        user.setName(registrationRequest.getUsername());
        user.setPhone(registrationRequest.getPhone());
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId(2L);
        role.setName(RoleName.USER);
        roles.add(role);
        user.setRoles(roles);
        // Set other user properties

        // Save the user in the database

        User u = userService.saveUser(user);
        roleRepository.save(role);

        return  ResponseEntity.ok(u);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody UserLoginRequest loginRequest) {
        try {
            // Authenticate the user
          User u =userService.authenticateUser(loginRequest.getEmail(),loginRequest.getPassword());
            if (u != null) {
                System.out.println("1");
                // Generate JWT token
                String token = shaTokenProvider.generateToken(loginRequest.getEmail());
                // Create authentication response
                System.out.println("4");
                AuthenticationResponse response = new AuthenticationResponse();
                System.out.println("5");
                response.setToken(token);
                System.out.println("6");
                response.setUser(u);
                System.out.println("7");
                // Set other response properties
                u.setToken(token);
                userService.saveUser(u);
                return ResponseEntity.ok(response);
            } else {
                System.out.println("2");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (Exception e) {
            System.out.println("3");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}

