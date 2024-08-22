## Lab 4 - OpenAI Local Functions

In this exercise you will configure SpringAI's ChatClient with a local function.  This function will be used to embellish the response from the API with external knowledge not known to the model.

An example is combining general knowlege about a given company with its current stock price.  The model is trained with general information, but does not have access to current data.  The following is an example of a combination of the two:

> The stock ticker symbol 'TSLA' belongs to Tesla, Inc. Tesla is a renowned electric vehicle and clean energy company. The current stock price for Tesla is $174.11 

---
**Part 1 - Create Spring Boot Project**

1. Create a new Spring Boot application. Name the project **lab-4-openai-local**, and use this value for the artifact. Use JAR packaging and the latest versions of Java. Use the latest version of Boot. Set the package as `com.example`. Search for and select "OpenAI" as a dependency.  Generate, Unzip, and import this project into your IDE.

1. Find and rename the `src/main/resources/application.properties` to `src/main/resources/application.yml`. Establish the following entries:
```
spring:
  application.name: lab4-openai-functions
  main.web-application-type: none  # No web server.
```
3. Find the main test class in `src/test/java/com/example`.  Rename it to `ApplicationTests.java` (if you like.)  Run the test, the test should start and stop with no errors.
- Note: This assumes the `SPRING_AI_OPENAI_API_KEY` is still set from previous labs.

---
**Part 2 - Create a Client Object**

Similar to previous labs, we will establish a client object for our application to interact with OpenAI. To decouple our business logic from a specific AI model, we will build an interface first.

4. Create a new **client** folder under `src/main/java/com/example`.  Within this package create a new Java file called `AIClient.java`.
* The IDE should create an empty Java class definition for you.
5. Copy the following code to define this interface and save your work:
```
public interface AIClient {
    public String callModel(String prompt );
    public String callModel(String prompt, String model );
}
```
* Note: the purpose of this interface is to decouple the `@Service` bean we will build from specific AI APIs.
6. Within `src/main/java/com/example/client` create a new Java file called `OpenAIClient`.  Give it the following initial definition.  Organize imports and save your work:
```
@Component
@Profile("openai")
public class OpenAIClient implements AIClient {

   	@Autowired
	ChatClient chatClient;

    public String callModel(String prompt ) {
		return callModel(prompt, null);
	}

    public String callModel(String prompt, String model ) {
        return null;
    }
}
```
* This client object will provide the implementation specific to OpenAI.  Spring will only create it when the "openai" profile is active, making it possible for our code to support multiple models.  The `@Autowired ChatClient` is provided automatically by Spring Boot.

7. Implement the second `callModel` method.  With the following code:
```
    public String callModel(String prompt, String model ) {

        if (model==null) {
            model = "gpt-3.5-turbo";    // (1)
        }

		ChatResponse response = chatClient.call(
			new Prompt(prompt,              // (2)
            	OpenAiChatOptions.builder()
					.withModel(model)
                    .withFunction("stockService") // (3)
					.build()
				));

        return response.getResults().get(0).getOutput().getContent();   //  (4)
    }
```
* (1) If the incoming model parameter is null, set it to a default value. **NOTE:** This value may need to be adjusted if you take this course after 2024.  See https://platform.openai.com/docs/models.
* (2) The prompt will be passed from our `@Service` object.
* (3) Critical: `withFunction()` allows us to define the function to be called to embellish the response.  `stockService` will be a bean which we will create in the next step.
* (4) Return the result.

8. Organize your imports and save your work.


---
**Part 3 - Create a Service Object**

Next we will create a `@Bean` which retrieves current price of a given stock given a ticker symbol.  OpenAI's models are trained on data from the past; they can give us general information about a company, but cannot provide current information.  We will use a local function to obtain this.

To keep our lab focused, the service will generate a random price rather than attempt to lookup a live value.

9. Create a new **service** folder under `src/main/java/com/example`.  Within this package create a new Java file called `StockService.java`.
1. Copy the following code to begin, expect a compilation error:
```
@Service("stockService")    // (1)
@Description("Service to get stock information")    // (2)	
public class StockService implements Function<com.example.service.StockService.Request, com.example.service.StockService.Response> {  // (3)

}
```
* (1) The name of this `@Service` must match the value provided to the `withFunction()` call on the client's options.
* (2) The `@Description` provides descriptive information to the client.
* (3) The class must implement `java.util.function.Function` to be usable to the `ChatClient`.  The interface will define input and output types which will be communicated to the API.

11. Next, add the following `Record` definitions to the class to clearly define the input and output types.  This will impact the JSON description sent to the API:
```
    public record Request(String symbol) {}
    public record Response(String symbol, Double price, String currency) {}
```

12. The `Function` interface requires an `apply()` method.  This method will be called by the client after the response is received from the API.  It will provide current information on the stock price:
```
	public Response apply(Request request) {
		//	For now, return an artificial response:
		double price = Math.random()*800;
		return new Response(request.symbol(),price, "USD");
	}    
```
13. Now we can implement our main business method.  Our goal is to provide a brief description of a company including current price information:
```
    @Autowired AIClient client;
	
	public String getCompanySummary(String symbol) {
		return client.callModel(
			"Provide a description of the company with stock ticker symbol '%s'".formatted(symbol)
		);
	}
```
14. Organize your imports, save your work.

---
**Part 4 - `@Test` Your Service**

Anything we code, we should test.  

15. Create a new **service** folder under `src/test/java/com/example`.  Within this package create a new Java file called `StockServiceTests.java`.  Provide the following initial implementation:
```
@SpringBootTest
@ActiveProfiles("openai")
public class StockServiceTests {

    @Autowired  StockService service;

}
```

16. Provide a `@Test` method to call the service's `getCompanySummary()` with the stock ticker symbol of your choice.  Assert that the response contains information on the company you selected:
```
    @Test
    void testGetCompanySummary() {
        String summary = service.getCompanySummary("NVDA");

        assertThat(summary).isNotNull();
        assertThat(summary).contains("NVIDIA Corporation");

        System.out.println("The company summary is: " + summary);
    }
```
17. Organize imports and save your work.  Note that the `assertThat` functions are part of AssertJ.  You may need to manually add the following import statement:  `import static org.assertj.core.api.Assertions.*;`.

1. Run the test, it should pass.  You should see a description of the output similar to this:

> The company summary is: NVIDIA Corporation (NVDA) is a technology company known for its graphics processing units (GPUs) and chipsets. The stock price for NVDA is $607.37 USD.

**Part 7 - Summary**
Our application now has an integration point with the OpenAI API.  Spring's `ChatClient` instructs the API that a local function exists to provide missing information.  The generated response is embellished by the client by calling the designated function. 