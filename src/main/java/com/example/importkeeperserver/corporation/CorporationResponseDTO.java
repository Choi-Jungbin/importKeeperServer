package com.example.importkeeperserver.corporation;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CorporationResponseDTO {
    List<CorporationDTO> corporations;

    public CorporationResponseDTO(List<Corporation> corporations){
        this.corporations = corporations.stream()
                .map(CorporationDTO::new)
                .collect(Collectors.toList());
    }
}
