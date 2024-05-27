package com.example.client;

import java.util.List;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("openai")
public class OpenAIClient {

   	@Autowired
	ChatClient chatClient;

    public String callModel(String prompt, String model ) {

        if (model==null) {
            model = "gpt-3.5-turbo";
        }

		ChatResponse response = chatClient.call(
			new Prompt(prompt,
            	OpenAiChatOptions.builder()
					.withModel(model)
                    .withTemperature(0.4f)
					.build()
				));

		assertResponse(response);
        return response.getResults().get(0).getOutput().getContent();
    }

	private boolean assertResponse (ChatResponse response) {
		assert response != null :  "API Response was null";
		assert response.getResults() != null : "getResults() was null";
		assert response.getResults().size() > 0 :  "getResults() was empty";
		assert response.getResults().get(0) != null :  "First Generation in API Response was missing";
		assert response.getResults().get(0).getOutput() != null :  "Assistant Message from the API Response generation was missing";
		assert response.getResults().get(0).getOutput().getContent() != null : "Assistant Message from the API Response generation was empty";
		return true;
	}
}