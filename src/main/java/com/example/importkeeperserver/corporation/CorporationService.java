package com.example.importkeeperserver.corporation;

import com.example.importkeeperserver.core.error.NotFoundException;
import com.example.importkeeperserver.corporation.review.Review;
import com.example.importkeeperserver.corporation.review.ReviewDTO;
import com.example.importkeeperserver.corporation.review.ReviewJPARepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CorporationService {
    private final CorporationJPARepository corporationJPARepository;
    private final ReviewJPARepository reviewJPARepository;

    @PostConstruct
    @Transactional
    public void corporationInit(){
        try {
            String reviewPath = "src/main/resources/aliexpress_review/";
            File dir = new File(reviewPath);
            String[] filenames = dir.list();

            JSONParser parser = new JSONParser();
            for (String filename : filenames) {
                String category = filename.split("\\.")[0];
                Reader reader = new FileReader(reviewPath + filename);
                JSONObject jsonObject = (JSONObject) parser.parse(reader);

                Iterator stores = ((JSONObject) jsonObject.get(category)).entrySet().iterator();
                while(stores.hasNext()){
                    Map.Entry entry = (Map.Entry) stores.next();
                    String storeId = (String) entry.getKey();
                    JSONObject store = (JSONObject) entry.getValue();
                    Corporation corporation = corporationJPARepository.findById(storeId)
                            .orElseGet(() -> {
                                Corporation newCorporation = Corporation.builder()
                                        .id(storeId)
                                        .name(store.get("store_name").toString())
                                        .category(Category.valueOf(category))
                                        .vatNum(store.get("vat_num").toString())
                                        .address(store.get("address").toString())
                                        .companyName(store.get("company_name").toString())
                                        .build();
                                return corporationJPARepository.save(newCorporation);
                            });

                    JSONArray jsonArray = (JSONArray) store.get("review");
                    for(Object object : jsonArray){
                        JSONObject obj = (JSONObject) object;
                        Long scoreLong = (Long) obj.get("score");
                        int score = (int) (scoreLong / 20);
                        String content = (String) ((JSONObject) object).get("content");

                        corporation.updateTotalRating(score);
                        Review review = Review.builder()
                                .corporation(corporation)
                                .rating(score)
                                .content(content)
                                .build();

                        reviewJPARepository.save(review);
                    }
                    corporationJPARepository.save(corporation);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void creatCorporation(CorpCreateRequestDTO requestDTO){
        Corporation corporation = Corporation.builder()
                .id(requestDTO.getId())
                .name(requestDTO.getName())
                .build();
        corporationJPARepository.save(corporation);

        Map<String, ReviewDTO> reviews = requestDTO.getReviews();

        if (reviews == null || reviews.isEmpty()) {
            // reviews가 null이거나 비어있으면 메서드를 종료
            return;
        }

        for(String reviewId : reviews.keySet()){
            ReviewDTO reviewDTO = reviews.get(reviewId);
            Review review = Review.builder()
                    .corporation(corporation)
                    .rating(reviewDTO.getRating())
                    .content(reviewDTO.getContent())
                    .build();
            reviewJPARepository.save(review);

            corporation.updateTotalRating(review.getRating());
        }
    }

    @Transactional
    public CorporationDTO findCorporation(String id){
        Corporation corporation = corporationJPARepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 기업이 없습니다."));

        return new CorporationDTO(corporation);
    }

    @Transactional
    public CorporationResponseDTO findMatchCorporations(String name){
        List<Corporation> corporations = corporationJPARepository.findByNameContaining(name);

        return new CorporationResponseDTO(corporations);
    }

    @Transactional
    public CorporationResponseDTO findALlCorporations(Pageable pageable){
        Page<Corporation> corporations = corporationJPARepository.findAll(pageable);

        return new CorporationResponseDTO(corporations.getContent());
    }

}
