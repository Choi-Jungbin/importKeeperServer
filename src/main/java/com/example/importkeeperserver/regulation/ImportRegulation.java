package com.example.importkeeperserver.regulation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "import_regulations")
@Getter
@NoArgsConstructor
public class ImportRegulation {

    @Id
    private String manageNum;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String item;

    @Builder
    public ImportRegulation(String manageNum, String country, String item){
        this.manageNum = manageNum;
        this.country = country;
        this.item = item;
    }
}
