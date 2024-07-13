package com.example.importkeeperserver.corporation;

import com.example.importkeeperserver.corporation.review.Review;
import com.example.importkeeperserver.corporation.review.ReviewDTO;
import com.example.importkeeperserver.corporation.review.ReviewJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CorporationService {
    private final CorporationJPARepository corporationJPARepository;
    private final ReviewJPARepository reviewJPARepository;

    @Transactional
    public void creatCorporation(CorpCreateRequestDTO requestDTO){
        Corporation corporation = Corporation.builder()
                .id(requestDTO.getId())
                .name(requestDTO.getName())
                .country(requestDTO.getCountry())
                .build();
        corporationJPARepository.save(corporation);

        Map<String, ReviewDTO> reviews = requestDTO.getReviews();

        for(String reviewId : reviews.keySet()){
            ReviewDTO reviewDTO = reviews.get(reviewId);
            Review review = Review.builder()
                    .id(reviewId)
                    .corporation(corporation)
                    .rating(reviewDTO.getRating())
                    .review(reviewDTO.getReview())
                    .build();
            reviewJPARepository.save(review);

            corporation.updateTotalRating(review.getRating());
        }
    }

    public Corporation findCorporation(Long id){
        Corporation corporation = corporationJPARepository.findById(id)
                .orElseThrow();
        return corporation;
    }
}
