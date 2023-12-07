package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.UserLoginRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.UserRegistrationRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.response.AuthenticationResponse;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;
//import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.security.jwt.JwtTokenProvider;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.UserService;


@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;


    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        // Validate registration request
        // ...

        // Create a new user
        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder().encode(registrationRequest.getPassword())); // Password encryption
        // Set other user properties

        // Save the user in the database
        userService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody UserLoginRequest loginRequest) {
//        try {
//            // Authenticate the user
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    loginRequest.getUsername(), loginRequest.getPassword()));
//
//            // Generate JWT token
////            String token = jwtTokenProvider.generateToken(loginRequest.getUsername());
//
//            // Create authentication response
//            AuthenticationResponse response = new AuthenticationResponse();
//            response.setToken(token);
//            // Set other response properties
//
//            return ResponseEntity.ok(response);
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }

    // Other methods and dependencies
    // ...


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
