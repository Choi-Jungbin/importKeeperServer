package com.example.importkeeperserver.store;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(StoreService.class)
public class StoreServiceTest {

    @Test
    public void testCorporationInit(){

    }
}
