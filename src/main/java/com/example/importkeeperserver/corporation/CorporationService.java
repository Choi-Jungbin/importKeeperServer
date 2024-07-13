package com.example.importkeeperserver.corporation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorporationService {
    private final CorporationJPARepository corporationJPARepository;

    public void creatCorporation(){}

    public Corporation findCorporation(Long id){
        Corporation corporation = corporationJPARepository.findById(id)
                .orElseThrow();
        return corporation;
    }
}
