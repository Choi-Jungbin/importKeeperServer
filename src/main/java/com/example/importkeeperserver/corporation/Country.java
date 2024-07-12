package com.example.importkeeperserver.corporation;

import lombok.Getter;

@Getter
public enum Country {
    KR("한국(KOREA)"),
    US("미국(USA)"),
    JP("일본(JAPAN)"),
    CN("중국(CHINA");

    private final String country;

    Country(String c){this.country = c;}
}
