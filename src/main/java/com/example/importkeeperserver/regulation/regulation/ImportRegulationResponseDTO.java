package com.example.importkeeperserver.regulation.regulation;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ImportRegulationResponseDTO {
    private List<ImportRegulationDTO> importRegulations;

    public ImportRegulationResponseDTO(List<ImportRegulation> importRegulations){
        this.importRegulations = importRegulations.stream()
                .map(ImportRegulationDTO::new)
                .collect(Collectors.toList());
    }

    @Getter
    private class ImportRegulationDTO{
        private String country;
        private String item;

        private ImportRegulationDTO(ImportRegulation importRegulation){
            this.country = importRegulation.getCountry();
            this.item = importRegulation.getItem();
        }
    }
}
