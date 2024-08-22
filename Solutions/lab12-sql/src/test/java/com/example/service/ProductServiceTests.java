package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-15: Define this test class as a Spring Boot test.
//  Use the @ActiveProfiles annotation to activate the "aws" profile.
@SpringBootTest
@ActiveProfiles("openai")  // Working.
//@ActiveProfiles("azure")   // Working.
//@ActiveProfiles("aws")     // Working.
//@ActiveProfiles("ollama")    // Fails due to extra words in the response.
public class ProductServiceTests {

    //  TODO-16: Use the @Autowired annotation to inject an instance of the ProductService.
    @Autowired ProductService productService;

    private String samplePrompt = "List the sales of the top 3 products by revenue during the last 30 days.";
    private String[] sampleResults = {"Smart Watch", "149.85", "Gaming Console", "121.50", "Digital SLR Camera", "120.00"};


    //  TODO-17: Define a productQueryTest() @Test method to test the productQuery() method of the productService.
    //  Pass in a string that describes the query to be made. 
    //  Use the "samplePrompt" String defined above or an equivalent.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content is not null.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content contains some expected results.
    //  Use the "sampleResults" array as an example.
    //  Print the response List that is returned.
    @Test
    void productQueryTest() {
        String response =
            productService.productQuery(samplePrompt);

        assertThat(response).isNotNull();

        //  Print the results
        System.out.println("The results of the call are: " + response);

        assertThat(response).contains(sampleResults);
        
    }

    //  TODO-18: Organize the imports. Save all work.
    //  Run the test.  It should pass.
}
