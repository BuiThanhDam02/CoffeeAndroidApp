package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Comment;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CommentRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.LikeRepository;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    CoffeeRepository coffeeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikeRepository likeRepository;

    @GetMapping("/all")
    public List<Coffee> getAll() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/search")
    public List<Coffee> searchByName(@RequestParam String name) {
        return coffeeRepository.findByNameContaining(name);
    }

    @GetMapping("/bySupplierId")
    public List<Coffee> getBySupplierId(@RequestParam("supplierId") int supplierId){
        return coffeeRepository.findBySupplierId(supplierId);
    }
    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsByCoffeeId(@PathVariable Long id) {
        return commentRepository.findByCoffeeId(id);
    }

    @GetMapping("/{id}/like/{userid}")
    public List<Comment> likeByCoffeeId(@PathVariable Long id) {
        return likeRepository.findByCoffeeId(id);
    }
//    @GetMapping("/{id}/unlike")
//    public List<Comment> unlikeByCoffeeId(@PathVariable Long id) {
//        return likeRepository.findByCoffeeId(id);
//    }

}
