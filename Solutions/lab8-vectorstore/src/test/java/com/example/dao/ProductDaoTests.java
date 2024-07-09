package com.example.dao;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.service.Utilities;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles({"simple-vector-store","openai-embedding"})
//@ActiveProfiles({"pg-vector-store","openai-embedding"})
//@ActiveProfiles({"redis-vector-store","openai-embedding"})

public class ProductDaoTests {

    @Autowired ProductDao dao;

    @BeforeEach
    public void setUp() {
        dao.add(Utilities.products);
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


}
