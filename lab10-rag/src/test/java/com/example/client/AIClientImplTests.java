package com.example.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

//  TODO-11: Define this test class as a Spring Boot test.
//  Use the @ActiveProfiles annotation to activate: 
//  1) the "simple-vector-store" profile  AND
//  2) the matching chat model you plan to use:
//      For Amazon Bedrock,     use "aws".
//      For Azure OpenAI,       use "azure".
//      For standard OpenAI,    use "openai".
//      For Ollama,             use "ollama".



// @ActiveProfiles({"simple-vector-store","aws"})
// @ActiveProfiles({"simple-vector-store","azure"})
// @ActiveProfiles({"simple-vector-store","openai"})
// @ActiveProfiles({"simple-vector-store","ollama"})
public class AIClientImplTests {

    //  TODO-12: Use the @Autowired annotation to inject an instance of the AIClient.


    //  TODO-13: Write a @Test method to validate the AIClient.
    //  First, call the client's save() method with the Utilities.productCatalog list; this populates the test data.
    //  Next, call the client's getProductRecommendations() method with the Utilities.query String.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content is not null.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content contains expected results.
    //  Utilities.sampleResults array contains the expected results.
    //  Print the response string that is returned.


    //  TODO-14: Save all work.  Run this test, it should pass.







    //  TODO-23 (OPTIONAL):  Alter the @ActiveProfiles annotation at the top of this class.
    //  Replace the "simple-vector-store" profile with "redis-vector-store"
    //  Save your work and run the test again.  It should pass.

    //  TODO-26 (OPTIONAL):  Alter the @ActiveProfiles annotation at the top of this class.
    //  Replace the "simple-vector-store" profile with "pg-vector-store"
    //  Save your work and run the test again.  It should pass.
}
