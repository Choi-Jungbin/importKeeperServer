package com.example.importkeeperserver.corporation.review;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class ReviewDTO {
    @NotEmpty
    private String id;
    private int rating;
    private String review;

    public ReviewDTO(String id, int rating, String review){
        this.id = id;
        this.rating = rating;
        this.review = review;
    }
}
