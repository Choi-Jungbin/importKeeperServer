package com.example.importkeeperserver.store;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StoreResponseDTO {
    List<StoreDTO> stores;

    public StoreResponseDTO(List<Store> stores){
        this.stores = stores.stream()
                .map(StoreDTO::new)
                .collect(Collectors.toList());
    }
}
