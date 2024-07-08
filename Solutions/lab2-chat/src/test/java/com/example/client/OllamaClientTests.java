package com.example.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

//  TODO-08: Implement a test class for the AwsClient class.
//  Use the @SpringBootTest annotation to load the Spring context.
//  Set the webEnvironment to NONE.
//  Use the @ActiveProfiles annotation to specify the "aws" profile.

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("ollama")
public class OllamaClientTests {

    //  TODO-09: Inject your AIClient object into this test class:
    @Autowired AIClient client;

    @Test
    //@Disabled
    public void testCallModel() {
        String prompt = "Generate a list containing the names of the five great lakes. Return JSON format only; do not return any additional text or explanations outside of the JSON structure.";
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
