package com.example.importkeeperserver.store;

import com.example.importkeeperserver.core.utils.ApiUtils;
import com.example.importkeeperserver.store.review.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<ApiUtils.ApiResult> createCorporation(@RequestBody StoreCreateRequestDTO requestDTO){
        storeService.creatCorporation(requestDTO);

        return ResponseEntity.ok(ApiUtils.success(null, "success"));
    }

    @GetMapping("/id")
    public ResponseEntity<ApiUtils.ApiResult> findCorporation(@RequestParam String id){
        StoreDTO responseDTO = storeService.findCorporation(id);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping("/name")
    public ResponseEntity<ApiUtils.ApiResult> findMatchCorporations(@RequestParam String name){
        StoreResponseDTO responseDTO = storeService.findMatchCorporations(name);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult> findAllCorporations(@PageableDefault(size = 10)Pageable pageable){
        StoreResponseDTO responseDTO = storeService.findALlCorporations(pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @PostMapping("/report")
    public ResponseEntity<ApiUtils.ApiResult> createReport(@RequestBody ReviewDTO requestDTO){
        storeService.createReport(requestDTO);

        return ResponseEntity.ok(ApiUtils.success(null, "success"));
    }

    @GetMapping("/category")
    public ResponseEntity<ApiUtils.ApiResult> findCorporationByCategory(@RequestParam Category category){
        StoreResponseDTO responseDTO = storeService.findCorporationByCategory(category);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }
}
