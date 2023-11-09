package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Like;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.LikeRepository;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {
    // Code xử lý yêu thích tại đây
    @Autowired
    private LikeRepository likeRepository;

    @GetMapping("/")
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

}