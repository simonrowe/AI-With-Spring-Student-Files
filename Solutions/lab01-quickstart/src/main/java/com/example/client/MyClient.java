package com.example.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

@Component
public class MyClient {

    private final ChatClient client;

    public MyClient(ChatModel model) {
        client = ChatClient.builder(model).build();
    }

    public String call(String input) {
        return client
                .prompt()
                .user(input)
                .call()
                .content();
    }

}
