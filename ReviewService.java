package uth.java.duan_java.services;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.java.duan_java.models.dtos.ReviewDTO;
import uth.java.duan_java.models.entities.Review;
import uth.java.duan_java.repositories.ReviewRepo;

import java.util.List;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ReviewDTO> getReviews(){
        List<Review> reviews = reviewRepo.findAll();
        if (reviews.isEmpty()){
            throw new RuntimeException("No reviews found");
        }
        return modelMapper.map(reviews, new TypeToken<List<ReviewDTO>>(){}.getType());
    }
    @Override
    public  ReviewDTO getReviewById(long id){
        Review review = reviewRepo.findById((long) id)
                .orElseThrow(()-> new RuntimeException("Review not found"));
        return modelMapper.map(review, ReviewDTO.class);
    }
    @Override
    public ReviewDTO createReview(ReviewDTO review){
        Review newReview = modelMapper.map(review, Review.class);
        newReview = reviewRepo.save(newReview);
        return modelMapper.map(newReview, ReviewDTO.class);

    }
    @Override
    public ReviewDTO updateReview(ReviewDTO review, long id){
        Review existingReview = reviewRepo.findById((long) id)
                .orElseThrow(()-> new RuntimeException("Review not found"));
        existingReview.setReviewId(existingReview.getReviewId());
        Review newReview = modelMapper.map(review, Review.class);
        newReview = reviewRepo.save(newReview);
        return modelMapper.map(newReview, ReviewDTO.class);

    }

    public boolean deleteReview(long id){
        Review existingReview = reviewRepo.findById((long) id)
                .orElseThrow(()-> new RuntimeException("Review not found"));
        reviewRepo.delete(existingReview);
        return true;
    }
}
