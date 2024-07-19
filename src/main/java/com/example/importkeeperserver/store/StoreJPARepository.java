package com.example.importkeeperserver.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreJPARepository extends JpaRepository<Store, String> {
    List<Store> findByNameContainingIgnoreCase(String name);

    Page<Store> findAll(Pageable pageable);

    List<Store> findByCategory(Category category);

    List<Store> findByCompanyNameContainingIgnoreCase(String company);
}
