package com.example.importkeeperserver.regulation.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemKeywordJPARepository extends JpaRepository<ItemKeyword, String> {
    Page<ItemKeyword> findByAutoCompleteKeywordStartingWith(String prefix, Pageable pageable);

    Page<ItemKeyword> findAll(Pageable pageable);
}
