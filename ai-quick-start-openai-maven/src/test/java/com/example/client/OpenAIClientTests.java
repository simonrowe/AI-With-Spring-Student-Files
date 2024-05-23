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

	@Value("${SPRING_AI_OPENAI_API_KEY}") String keyBeingUsed;

	@Test
	void quickChat() {

		System.out.println("The key being used is: " + keyBeingUsed);

        String response = 
            openAIClient.callModel(
                "Generate the names of the five great lakes.  Produce JSON output.",
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
