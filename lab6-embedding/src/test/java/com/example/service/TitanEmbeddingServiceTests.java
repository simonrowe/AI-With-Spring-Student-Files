package com.example.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("aws-titan-embedding")
public class TitanEmbeddingServiceTests {

    @Autowired
    EmbeddingService svc;

    @Test
    @Disabled  //  Temporarily disabled due to Titan response formatting problem.  ValidationException: Malformed input request: 2 schema violations found, please reformat your input and try again. (Service: BedrockRuntime, Status Code: 400, Request ID: xxxxxx
    public void testFindClosestMatch() {
        String result = svc.findClosestMatch(Utilities.query, Utilities.products);
        assert(result.startsWith("Wireless Headphones:"));
        System.out.println(result);
    }
}
