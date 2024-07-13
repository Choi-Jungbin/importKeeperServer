package com.example.importkeeperserver.corporation;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "corporations")
@Getter
@NoArgsConstructor
public class Corporation {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Item item;

    @Column
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column
    private int reviewCount;

    @Column
    private int totalRating;

    @Column
    private int reportCount;

    @Builder
    public Corporation(String id, String name, Item item, Country country){
        this.id = id;
        this.name = name;
        this.item = item;
        this.country = country;
        this.reviewCount = 0;
        this.totalRating = 0;
        this.reportCount = 0;
    }

    public void updateTotalRating(int rating){
        this.reviewCount += 1;
        this.totalRating += rating;
    }

    void updateReport(){
        this.reportCount += 1;
    }
}
