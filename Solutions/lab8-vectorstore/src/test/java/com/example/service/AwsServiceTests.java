package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

//  TODO-13: Define this test class as a Spring Boot test.
//  Set the webEnvironment attribute to SpringBootTest.WebEnvironment.NONE.
//  Use the @ActiveProfiles annotation to activate the "simple-vector-store" profile.

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles({"simple-vector-store","aws-cohere-embedding"})
//@ActiveProfiles({"redis-vector-store","aws-cohere-embedding"})
//@ActiveProfiles({"pg-vector-store","aws-cohere-embedding"})
public class CohereServiceTests {

    //  TODO-14: Use the @Autowired annotation to inject an instance of the ProductService.
    @Autowired ProductService svc;
   
    //  TODO-15: Write a @Test method to validate the ProductService.
    //  First, call the service's save() method with the Utilities.products list; this populates the test data.
    //  Next, call the service's findClosestMatch() method with the samplePrompt String.
    //  Use assertThat() to validate that the result starts with "Wireless Headphones:".
    //  Finally, print the result to the console.
    @Test
    public void testFindClosestMatch() {
        svc.save(Utilities.products);
        String result = svc.findClosestMatch(Utilities.samplePrompt);
        assertThat(result).startsWith("Wireless Headphones:");
        System.out.println(result);
    }

    //  TODO-16: Save all work.  Run this test, it should pass.

    //  TODO-22 (OPTIONAL):  Comment out the @ActiveProfiles annotation at the top of this class.
    //  Replace it with @ActiveProfiles("redis-vector-store")
    //  Save your work and run the test again.  It should pass.

    //  TODO-26 (OPTIONAL):  Comment out the @ActiveProfiles annotation at the top of this class.
    //  Replace it with @ActiveProfiles("pg-vector-store")
    //  Save your work and run the test again.  It should pass.

}
