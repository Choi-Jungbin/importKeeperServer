package com.example.importkeeperserver.store.review;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReviewResponseDTO {
    List<ReviewDTO> reviews;

    public ReviewResponseDTO(List<Review> reviews){
        this.reviews = reviews.stream()
                .map(ReviewDTO::new)
                .collect(Collectors.toList());
    }

    @Getter
    private class ReviewDTO{
        private int rating;
        private String content;

        ReviewDTO(Review review){
            this.rating = review.getRating();
            this.content = review.getContent();
        }
    }
}
