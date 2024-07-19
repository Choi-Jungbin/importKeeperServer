package com.example.importkeeperserver.regulation.regulation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportRegulationJPARepository extends JpaRepository<ImportRegulation, String> {
    List<ImportRegulation> findByCountryContainingIgnoreCase(String country);
    List<ImportRegulation> findByItemContainingIgnoreCase(String item);
}
