package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.LikeRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.response.LikeResponse;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping("/all")
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @PostMapping("/toggleLike")
    public ResponseEntity<LikeResponse> toggleLike(@RequestBody LikeRequest likeRequest){
        System.out.println(likeRequest);
        Long userId = likeRequest.getUser_id();
        Long coffeeId = likeRequest.getCoffee_id();

        Like existingLike = likeRepository.findByUserIdAndCoffeeId(userId, coffeeId);

        if(existingLike != null){
            likeRepository.delete(existingLike);
            return new ResponseEntity<>(new LikeResponse("Like removed successfully", true), HttpStatus.OK);
        } else{
            Like newLike = new Like();
            User user = userRepository.findById(userId).orElse(null);
            Coffee coffee = coffeeRepository.findById(coffeeId).orElse(null);
            newLike.setUser(user);
            newLike.setCoffee(coffee);
            likeRepository.save(newLike);
            return new ResponseEntity<>(new LikeResponse("Like added successfully", true), HttpStatus.OK);
        }
    }

    @GetMapping("/checkLike")
    public ResponseEntity<LikeResponse> checkLike(@RequestParam Long user_id, @RequestParam Long coffee_id) {
        Like existingLike = likeRepository.findByUserIdAndCoffeeId(user_id, coffee_id);
        if (existingLike != null) {
            return new ResponseEntity<>(new LikeResponse("Liked", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new LikeResponse("Not liked", false), HttpStatus.OK);
        }
    }

}