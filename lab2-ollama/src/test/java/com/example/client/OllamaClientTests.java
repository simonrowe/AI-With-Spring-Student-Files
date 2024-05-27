package com.example.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("ollama")
public class OllamaClientTests {

    @Autowired OllamaClient client;

    @Test
    public void testCallModel() {
        String response = client.callModel("What are the 5 great lakes?");
		Assertions.assertThat(response).isNotNull();
        System.out.println(response);
    }

    
}
