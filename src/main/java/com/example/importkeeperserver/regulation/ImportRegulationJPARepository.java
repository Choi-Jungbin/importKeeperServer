package com.example.importkeeperserver.regulation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportRegulationJPARepository extends JpaRepository<ImportRegulation, String> {
    List<ImportRegulation> findByCountryContaining(String country);
    List<ImportRegulation> findByItemContaining(String item);
}
