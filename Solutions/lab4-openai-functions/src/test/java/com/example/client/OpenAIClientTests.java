package com.example.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-13: Define this test class as a Spring Boot test.
//  Set the webEnvironment attribute to SpringBootTest.WebEnvironment.NONE.
//  Use the @ActiveProfiles annotation to activate the "aws" profile.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("openai")
public class OpenAIClientTests {

    //  TODO-14: Use the @Autowired annotation to inject an instance of our AIClient.
    @Autowired AIClient openAIClient;

	private String samplePrompt = "Provide current information on the company with stock ticker symbol NVDA.";
    private String[] sampleResults = {"NVDA", "price"};

    //  TODO-15: Define a @Test method to test the callApi() method of the client.
    //  Pass in a string that describes the response you wish to generate, 
    //  such as the "samplePrompt" String defined above or an equivalent.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content is not null.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content contains expected results.
    //  Print the response string that is returned.

	@Test
	void quickChat() {

        String response = 
            openAIClient.callApi(
                "Provide a description of the company with stock ticker symbol NVDA.");

		assertThat(response).isNotNull();
        assertThat(response).contains(sampleResults);

		//	Print the results
		System.out.println("The results of the call are: " + response);

    }


}
