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
    private int totalRating;

    @Column
    private int reportCount;

    @Builder
    public Corporation(String id, String name, Category category, String vatNum, String address, String companyName){
        this.id = id;
        this.name = name;
        this.category = category;
        this.vatNum = vatNum;
        this.address = address;
        this.companyName = companyName;
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
