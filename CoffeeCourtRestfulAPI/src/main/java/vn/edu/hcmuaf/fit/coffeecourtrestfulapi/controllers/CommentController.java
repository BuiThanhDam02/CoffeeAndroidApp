package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Comment;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CommentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

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

}