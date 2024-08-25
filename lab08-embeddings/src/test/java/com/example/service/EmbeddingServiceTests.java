package com.example.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-10: Define this test class as a Spring Boot test.
//  Use the @ActiveProfiles annotation to activate the matching embedding model you plan to use.
//      For Amazon Bedrock,     use "aws".
//      For standard OpenAI,    use "openai".
//      For Ollama,             use "ollama".

public class EmbeddingServiceTests {


    //  Sample product catalog:
    List<String> products = List.of(
            "Wireless Mouse: A comfortable wireless mouse with ergonomic design and long battery life, perfect for seamless connectivity without cables.",
            "Wireless Headphones: Lightweight, noise-canceling technology, immersive sound and long battery life, ideal for people on-the-go.",
            "Bluetooth Speaker: Portable Bluetooth speaker with excellent sound quality and long battery life, suitable for outdoor use and parties.",
            "4K Monitor: A 27-inch 4K UHD monitor with vibrant colors and high dynamic range for stunning visuals, providing an exceptional viewing experience."
    );

    //  Sample user query:
    String query = "I need high quality wireless headphones to block out noise on a plane";


    //  TODO-12: Define a test method to call the findClosestMatch method of the service.
    //  Use the provided query and products variables as test inputs.
    //  Capture the result in a String variable.
    //  assertThat the result startsWith "Wireless Headphones".
    //  Print the result to the console:



    
    //  TODO-13: Organize your imports and save your work.
    //  Run this test.  It should pass.
    //  The product description for wireless headphones should be displayed.



    //  TODO-14 (OPTIONAL): Instead of using an external model, we can use an internal SpringAI class to create embeddings.
    //  Alter the @ActiveProfiles method to enable the "internal" profiles.
    //  Open application.yml and view the configuration of the "internal" profile,
    //  It simply enables SpringAI's TransformersEmbeddingModel bean.
    //  Run the test again.  It should still pass, but this time no external model was used.
}
