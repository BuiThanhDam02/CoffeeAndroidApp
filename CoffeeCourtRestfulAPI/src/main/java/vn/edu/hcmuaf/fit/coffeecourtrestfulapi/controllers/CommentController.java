package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.CommentRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Comment;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CommentRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID: " + id));
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setName(updatedComment.getName());
                    comment.setContent(updatedComment.getContent());
                    comment.setStar(updatedComment.getStar());
                    comment.setStatus(updatedComment.getStatus());
                    // Cập nhật các thuộc tính khác của comment
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }
    @PostMapping("/add")
    public ResponseEntity<String> saveComment(@RequestBody CommentRequest commentRequest) {
        System.out.println(commentRequest);
        try {
            Comment comment = new Comment();
            Coffee coffee = coffeeRepository.findOneById(commentRequest.getCoffee_id());
            comment.setUser(commentRequest.getUser());
            comment.setCoffee(coffee);
            comment.setContent(commentRequest.getContent());
            comment.setName(commentRequest.getUser().getName());
            comment.setStar(commentRequest.getStar());
            comment.setStatus(0);
            commentRepository.save(comment);
            return ResponseEntity.ok("Comment added successfully");
        } catch (Exception e ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding comment: " + e.getMessage());

        }
    }
    @GetMapping("/getAllByCoffeeID/{id}")
    public List<Comment> getCommentsByCoffeeId(@PathVariable Long id) {
        return commentRepository.findByCoffeeId(id);
    }
}