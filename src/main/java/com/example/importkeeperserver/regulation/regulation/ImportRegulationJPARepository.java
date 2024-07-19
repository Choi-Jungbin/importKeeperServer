package com.example.importkeeperserver.regulation.regulation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportRegulationJPARepository extends JpaRepository<ImportRegulation, String> {
    Page<ImportRegulation> findByCountryContainingIgnoreCase(String country, Pageable pageable);
    Page<ImportRegulation> findByItemContainingIgnoreCase(String item, Pageable pageable);
}
