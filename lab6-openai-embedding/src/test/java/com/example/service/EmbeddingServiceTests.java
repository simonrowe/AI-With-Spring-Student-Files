package com.example.service;


import org.junit.jupiter.api.Test;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("openai-embedding")
public class EmbeddingServiceTests {

    @Autowired EmbeddingService svc;

    @Test
    public void testLookup() {
        Map result = svc.lookup("tell me a joke");
        EmbeddingResponse embeddingResponse = (EmbeddingResponse) result.get("embedding");
        assert(embeddingResponse != null);
    }

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
