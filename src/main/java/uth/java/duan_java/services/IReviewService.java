package uth.java.duan_java.services;

import uth.java.duan_java.models.dtos.ReviewDTO;
import uth.java.duan_java.models.entities.Review;

import java.util.List;

public interface IReviewService {
    List<ReviewDTO> getReviews();

    ReviewDTO getReviewById(long id);

    ReviewDTO createReview(ReviewDTO review);

    ReviewDTO updateReview(ReviewDTO review, long id);

    boolean deleteReview(long id);



}
