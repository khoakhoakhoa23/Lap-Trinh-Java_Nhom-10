package uth.java.duan_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uth.java.duan_java.models.entities.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    // Tìm danh sách Review theo productId
    @Query("SELECT r FROM Review r WHERE r.product.productId = :productId")
    List<Review> findByProductId(@Param("productId") Long productId);

    // Tìm Review theo userId và productId
    @Query("SELECT r FROM Review r WHERE r.user.userId = :userId AND r.product.productId = :productId")
    Optional<Review> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
