package com.example.importkeeperserver.store;

import lombok.Getter;

@Getter
public class StoreDTO {
    private String id;
    private String name;
    private String imagePath;
    private String imageBase64;
    private Category category;
    private String vatNum;
    private String address;
    private String companyName;
    private String rating;
    private int report;

    public StoreDTO(Store store){
        this.id = store.getId();
        this.name = store.getName();
        this.imagePath = store.getImagePath();
        this.category = store.getCategory();
        this.vatNum = store.getVatNum();
        this.address = store.getAddress();
        this.companyName = store.getCompanyName();
        this.rating = String.format("%.2f", store.getRating());
        this.report = store.getReport();
    }
}
