package com.example.client;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("openAIClientBean")
@Profile("openai")
public class OpenAIClient {

   	@Autowired OpenAiChatModel chatModel;

    public String callModel(String prompt, String model ) {

        if (model==null) {
            model = "gpt-3.5-turbo";
        }

		ChatOptions options = OpenAiChatOptions.builder()
			.withModel(model)
			.withTemperature(0.4f)
			.build();

		ChatResponse response = chatModel.call(
			new Prompt(prompt,options)
		);

		assertResponse(response);
        return response.getResult().getOutput().getContent();
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