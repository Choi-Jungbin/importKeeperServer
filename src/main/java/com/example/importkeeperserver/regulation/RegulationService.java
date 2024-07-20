package com.example.importkeeperserver.regulation;

import com.example.importkeeperserver.regulation.item.ItemKeyword;
import com.example.importkeeperserver.regulation.item.ItemKeywordJPARepository;
import com.example.importkeeperserver.regulation.item.ItemKeywordResponseDTO;
import com.example.importkeeperserver.regulation.regulation.ImportRegulation;
import com.example.importkeeperserver.regulation.regulation.ImportRegulationJPARepository;
import com.example.importkeeperserver.regulation.regulation.ImportRegulationResponseDTO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RegulationService {

    private final ItemKeywordJPARepository itemKeywordJPARepository;
    private final ImportRegulationJPARepository importRegulationJPARepository;

    // 서버 실행 시 바로 진행
    @PostConstruct
    @Transactional
    public void regulationInit(){
        try {
            loadItemKeywordCSV();
            loadImportRegulation();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public ItemKeywordResponseDTO getAutoCompleteKeyword(String prefix, Pageable pageable){
        Page<ItemKeyword> itemKeywords = itemKeywordJPARepository.findByAutoCompleteKeywordStartingWith(prefix, pageable);

        return new ItemKeywordResponseDTO(itemKeywords.getContent());
    }

    @Transactional
    public ItemKeywordResponseDTO findItemKeyword(Pageable pageable){
        Page<ItemKeyword> itemKeywords = itemKeywordJPARepository.findAll(pageable);

        return new ItemKeywordResponseDTO(itemKeywords.getContent());
    }

    @Transactional
    public ImportRegulationResponseDTO findAllRegulations(Pageable pageable){
        Page<ImportRegulation> importRegulations = importRegulationJPARepository.findAll(pageable);

        return new ImportRegulationResponseDTO(importRegulations.getContent());
    }

    @Transactional
    public ImportRegulationResponseDTO findRegulationByCountry(String country, Pageable pageable){
        Page<ImportRegulation> importRegulations = importRegulationJPARepository.findByCountryContainingIgnoreCase(country, pageable);

        return new ImportRegulationResponseDTO(importRegulations.getContent());
    }

    @Transactional
    public ImportRegulationResponseDTO findRegulationByItem(String item, Pageable pageable){
        Page<ImportRegulation> importRegulations = importRegulationJPARepository.findByItemContainingIgnoreCase(item, pageable);

        return new ImportRegulationResponseDTO(importRegulations.getContent());
    }

    @Transactional
    public void loadItemKeywordCSV() throws IOException, CsvValidationException {
        ClassPathResource resource = new ClassPathResource("import_regulation/Strategic_Materials_Item_Keyword_2019.csv");
        InputStreamReader isr = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReader(isr);
        // 한 줄 넘기기
        csvReader.readNext();

        String[] nextRecord;
        while((nextRecord = csvReader.readNext()) != null){
            // 규제국
            String keywordNum = nextRecord[0];
            String item = nextRecord[1];
            String autoCompleteKeyword = nextRecord[7];

            ItemKeyword itemKeyword = itemKeywordJPARepository.findById(keywordNum)
                    .orElseGet(() -> {
                        ItemKeyword newItemKeyword = ItemKeyword.builder()
                                .keywordNum(keywordNum)
                                .item(item)
                                .autoCompleteKeyword(autoCompleteKeyword)
                                .build();
                        return itemKeywordJPARepository.save(newItemKeyword);
                    });
        }
        csvReader.close();
    }

    @Transactional
    public void loadImportRegulation() throws IOException {
        ClassPathResource resource = new ClassPathResource("import_regulation/Import_Regulations_DB_2024-07-15.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(resource.getInputStream());

        // 첫번째 시트 불러오기
        Sheet sheet = wb.getSheetAt(0);
        for(Row row : sheet){
            // 첫번째 행 넘기기
            if(row.getRowNum() == 0){
                continue;
            }

            String manageNum = row.getCell(0).getStringCellValue();
            String country = row.getCell(1).getStringCellValue();
            String item = row.getCell(2).getStringCellValue();
            ImportRegulation importRegulation = importRegulationJPARepository.findById(manageNum)
                    .orElseGet(() -> {
                        ImportRegulation newImportRegulation = ImportRegulation.builder()
                                .manageNum(manageNum)
                                .country(country)
                                .item(item)
                                .build();
                        return importRegulationJPARepository.save(newImportRegulation);
                    });
        }
    }
}
