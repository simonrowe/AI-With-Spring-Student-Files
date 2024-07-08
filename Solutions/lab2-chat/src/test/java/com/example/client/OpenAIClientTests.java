package com.example.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-08: Implement a test class for the OpenAIClient class.
//  Use the @SpringBootTest annotation to load the Spring context.
//  Set the webEnvironment to NONE.
//  Use the @ActiveProfiles annotation to specify the "openai" profile.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("openai")
public class OpenAIClientTests {

    //  TODO-09: Inject your AIClient object into this test class:
    @Autowired AIClient client;

	@Test
    //@Disabled
	void quickChat() {

        String prompt = "Generate the names of the five great lakes and their sizes in square miles.  Produce JSON output.";
        String response = null;

        //  TODO-10: Use the client object to call the API. 
        //  Pass the prompt string defined above, or use another.
        //  Save the response in the response variable.
        response =
            client.callApi(prompt);


		assertThat(response).isNotNull();
		assertThat(response).contains("Huron", "Ontario", "Michigan", "Erie", "Superior");
		
		//	Print the results
		System.out.println("The results of the call are: " + response);
    }

    //  TODO-11: Save your work.  
    //  Remove the @Disabled annotation from the test method.
    //  Run the test, it should pass.

}
