package com.example.dao;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import com.example.client.AIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import com.example.Utilities;

//  TODO-08: Run this test to verify your DAO has been built correctly.
//   It should pass.  If not, fix any issues before proceeding.
@SpringBootTest
@ActiveProfiles("simple-vector-store")
public class ProductDaoTests {

    @Autowired ProductDao dao;

    @BeforeEach
    public void setUp() {
        dao.add(Utilities.productCatalog);
    }

    @Test
    public void testFindClosestMatch() {
        String query = "I need a gift for my mom to help her with diet and exercise.";
        List<String> results = dao.findClosestMatches(query,2);
        results.forEach(System.out::println);

        assertThat(results)
                .extracting(element -> element.startsWith("Wireless Fitness Tracker"))
                .contains(true);
        assertThat(results)
                .extracting(element -> element.startsWith("Smart Garden Kit"))
                .contains(true);
    }

    @TestConfiguration
    static class Config {

        //  While testing the ProductDao, we don't want to bother with dependency injection issues on the ProductService.
        //  This stub implementation of the AIClient satisfies the ProductService's dependency.
        @Bean
        public AIClient aiClient() {
            return new AIClient() {
                @Override
                public String getProductRecommendationsText(String prompt, List<String> products) {
                    return "stub implementation.";
                }
            };
        }
    }
}
