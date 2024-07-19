package com.example.importkeeperserver.corporation;

import lombok.Getter;

@Getter
public class CorporationDTO {
    private String id;
    private String name;
    private Category category;
    private String vatNum;
    private String address;
    private String companyName;
    private String rating;

    public CorporationDTO(Corporation corporation){
        this.id = corporation.getId();
        this.name = corporation.getName();
        this.category = corporation.getCategory();
        this.vatNum = corporation.getVatNum();
        this.address = corporation.getAddress();
        this.companyName = corporation.getCompanyName();
        this.rating = String.format("%.2f", corporation.getRating());
    }
}
