package com.example.importkeeperserver.corporation;

import com.example.importkeeperserver.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/id")
    public ResponseEntity<ApiUtils.ApiResult> findCorporation(@RequestParam String id){
        CorporationDTO responseDTO = corporationService.findCorporation(id);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping("/name")
    public ResponseEntity<ApiUtils.ApiResult> findMatchCorporations(@RequestParam String name){
        CorporationResponseDTO responseDTO = corporationService.findMatchCorporations(name);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult> findAllCorporations(@PageableDefault(size = 10)Pageable pageable){
        CorporationResponseDTO responseDTO = corporationService.findALlCorporations(pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }
}
