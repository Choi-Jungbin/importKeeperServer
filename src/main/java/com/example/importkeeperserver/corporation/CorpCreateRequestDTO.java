package com.example.importkeeperserver.corporation;

import com.example.importkeeperserver.corporation.review.ReviewDTO;
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
    private Map<String, ReviewDTO> reviews;

    public CorpCreateRequestDTO(String id, String name,
                                Country country,
                                Map<String, ReviewDTO> reviews){
        this.id = id;
        this.name = name;
        this.country = country;
        this.reviews = reviews;
    }
}
