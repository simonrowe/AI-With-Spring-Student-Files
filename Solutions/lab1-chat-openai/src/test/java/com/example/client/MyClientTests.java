package com.example.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyClientTests {

    @Autowired MyClient client;

    @Test
    public void testCallModel() {
        String response = client.callModel("What are the names of the five great lakes.  Produce JSON output.");
        Assertions.assertThat(response).contains("Superior", "Huron", "Erie", "Michigan", "Ontario");
        System.out.println("The response is: " + response);
    }

}
