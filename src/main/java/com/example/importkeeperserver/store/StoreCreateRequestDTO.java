package com.example.importkeeperserver.store;

import com.example.importkeeperserver.store.review.ReviewDTO;
import lombok.Getter;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Getter
public class StoreCreateRequestDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    private Map<String, ReviewDTO> reviews;

    public StoreCreateRequestDTO(String id, String name,
                                 Map<String, ReviewDTO> reviews){
        this.id = id;
        this.name = name;
        this.reviews = reviews;
    }
}
