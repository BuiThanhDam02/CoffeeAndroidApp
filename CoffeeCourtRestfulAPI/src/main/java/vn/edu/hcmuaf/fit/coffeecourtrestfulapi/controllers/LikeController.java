package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.LikeRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Like;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.LikeRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikeController {
    // Code xử lý yêu thích tại đây
    @Autowired
    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private CoffeeRepository coffeeRepository;

    @GetMapping("/")
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public ResponseEntity<String> toggleLike(@RequestBody LikeRequest likeRequest){
        Long userId = likeRequest.getUser_id();
        Long coffeeId = likeRequest.getCoffee_id();

        Like existingLike = likeRepository.findByUserIdAndCoffeeId(userId, coffeeId);

        if(existingLike != null){
            likeRepository.delete(existingLike);
            return ResponseEntity.ok("Like removed successfully");
        } else{
            Like newLike = new Like();
            User user = userRepository.findById(userId).orElse(null);
            Coffee coffee = coffeeRepository.findById(coffeeId).orElse(null);
            newLike.setUser(user);
            newLike.setCoffee(coffee);
            likeRepository.save(newLike);
            return ResponseEntity.ok("Like added successfully");

        }
    }

}