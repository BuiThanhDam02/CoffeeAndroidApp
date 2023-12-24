package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Admin;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Các phương thức truy vấn khác nếu cần
    List<Comment> findByCoffeeId(Long coffeeId);
    @Modifying
    @Query("UPDATE Comment c SET c.status = -1 WHERE c.coffee.id = :id")
    void deleteByCoffeeId(Long id);
}