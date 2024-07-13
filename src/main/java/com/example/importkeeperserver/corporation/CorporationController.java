package com.example.importkeeperserver.corporation;

import com.example.importkeeperserver.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corp")
public class CorporationController {
    private final CorporationService corporationService;

    @PostMapping
    public ResponseEntity<ApiUtils.ApiResult> createCorporation(@RequestBody CorpCreateRequestDTO requestDTO){
        corporationService.creatCorporation(requestDTO);
        return ResponseEntity.ok(ApiUtils.success(null, "success"));
    }

    @GetMapping
    public ResponseEntity<?> findCorporation(Long id){
        return ResponseEntity.ok(null);
    }
}
