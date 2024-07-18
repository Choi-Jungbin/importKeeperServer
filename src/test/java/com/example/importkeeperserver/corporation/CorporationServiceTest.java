package com.example.importkeeperserver.corporation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(CorporationService.class)
public class CorporationServiceTest {

    @Test
    public void testCorporationInit(){

    }
}
