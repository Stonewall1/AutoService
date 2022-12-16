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
import java.util.Optional;
import java.util.stream.Collectors;

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
        return BigDecimal.valueOf(findAll()
                .stream()
                .collect(Collectors.averagingDouble(Review::getRating)));
    }
}
