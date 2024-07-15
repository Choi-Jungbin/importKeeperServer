package com.example.importkeeperserver.regulation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_keywords")
@Getter
@NoArgsConstructor
public class ItemKeyword {
    @Id
    private String keywordNum;

    @Column(nullable = false)
    private String item;

    @Builder
    public ItemKeyword(String keywordNum, String item){
        this.keywordNum = keywordNum;
        this.item = item;
    }
}
