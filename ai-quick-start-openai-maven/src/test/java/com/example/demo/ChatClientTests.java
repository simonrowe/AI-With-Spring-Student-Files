package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ChatClientTests {

	@Autowired
	ChatClient chatClient;

	@Test
	void quickChat() {
		ChatResponse response = chatClient.call(
				new Prompt(
						"Generate the names of 5 famous pirates.",
						OpenAiChatOptions.builder()
								.withModel("gpt-3.5-turbo")
								//	.withModel("gpt-4.0o")
								.withTemperature(0.4f)
								.build()
				));

		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(response.getResults()).isNotEmpty();

		//	Print the results
		System.out.println("The results of the call are:");
		response.getResults().forEach(System.out::println);
	}

}
