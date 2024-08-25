package com.example.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.service.StockService;

//  TODO-13: Define this test class as a Spring Boot test.
//  Use the @ActiveProfiles annotation to activate the "openai" profile.
@SpringBootTest
@ActiveProfiles("openai")
public class OpenAIClientTests {

    //  TODO-14: Use the @Autowired annotation to inject an instance of our AIClient.
    @Autowired AIClient client;

	private String samplePrompt = "Provide a 50-100 word overview of company NVDA, including today's trading information such as price and volume.";
    private String[] sampleResults = {"NVDA", StockService.price + "", StockService.volume + ""};

    //  TODO-15: Define a @Test method to test the callApi() method of the client.
    //  Pass in a string that describes the response you wish to generate, 
    //  such as the "samplePrompt" String defined above or an equivalent.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content is not null.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content contains expected results.
    //  Print the response string that is returned.

	@Test
	void testCallApi() {

        String response = client.callApi(samplePrompt);

		assertThat(response).isNotNull();
        assertThat(response).contains(sampleResults);

		//	Print the results
		System.out.println("The results of the call are: " + response);
    }

    //  TODO-16: Organize imports and save all work.
    //  Run this test, it should pass.
    //  Notice the output contains specific data obtained from the StockService.

}
