package uth.java.duan_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uth.java.duan_java.models.entities.Review;
import uth.java.duan_java.repositories.ReviewRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepo reviewRepo;

    // 1. Lấy danh sách tất cả đánh giá cho sản phẩm
    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long productId) {
        List<Review> reviews = reviewRepo.findByProductId(productId);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // 2. Thêm đánh giá mới
    @PostMapping()
    public ResponseEntity<String> addReview(@RequestBody Review review) {
        reviewRepo.save(review);
        return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
    }

    // 3. Cập nhật đánh giá
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        Optional<Review> existingReview = reviewRepo.findById((long) Math.toIntExact(id));
        if (existingReview.isEmpty()) {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }

        Review review = existingReview.get();
        review.setRating(updatedReview.getRating()); // Cập nhật đánh giá
        review.setComment(updatedReview.getComment()); // Cập nhật bình luận
        reviewRepo.save(review);
        return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    }

    // 4. Xóa đánh giá
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeReview(@PathVariable Long id) {
        Optional<Review> existingReview = reviewRepo.findById((long) Math.toIntExact(id));
        if (existingReview.isEmpty()) {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }

        reviewRepo.delete(existingReview.get());
        return new ResponseEntity<>("Review removed successfully", HttpStatus.OK);
    }
}
