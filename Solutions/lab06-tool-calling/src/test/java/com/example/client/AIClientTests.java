package com.example.client;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.service.StockTools;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.service.StockService;

import java.util.List;

//  TODO-09: Define this test class as a Spring Boot test.
//  Activate one of the given profiles based on which backend you are using.
@SpringBootTest
@ActiveProfiles("openai")
//@ActiveProfiles("azure")
//@ActiveProfiles("aws")
public class AIClientTests {

    //  TODO-10: Notice the AIClientUse is @Autowired.  This is the class under test.
    //   The StockService is also @Autowired.  We will use it when asserting the test results.
    @Autowired AIClient client;
    @Autowired StockService stockService;

	private String samplePrompt = "Provide a 50-100 word overview of company NVDA, including today's trading information such as price and volume.";
    String[] sampleResults;

    @BeforeEach
    void setUp() {
        sampleResults = new String[]{"NVDA", stockService.getStockPrice("NVDA") + "", stockService.getStockVolume("NVDA") + ""};
    }

    //  TODO-11: Define a @Test method to test the callApi() method of the client.
    //  Pass in a string that describes the response you wish to generate,
    //  such as the "samplePrompt" String defined above or an equivalent.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content is not null.
    //  Use AssertJ's Assertions.assertThat() method to ensure that the content contains expected results,
    //  such as the "sampleResults" String defined above or an equivalent.
    //  Print the response string that is returned.
    @Test
	void testCallApi() {

        String response = client.callApi(samplePrompt);

		assertThat(response).isNotNull();
        assertThat(response).contains(sampleResults);

		//	Print the results
		System.out.println("The results of the call are: " + response);
    }

    //  TODO-12: Organize all imports and save all work.
    //  Run this test, it should pass.
    //  Notice the output contains specific data obtained from the StockService.

    //  TODO-13: Examine the test below, it will verify that the model is able to use multiple tools.
    //  In order to calculate the portfolio value, it will need to
    //  1) use our tool to get the stock prices
    //  2) use our tool to calculate the entire portfolio.
    //  The test logic here will use our code directly.
    //  The formatted total should appear in the results..
    //  Remove the @Disabled annotation, run the test.  It should pass.
    @Test
    @Disabled
    void testCalculatePortfolioValue() {
        String input = """
            I have 100 shares of NVDA 
            and 50 shares of AAPL. 
            What is the total value of my portfolio?
        """;
        String response = client.callApi(input);

        //	Print the results
        System.out.println("The results of the call are: " + response);

        // Calculate the result manually:
        List<StockTools.Request> stocks = List.of(
            new StockTools.Request(100, stockService.getStockPrice("NVDA")),
            new StockTools.Request(50, stockService.getStockPrice("AAPL"))
        );
        String formattedResult = stockService.calculatePortfolioValueFormatted(stocks);

        //  The response returned from the model
        //  should contain the formatted dollar result:
        assertThat(response).isNotNull();
        assertThat(response).contains(formattedResult);
    }
}
