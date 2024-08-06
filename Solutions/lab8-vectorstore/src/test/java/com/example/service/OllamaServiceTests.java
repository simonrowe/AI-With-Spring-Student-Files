package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-14: Define this test class as a Spring Boot test.
//  Use the @ActiveProfiles annotation to activate the "simple-vector-store" profile.

@SpringBootTest
@ActiveProfiles({"simple-vector-store","ollama-embedding"})
//@ActiveProfiles({"redis-vector-store","ollama-embedding"})
//@ActiveProfiles({"pg-vector-store","aws-cohere-embedding"})
public class OllamaServiceTests {

    //  TODO-15: Use the @Autowired annotation to inject an instance of the ProductService.
    @Autowired ProductService svc;

    String samplePrompt = "I need high quality wireless headphones to block out noise on a plane";

    //  TODO-16: Write a @Test method to validate the ProductService.
    //  First, call the service's save() method with the Utilities.products list; this populates the test data.
    //  Next, call the service's findClosestMatch() method with the samplePrompt String.
    //  Use assertThat() to validate that the result starts with "Wireless Headphones:".
    //  Finally, print the result to the console.
    @Test
    public void testFindClosestMatch() {
        svc.save(Utilities.products);
        String result = svc.findClosestMatch(samplePrompt);
        assertThat(result).startsWith("Wireless Headphones:");
        System.out.println(result);
    }

    //  TODO-17: Save all work.  Run this test, it should pass.

    //  TODO-22 (OPTIONAL):  Comment out the @ActiveProfiles annotation at the top of this class.
    //  Replace it with @ActiveProfiles("redis-vector-store")
    //  Save your work and run the test again.  It should pass.

    //  TODO-26 (OPTIONAL):  Comment out the @ActiveProfiles annotation at the top of this class.
    //  Replace it with @ActiveProfiles("pg-vector-store")
    //  Save your work and run the test again.  It should pass.

}
