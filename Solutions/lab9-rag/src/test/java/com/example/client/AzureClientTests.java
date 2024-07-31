package com.example.client;

import com.example.Utilities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

//  TODO-13: Define this test class as a Spring Boot test.
//  Use the @ActiveProfiles annotation to activate the "simple-vector-store" and "azure" profiles.
@SpringBootTest
@ActiveProfiles({"simple-vector-store","azure"})
public class AzureClientTests {

    //  TODO-14: Use the @Autowired annotation to inject an instance of our AIClient.
    @Autowired AIClient client;

    //  TODO-15: Define a @Test method to test the getProductRecommendationsText() method of the client.
    //  Pass in a query string to describe a search request.  Use Utilities.query.
    //  Pass in a List of products to pass in the prompt.  Use Utilities.productMatches
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content is not null.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content contains expected results.
    //  Utilities.sampleResults array contains the expected results.
    //  Print the response string that is returned.
    @Test
    void testGetProductRecommendationsText() {
        String response =
                client.getProductRecommendationsText(Utilities.query, Utilities.productMatches);
        assertThat(response).isNotNull();
        assertThat(response).contains(Utilities.sampleResults);

        //	Print the results
        System.out.println("The results of the call are: " + response);
    }

    //  TODO-16: Organize your imports and save your work.
    //  Run this test, it should pass.
    //  Check the results in the console.

}
