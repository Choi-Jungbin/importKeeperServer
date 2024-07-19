package com.example.importkeeperserver.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreJPARepository extends JpaRepository<Store, String> {
    Page<Store> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Store> findAll(Pageable pageable);

    Page<Store> findByCategory(Category category, Pageable pageable);

    Page<Store> findByCompanyNameContainingIgnoreCase(String company, Pageable pageable);
}
