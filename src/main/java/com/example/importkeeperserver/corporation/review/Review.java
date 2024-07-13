package com.example.importkeeperserver.corporation.review;

import com.example.importkeeperserver.corporation.Corporation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor
public class Review {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "corp_id")
    private Corporation corporation;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "longtext")
    private String review;

    @Builder
    public Review(String id, Corporation corporation, int rating, String review){
        this.id = id;
        this.corporation = corporation;
        this.rating = rating;
        this.review = review;
    }
}
