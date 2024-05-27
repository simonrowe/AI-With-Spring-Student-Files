package com.example.client;

import org.springframework.ai.azure.openai.AzureOpenAiChatOptions;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("azure")
public class AzureClient {

   	@Autowired
	ChatClient chatClient;

    public String callModel(String prompt ) {

		ChatResponse response = chatClient.call(
			new Prompt(prompt,
            	AzureOpenAiChatOptions.builder()
				.build()
				));

        return response.getResults().get(0).getOutput().getContent();
    }

}
