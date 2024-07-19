package com.example.importkeeperserver.store.review;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class ReviewDTO {
    @NotEmpty
    private String store;
    private int rating;
    private String content;

    public ReviewDTO(String store, int rating, String content){
        this.store = store;
        this.rating = rating;
        this.content = content;
    }
}
