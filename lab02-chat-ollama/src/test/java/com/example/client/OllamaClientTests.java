package com.example.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("ollama")
public class OllamaClientTests {

    @Autowired
    private AIClient client;

    private String samplePrompt = "Generate the names of the five great lakes and their sizes in square miles.  Produce JSON output.";
    private String[] sampleResults = {"Huron", "Ontario", "Michigan", "Erie", "Superior"};

    @Test
    public void testCallApi() {
        String response = client.callApi(samplePrompt);
        
        assertThat(response).isNotNull();
        
        for (String expectedResult : sampleResults) {
            assertThat(response).contains(expectedResult);
        }
        
        System.out.println("Response: " + response);
    }



}
