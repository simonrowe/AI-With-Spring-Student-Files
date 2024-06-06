package com.example.client;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("azure")
public class AzureClient {

   	@Autowired AzureOpenAiChatModel chatModel;

    public String callModel(String prompt ) {

        ChatOptions options = 
            AzureOpenAiChatOptions.builder().build();
        
		ChatResponse response = chatModel.call(
			new Prompt(prompt,options )
        );

        return response.getResult().getOutput().getContent();
    }

}