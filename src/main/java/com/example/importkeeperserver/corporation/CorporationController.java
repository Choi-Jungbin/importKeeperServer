package com.example.importkeeperserver.corporation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corp")
public class CorporationController {

    @PostMapping
    public ResponseEntity<?> createCorporation(){
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<?> findCorporation(Long id){
        return ResponseEntity.ok(null);
    }
}
