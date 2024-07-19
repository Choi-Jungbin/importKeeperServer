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
    public ResponseEntity<ApiUtils.ApiResult> createStore(@RequestBody StoreCreateRequestDTO requestDTO){
        storeService.creatStore(requestDTO);

        return ResponseEntity.ok(ApiUtils.success(null, "success"));
    }

    @GetMapping("/id")
    public ResponseEntity<ApiUtils.ApiResult> findStore(@RequestParam String id){
        StoreDTO responseDTO = storeService.findStore(id);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping("/name")
    public ResponseEntity<ApiUtils.ApiResult> findStoreByName(@RequestParam String name, @PageableDefault(size = 10)Pageable pageable){
        StoreResponseDTO responseDTO = storeService.findStoreByName(name, pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult> findAllStores(@PageableDefault(size = 10)Pageable pageable){
        StoreResponseDTO responseDTO = storeService.findAllStores(pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @PostMapping("/report")
    public ResponseEntity<ApiUtils.ApiResult> createReport(@RequestBody ReviewDTO requestDTO){
        storeService.createReport(requestDTO);

        return ResponseEntity.ok(ApiUtils.success(null, "success"));
    }

    @GetMapping("/category")
    public ResponseEntity<ApiUtils.ApiResult> findStoreByCategory(@RequestParam Category category, @PageableDefault(size = 10)Pageable pageable){
        StoreResponseDTO responseDTO = storeService.findStoreByCategory(category, pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping("/company")
    public ResponseEntity<ApiUtils.ApiResult> findStoreByCompany(@RequestParam String company, @PageableDefault(size = 10)Pageable pageable){
        StoreResponseDTO responseDTO = storeService.findStoreByCompany(company, pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }
}
