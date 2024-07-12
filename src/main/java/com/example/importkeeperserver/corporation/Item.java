package com.example.importkeeperserver.corporation;

import lombok.Getter;

@Getter
public enum Item {
    ELECT("전자제품"),
    CLOTHES("의류"),
    LIFE("생활용품"),
    FOOD("건강식품"),
    COSMETICS("화장품");

    private final String item;

    Item(String i){this.item = i;}
}
