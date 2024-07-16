package com.example.importkeeperserver.regulation;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegulationService {

    private final ItemKeywordJPARepository itemKeywordJPARepository;
    private final ImportRegulationJPARepository importRegulationJPARepository;

    // 서버 실행 시 바로 진행
    @PostConstruct
    public void itemKeywordInit(){
        try {
            loadItemKeywordCSV();
            loadImportRegulation();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public ItemKeywordResponseDTO getAutoCompleteKeyword(String prefix){
        List<ItemKeyword> itemKeywords = itemKeywordJPARepository.findByAutoCompleteKeywordStartingWith(prefix);

        return new ItemKeywordResponseDTO(itemKeywords);
    }

    @Transactional
    public ItemKeywordResponseDTO findItemKeyword(Pageable pageable){
        Page<ItemKeyword> itemKeywords = itemKeywordJPARepository.findAll(pageable);

        return new ItemKeywordResponseDTO(itemKeywords.getContent());
    }

    @Transactional
    public ImportRegulationResponseDTO findRegulationByCountry(String country){
        List<ImportRegulation> importRegulations = importRegulationJPARepository.findByCountryContaining(country);

        return new ImportRegulationResponseDTO(importRegulations);
    }

    @Transactional
    public ImportRegulationResponseDTO findRegulationByItem(String item){
        List<ImportRegulation> importRegulations = importRegulationJPARepository.findByItemContaining(item);

        return new ImportRegulationResponseDTO(importRegulations);
    }

    @Transactional
    public void loadItemKeywordCSV() throws IOException, CsvValidationException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(
                // 파일 명이 한글이면 인식을 못함
                Objects.requireNonNull(classLoader
                        .getResource("import_regulation/Strategic_Materials_Item_Keyword_2019.csv"))
                        .getFile()
        );
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
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
        InputStream is = getClass().getResourceAsStream("/import_regulation/Import_Regulations_DB_2024-07-15.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(is);

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
