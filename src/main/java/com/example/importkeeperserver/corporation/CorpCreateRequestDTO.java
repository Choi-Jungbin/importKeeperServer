package com.example.importkeeperserver.corporation;

import lombok.Getter;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Getter
public class CorpCreateRequestDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    private Country country;
    private Map<String, reviewDTO> reviews;

    @Getter
    public static class reviewDTO{
        @NotEmpty
        private String id;
        private int rating;
        private String review;

        public reviewDTO(String id, int rating, String review){
            this.id = id;
            this.rating = rating;
            this.review = review;
        }
    }
}
