package com.example.service;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("openai")
public class StockServiceTests {

    @Autowired  StockService service;

    @Test
    void testGetCompanySummary() {
        String summary = service.getCompanySummary("NVDA");

        assertThat(summary).isNotNull();
        assertThat(summary).contains("NVIDIA Corporation");

        System.out.println("The company summary is: " + summary);
    }


}
