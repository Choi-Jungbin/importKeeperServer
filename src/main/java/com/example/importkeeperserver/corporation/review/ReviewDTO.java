package com.example.importkeeperserver.corporation.review;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class ReviewDTO {
    @NotEmpty
    private String corporation;
    private int rating;
    private String content;

    public ReviewDTO(String corporation, int rating, String content){
        this.corporation = corporation;
        this.rating = rating;
        this.content = content;
    }
}
