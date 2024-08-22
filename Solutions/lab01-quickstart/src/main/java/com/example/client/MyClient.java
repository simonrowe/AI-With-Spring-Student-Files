package com.example.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyClient {

    private final ChatClient client;

    public MyClient(ChatClient.Builder builder) {
        client = builder.build();
    }

    public String callModel(String input) {
        return client
                .prompt()
                .user(input)
                .call()
                .content();
    }

}
