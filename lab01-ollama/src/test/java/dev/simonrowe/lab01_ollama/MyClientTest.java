package dev.simonrowe.lab01_ollama;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("ollama")
class MyClientTest {

    @Autowired
    private MyClient myClient;

    @Test
    void testCallWithSimpleMessage() {
        String message = "Hello, how are you?";
        String response = myClient.call(message);
        
        assertNotNull(response);
        assertFalse(response.trim().isEmpty());
    }

    @Test
    void testCallWithDifferentMessage() {
        String message = "What is the capital of France?";
        String response = myClient.call(message);
        
        assertNotNull(response);
        assertFalse(response.trim().isEmpty());
        assertTrue(response.toLowerCase().contains("paris"));
    }

}