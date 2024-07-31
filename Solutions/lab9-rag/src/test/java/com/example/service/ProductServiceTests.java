package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.Utilities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

//  TODO-17: Define this test class as a Spring Boot test.
//  Use the @ActiveProfiles annotation to activate the "simple-vector-store" and "aws" profiles.
//  Use the @ActiveProfiles annotation to activate the "simple-vector-store" and "azure" profiles.
//  Use the @ActiveProfiles annotation to activate the "simple-vector-store" and "ollama" profiles.
//  Use the @ActiveProfiles annotation to activate the "simple-vector-store" and "openai" profiles.
@SpringBootTest
@ActiveProfiles({"simple-vector-store","aws"})
//@ActiveProfiles({"simple-vector-store","azure"})
//@ActiveProfiles({"simple-vector-store","ollama"})
//@ActiveProfiles({"simple-vector-store","openai"})
public class ProductServiceTests {

    //  TODO-18: Use the @Autowired annotation to inject an instance of the ProductService.
    @Autowired ProductService svc;

    //  TODO-19: Write a @Test method to validate the ProductService.
    //  First, call the service's save() method with the Utilities.productCatalog list; this populates the test data.
    //  Next, call the service's getProductRecommendationsText() method with the Utilities.query String.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content is not null.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content contains expected results.
    //  Utilities.sampleResults array contains the expected results.
    //  Print the response string that is returned.
    @Test
    public void testGetProductRecommendationsText() {
        svc.save(Utilities.productCatalog);
        String response = svc.getProductRecommendationsText(Utilities.query);
        assertThat(response).isNotNull();
        assertThat(response).contains(Utilities.sampleResults);
        System.out.println(response);
    }

    //  TODO-20: Save all work.  Run this test, it should pass.

    //  TODO-23 (OPTIONAL):  Alter the @ActiveProfiles annotation at the top of this class.
    //  Replace the "simple-vector-store" profile with "redis-vector-store"
    //  Save your work and run the test again.  It should pass.

    //  TODO-26 (OPTIONAL):  Alter the @ActiveProfiles annotation at the top of this class.
    //  Replace the "simple-vector-store" profile with "pg-vector-store"
    //  Save your work and run the test again.  It should pass.

}
