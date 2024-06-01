package com.example.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-09: Define this test class as a Spring Boot test.
//  Set the webEnvironment attribute to SpringBootTest.WebEnvironment.NONE.
//  Use the @A
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("openai")
public class OpenAIClientTests {

    //  TODO-10: Use the @Autowired annotation to inject an instance of OpenAIClient.
    @Autowired OpenAIClient client;

    //  TODO-11: Define a test method to call the createImageUrl method of the client.
    //  Pass in a string that describes the image you want to generate, for example
    //  "Two golden retrievers playing tug-o-war in the snow.".
    //  Use AssertJ's Assertions.assertThat() method to ensure that the returned URL is not blank.
    //  Print the URL that is returned.
    @Test
    public void testCreateImageUrl() {
        String url = client.createImageUrl("Two golden retrievers playing tug-o-war in the snow.");
        Assertions.assertThat(url).isNotBlank();
        System.out.println("URL: " + url);
    }

    //  TODO-12: Run this test.  It should take a few moments to run, and it should pass.
    //  Find the URL of the generated image and open it in a browser to see the result.
}
