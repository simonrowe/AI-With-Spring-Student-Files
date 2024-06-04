package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("aws-cohere-embedding")
public class CohereEmbeddingServiceTests {

    @Autowired
    EmbeddingService svc;

    @Test
    public void testFindClosestMatch() {
        String result = svc.findClosestMatch(Utilities.query, Utilities.products);
        assert(result.startsWith("Wireless Headphones:"));
        System.out.println(result);
    }
}
