package com.example.client;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

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
        assertThat(url).isNotBlank();
        System.out.println("URL: " + url);
    }

    //  TODO-12: Organize your imports and save your work.
    //  Run this test.  Expect it to take a few moments to run, and it should pass.
    //  Find the URL of the generated image and open it in a browser to see the result.



    //  TODO-17 (Optional): Using your earlier @Test method as an example, define a new test method
    //  to test the client's createImageB64 method.  Pass in whatever image description you like.
    //  As before, use assertThat() to ensure that the returned Base-64 encoded String is not blank.
    //  Call the provided testValidBase64Image() method to check that the returned string appears to be a valid image.
    //  Call the provided saveBase64Image() to save a copy of your image.
    //  Organize your imports and save your work.
    //  Run this test.  Expect it to take a few moments to run, and it should pass.
    //  Open the newly generated image an admire your handiwork!

    @Test
    public void testCreateImageB64() {
        String imageB64 = client.createImageB64("Two golden retrievers playing tug-o-war in the snow.");
        assertThat(imageB64).isNotBlank();
        testValidBase64Image(imageB64);
        saveBase64Image(imageB64, "image.png");
    }


    //  This method provides basic verification that a Base-64 String APPERAS to be a valid image.
    //  It does not have a way to verify the content of the image.
    private void testValidBase64Image(String b64) {

        //  Can the incoming Base-64 String be decoded:
        byte[] decoded = Base64.getDecoder().decode(b64);
        Assertions.assertThat(decoded).isNotEmpty(); // Check for decoded data

        //  Test that the decoded data APPEARS to represent a valid PNG image.
        //  PNGs start with 0x89 signature and at least 4 bytes
        ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
        boolean hasCommonImageHeaderBytes =
                bis.read() == 0x89 && bis.available() >= 4;

        Assertions.assertThat(hasCommonImageHeaderBytes)
                .withFailMessage("Returned Base64 data does not appear to be a valid PNG image.")
                .isTrue();
    }

    //  This method saves a Base-64 encoded image to a file.
    private static void saveBase64Image(String b64, String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            filePath = "image.png";
        }
        byte[] decoded = Base64.getDecoder().decode(b64);
        try {
            Files.write(Paths.get(filePath), decoded);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
