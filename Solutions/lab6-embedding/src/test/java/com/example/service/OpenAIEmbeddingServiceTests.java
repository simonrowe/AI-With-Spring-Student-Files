package com.example.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-10: Define this test class as a Spring Boot test.
//  Set the webEnvironment attribute to SpringBootTest.WebEnvironment.NONE.
//  Use the @ActiveProfiles annotation to activate the "embedding" and "openai" profiles.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles({"embedding","openai"})
public class OpenAIEmbeddingServiceTests {

    //  TODO-11: Use the @Autowired annotation to inject an instance of our EmbeddingService.
    @Autowired EmbeddingService svc;

    //  TODO-12: Define a test method to call the testFindClosestMatch method of the service.
    //  Use the Utilities class to provide the query and products inputs.
    //  Capture the result in a String variable.
    //  assertThat the result startsWith "Wireless Headphones".
    //  Print the result to the console:
    @Test
    public void testFindClosestMatch() {
        String result = svc.findClosestMatch(Utilities.query, Utilities.products);
        assertThat(result).startsWith("Wireless Headphones:");
        System.out.println(result);
    }

    //  TODO-13: Organize your imports and save your work.
    //  Run this test.  It should pass.
    //  The product description for wireless headphones should be displayed.

}
