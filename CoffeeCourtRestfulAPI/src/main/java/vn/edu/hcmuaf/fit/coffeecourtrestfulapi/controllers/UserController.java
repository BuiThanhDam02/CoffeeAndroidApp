package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/id")
    public User getUserById(@RequestParam Long id) {
        return userRepository.findOneById(id);
    }

    @GetMapping("/paged")
    public List<User> getAllUserPaged(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updateUser) {
        Optional<User> existingUser  = Optional.ofNullable(userRepository.findOneById(userId));

        if(existingUser.isPresent()) {
            User user = existingUser.get();

            user.setName(Optional.ofNullable(updateUser.getName()).orElse(user.getName()));
            user.setEmail(Optional.ofNullable(updateUser.getEmail()).orElse(user.getEmail()));
            user.setAddress(Optional.ofNullable(updateUser.getAddress()).orElse(user.getAddress()));
            user.setPhone(Optional.ofNullable(updateUser.getPhone()).orElse(user.getPhone()));

            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
