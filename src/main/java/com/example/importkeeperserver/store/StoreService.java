package com.example.importkeeperserver.store;

import com.example.importkeeperserver.core.error.NotFoundException;
import com.example.importkeeperserver.store.review.Review;
import com.example.importkeeperserver.store.review.ReviewDTO;
import com.example.importkeeperserver.store.review.ReviewJPARepository;
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
public class StoreService {
    private final StoreJPARepository storeJPARepository;
    private final ReviewJPARepository reviewJPARepository;

    @PostConstruct
    @Transactional
    public void storeInit(){
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
                    JSONObject jsonStore = (JSONObject) entry.getValue();
                    Store store = storeJPARepository.findById(storeId)
                            .orElseGet(() -> {
                                Store newStore = Store.builder()
                                        .id(storeId)
                                        .name(jsonStore.get("store_name").toString())
                                        .category(Category.valueOf(category))
                                        .vatNum(jsonStore.get("vat_num").toString())
                                        .address(jsonStore.get("address").toString())
                                        .companyName(jsonStore.get("company_name").toString())
                                        .build();
                                return storeJPARepository.save(newStore);
                            });

                    JSONArray jsonArray = (JSONArray) jsonStore.get("review");
                    for(Object object : jsonArray){
                        JSONObject obj = (JSONObject) object;
                        Long scoreLong = (Long) obj.get("score");
                        int score = (int) (scoreLong / 20);
                        String content = (String) ((JSONObject) object).get("content");

                        store.updateRating(score);
                        Review review = Review.builder()
                                .store(store)
                                .rating(score)
                                .content(content)
                                .build();

                        reviewJPARepository.save(review);
                    }
                    storeJPARepository.save(store);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void creatStore(StoreCreateRequestDTO requestDTO){
        Store store = Store.builder()
                .id(requestDTO.getId())
                .name(requestDTO.getName())
                .build();
        storeJPARepository.save(store);

        Map<String, ReviewDTO> reviews = requestDTO.getReviews();

        if (reviews == null || reviews.isEmpty()) {
            // reviews가 null이거나 비어있으면 메서드를 종료
            return;
        }

        for(String reviewId : reviews.keySet()){
            ReviewDTO reviewDTO = reviews.get(reviewId);
            Review review = Review.builder()
                    .store(store)
                    .rating(reviewDTO.getRating())
                    .content(reviewDTO.getContent())
                    .build();
            reviewJPARepository.save(review);

            store.updateRating(review.getRating());
        }
    }

    @Transactional
    public StoreDTO findStore(String id){
        Store store = storeJPARepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 스토어가 없습니다."));

        return new StoreDTO(store);
    }

    @Transactional
    public StoreResponseDTO findStoreByName(String name){
        List<Store> stores = storeJPARepository.findByNameContaining(name);

        return new StoreResponseDTO(stores);
    }

    @Transactional
    public StoreResponseDTO findAllStores(Pageable pageable){
        Page<Store> corporations = storeJPARepository.findAll(pageable);

        return new StoreResponseDTO(corporations.getContent());
    }

    @Transactional
    public void createReport(ReviewDTO report){
        Store store = storeJPARepository.findById(report.getStore())
                .orElseThrow(() -> new NotFoundException("해당 스토어가 없습니다."));
        store.updateReport(report.getRating());
        storeJPARepository.save(store);
        Review review = Review.builder()
                .store(store)
                .rating(report.getRating())
                .content(report.getContent())
                .build();
        reviewJPARepository.save(review);
    }

    @Transactional
    public StoreResponseDTO findStoreByCategory(Category category){
        List<Store> stores = storeJPARepository.findByCategory(category);

        return new StoreResponseDTO(stores);
    }

    @Transactional
    public StoreResponseDTO findStoreByCompany(String company){
        List<Store> stores = storeJPARepository.findByCompanyNameContainingIgnoreCase(company);

        return new StoreResponseDTO(stores);
    }
}
