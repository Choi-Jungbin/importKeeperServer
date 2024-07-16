package com.example.importkeeperserver.regulation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemKeywordJPARepository extends JpaRepository<ItemKeyword, String> {
    List<ItemKeyword> findByAutoCompleteKeywordStartingWith(String prefix);

    Page<ItemKeyword> findAll(Pageable pageable);
}
