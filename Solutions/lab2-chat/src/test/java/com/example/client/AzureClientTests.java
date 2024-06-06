package com.example.client;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("azure")
public class AzureClientTests {

    @Autowired AzureClient client;

	@Test
	void quickChat() {

        String response = 
            client.callModel(
                "Generate the names of the five great lakes.  Produce JSON output.");

		assertThat(response).isNotNull();
        assertThat(response).contains("Huron", "Ontario", "Michigan", "Erie", "Superior");

		//	Print the results
		System.out.println("The results of the call are: " + response);
    }

}
