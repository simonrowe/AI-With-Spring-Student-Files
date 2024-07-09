package com.example.client;

import java.util.List;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//  TODO-05: Use a stereotype annotation to mark this class as a Spring bean.
//  Use an annotation to assign it to the "openai" profile.
@Component
@Profile("openai")
public class OpenAIClient implements AIClient {

	//	TODO-06: Use the @Autowired annotation to inject a ChatClient into this class.
	//  In this project, the OpenAIClient is the single bean of type ChatClient. 
	@Autowired ChatClient chatClient;

    public String callApi(String input ) {

		//  TODO-07: Build a ChatOptions object using the OpenAiChatOptions builder.
		//  Use the withFunction() method to set the "stockService" bean name.
		ChatOptions options = 
			OpenAiChatOptions.builder()
				.withFunction("stockService")
				.build();

		//  TODO-08: Build a Prompt object using the input and the ChatOptions object:
		Prompt prompt = new Prompt(input, options);

		//  TODO-09: Call the ChatClient's call method with the Prompt object.
		//  Store the response in a ChatResponse object:
		ChatResponse response = chatClient.call(prompt);

		//  TODO-10: Return the content of the first generation in the API response.
        return response.getResults().get(0).getOutput().getContent();
    }

}