package com.example.importkeeperserver.regulation;

import com.example.importkeeperserver.core.utils.ApiUtils;
import com.example.importkeeperserver.regulation.item.ItemKeywordResponseDTO;
import com.example.importkeeperserver.regulation.regulation.ImportRegulationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regulation")
public class RegulationController {
    public final RegulationService regulationService;

    @GetMapping("/itemKeyword/auto")
    public ResponseEntity<ApiUtils.ApiResult> autoComplete(@RequestParam String prefix, @PageableDefault(size = 10)Pageable pageable){
        ItemKeywordResponseDTO responseDTO = regulationService.getAutoCompleteKeyword(prefix, pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping("/itemKeyword")
    public ResponseEntity<ApiUtils.ApiResult> findItemKeyword(@PageableDefault(size = 10)Pageable pageable){
        ItemKeywordResponseDTO responseDTO = regulationService.findItemKeyword(pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult> findAllRegulations(@PageableDefault(size = 10)Pageable pageable){
        ImportRegulationResponseDTO responseDTO = regulationService.findAllRegulations(pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping("/country")
    public ResponseEntity<ApiUtils.ApiResult> findRegulationByCountry(@RequestParam String country, @PageableDefault(size = 10)Pageable pageable){
        ImportRegulationResponseDTO responseDTO = regulationService.findRegulationByCountry(country, pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }

    @GetMapping("/item")
    public ResponseEntity<ApiUtils.ApiResult> findRegulationByItem(@RequestParam String item, @PageableDefault(size = 10)Pageable pageable){
        ImportRegulationResponseDTO responseDTO = regulationService.findRegulationByItem(item, pageable);

        return ResponseEntity.ok(ApiUtils.success(responseDTO, "success"));
    }
}
