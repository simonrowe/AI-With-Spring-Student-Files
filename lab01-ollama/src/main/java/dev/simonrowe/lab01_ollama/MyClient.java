package dev.simonrowe.lab01_ollama;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("ollama")
public class MyClient {

    private final ChatClient client;

    public MyClient(ChatModel chatModel) {
        this.client = ChatClient.builder(chatModel).build();
    }

    public String call(String message) {
        return client
                .prompt()
                .user(message)
                .call()
                .content();
    }
}
