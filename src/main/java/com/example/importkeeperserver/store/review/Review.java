package com.example.importkeeperserver.store.review;

import com.example.importkeeperserver.store.Store;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "corp_id")
    private Store store;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "longtext")
    private String content;

    @Builder
    public Review(Store store, int rating, String content){
        this.store = store;
        this.rating = rating;
        this.content = content;
    }
}
