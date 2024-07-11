package com.example.client;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.client.EntityTests.ActorFilms;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("openai")
public class CallbackTests {

    private ChatClient client;
    @Autowired OpenAiChatModel model;

    @Test
    void testCallback() {
        client = ChatClient.builder(model).build();

        String response = client.prompt()
        .user("Provide a 50-100 word overview of company IBM, including today's trading information and recent news.")
        .functions(
            "getStockPriceFunction",
            "getStockVolumeFunction",
            "getStockNewsFunction")
        .call()
        .content();
        
        System.out.println(response);
        // use assertJ to assert that the response contains the word "price" and "$":
        assertThat(response).contains("price", "$", "volume");
    }
        
}
