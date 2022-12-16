package com.autoservice.service;

import com.autoservice.entity.Review;
import com.autoservice.entity.User;
import com.autoservice.repository.ReviewRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private static final Logger log = LogManager.getLogger(ReviewService.class);

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void save(Review review, User user) {
        review.setUser(user);
        reviewRepository.save(review);
        log.info("Review created");
    }

    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public BigDecimal countAverageRating() {
        BigDecimal average = new BigDecimal("0");
        List<Review> allReviews = reviewRepository.findAll();
        if (allReviews.size() != 0) {
            for (Review review : allReviews) {
                average = average.add(BigDecimal.valueOf(review.getRating()));
            }
            average = average.divide(BigDecimal.valueOf(allReviews.size()), RoundingMode.DOWN);

        }
        return average;
    }
}
