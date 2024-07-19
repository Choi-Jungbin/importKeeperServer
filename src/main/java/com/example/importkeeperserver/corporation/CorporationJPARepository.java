package com.example.importkeeperserver.corporation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorporationJPARepository extends JpaRepository<Corporation, String> {
    List<Corporation> findByNameContaining(String name);

    Page<Corporation> findAll(Pageable pageable);

    List<Corporation> findByCategory(Category category);
}
