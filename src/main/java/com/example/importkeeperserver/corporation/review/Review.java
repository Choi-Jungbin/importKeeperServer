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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "corp_id")
    private Corporation corporation;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "longtext")
    private String content;

    @Builder
    public Review(String id, Corporation corporation, int rating, String content){
        this.id = id;
        this.corporation = corporation;
        this.rating = rating;
        this.content = content;
    }
}
