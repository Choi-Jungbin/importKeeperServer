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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Item item;

    @Column
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column
    private int reportCount;

    @Builder
    public Corporation(String name, Item item, Country country){
        this.name = name;
        this.item = item;
        this.country = country;
        this.reportCount = 0;
    }

    void updateReport(){
        this.reportCount += 1;
    }
}
