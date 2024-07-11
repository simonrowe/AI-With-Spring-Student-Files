package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-09: Define this test class as a Spring Boot test.
//  Set the webEnvironment attribute to SpringBootTest.WebEnvironment.NONE.
//  Use the @ActiveProfiles annotation to activate the "openai" profile.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("openai")  // Working.
//@ActiveProfiles("azure")   // Fails due to SQL syntax not matching DB dialect.
//@ActiveProfiles("aws")     // Fails due to extra words in the response.
//@ActiveProfiles("ollama")    // Fails due to extra words in the response.
public class ProductServiceTests {

    //  TODO-10: Use the @Autowired annotation to inject an instance of the ProductService.
    @Autowired ProductService productService;

    private String samplePrompt = "List the sales of the top 3 products by revenue during the last month.";

    //  TODO-11: Define a @Test method to test the adHocQuery() method of the productService.
    //  Pass in a string that describes the response you wish to generate, 
    //  such as the "samplePrompt" String defined above or an equivalent.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content is not null.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content contains expected number of rows.
    //  Print the response List that is returned.
    @Test
    void sqlTest() {
        List<Map<String,Object>> response =
            productService.adHocQuery(samplePrompt);

        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(3);
        
        //  Print the results
        System.out.println("The results of the call are: " + response);
    }

}
