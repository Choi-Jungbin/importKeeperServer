package com.example.importkeeperserver.regulation.item;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ItemKeywordResponseDTO {
    private List<ItemKeywordDTO> itemKeywords;

    public ItemKeywordResponseDTO(List<ItemKeyword> itemKeywords){
        this.itemKeywords = itemKeywords.stream()
                .map(ItemKeywordDTO::new)
                .collect(Collectors.toList());
    }

    @Getter
    private static class ItemKeywordDTO{
        private String keywordNum;
        private String item;

        private ItemKeywordDTO(ItemKeyword itemKeyword){
            this.keywordNum = itemKeyword.getKeywordNum();
            this.item = itemKeyword.getItem();
        }
    }
}
