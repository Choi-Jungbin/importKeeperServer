package com.example.importkeeperserver.store.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJPARepository extends JpaRepository<Review, String> {
    Page<Review> findByStoreId(String store, Pageable pageable);
}
