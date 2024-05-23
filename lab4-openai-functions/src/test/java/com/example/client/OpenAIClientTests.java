package com.example.client;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("openai")
public class OpenAIClientTests {

    @Autowired OpenAIClient openAIClient;

	@Test
	void quickChat() {

        String response = 
            openAIClient.callModel(
                "Provide a description of the company with stock ticker symbol NVDA.",
                "gpt-3.5-turbo");

		Assertions.assertThat(response).isNotNull();

		//	Print the results
		System.out.println("The results of the call are: " + response);

    }

	@Test
	void invalidModel() {

		NonTransientAiException exception = 
			assertThrows(NonTransientAiException.class, () -> {
				openAIClient.callModel(
					"What should happen if we pass in an invalid model name?",
					"invalid");
			});

		assertTrue(exception.getMessage().startsWith("404"));	
	}

}
