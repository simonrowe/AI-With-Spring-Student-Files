package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("aws-cohere-embedding")
public class CohereEmbeddingServiceTests {

    @Autowired
    EmbeddingService svc;

    List<String> products = List.of(
            "Wireless Mouse: A comfortable wireless mouse with ergonomic design and long battery life, perfect for seamless connectivity without cables.",
            "Wireless Headphones: Lightweight, noise-canceling technology, immersive sound and long battery life, ideal for people on-the-go.",
            "Bluetooth Speaker: Portable Bluetooth speaker with excellent sound quality and long battery life, suitable for outdoor use and parties.",
            "4K Monitor: A 27-inch 4K UHD monitor with vibrant colors and high dynamic range for stunning visuals, providing an exceptional viewing experience."
    );
    String query = "I need high quality wireless headphones to block out noise on a plane";

    @Test
    public void testFindClosestMatch() {
        String result = svc.findClosestMatch(query, products);
        assert(result.startsWith("Wireless Headphones:"));
        System.out.println(result);
    }
}
