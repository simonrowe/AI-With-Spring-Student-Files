package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-13: Define this test class as a Spring Boot test.
//  Use the @ActiveProfiles annotation to activate: 
//  1) the "simple-vector-store" profile  AND
//  2) the matching embedding model you plan to use:
//      For Amazon Bedrock, Cohere,     use "aws-cohere-embedding".
//      For Amazon Bedrock, Titan,      use "aws-titan-embedding".
//      For standard OpenAI,            use "openai-embedding".
//      For Ollama,                     use "ollama-embedding".


@SpringBootTest
// @ActiveProfiles({"simple-vector-store","aws-cohere-embedding"})
// @ActiveProfiles({"simple-vector-store","aws-titan-embedding"})
// @ActiveProfiles({"simple-vector-store","openai-embedding"})
// @ActiveProfiles({"simple-vector-store","ollama-embedding"})
public class ProductServiceTests {

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

    //  TODO-22 (OPTIONAL):  Adjust the @ActiveProfiles annotation at the top of this class.
    //  Replace "simple-vector-store" with "redis-vector-store"
    //  Save your work and run the test again.  It should pass.

    //  TODO-26 (OPTIONAL):  Adjust the @ActiveProfiles annotation at the top of this class.
    //  Replace "simple-vector-store" with "pg-vector-store"
    //  Save your work and run the test again.  It should pass.

}
