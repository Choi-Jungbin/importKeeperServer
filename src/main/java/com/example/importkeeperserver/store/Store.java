package com.example.importkeeperserver.store;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stores")
@Getter
@NoArgsConstructor
public class Store {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private String vatNum;

    @Column
    private String address;

    @Column
    private String companyName;

    @Column
    private int reviewCount;

    @Column
    private float rating;

    @Column
    private int report;

    @Builder
    public Store(String id, String name, Category category, String vatNum, String address, String companyName){
        this.id = id;
        this.name = name;
        this.category = category;
        this.vatNum = vatNum;
        this.address = address;
        this.companyName = companyName;
        this.reviewCount = 0;
        this.rating = 0;
        this.report = 0;
    }

    public void updateRating(int rating){
        this.rating = (this.rating * reviewCount + rating) / (reviewCount + 1);
        this.reviewCount += 1;
    }

    void updateReport(int rating){
        this.report += 1;
        updateRating(rating);
    }
}
