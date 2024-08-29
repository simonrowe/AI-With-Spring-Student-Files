package com.example.service;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class StockServiceTests {

    @Autowired  StockService service;
    private String[] sampleResults = {"NVDA", StockService.price + "", StockService.volume + ""};

    @Test
    void testGetCompanySummary() {
        String summary = service.getCompanySummary("NVDA");

        assertThat(summary).isNotNull();
        assertThat(summary).contains(sampleResults);

        System.out.println("The company summary is: " + summary);
    }


}
