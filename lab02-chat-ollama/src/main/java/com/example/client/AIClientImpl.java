package com.example.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

@Component
public class AIClientImpl implements AIClient {
    
    private final ChatClient client;
    
    public AIClientImpl(ChatModel model) {
        this.client = ChatClient.builder(model).build();
    }


    public String callApi(String input ) {
        return client.prompt()
                .user(input)
                .call()
                .content();
    }

}
