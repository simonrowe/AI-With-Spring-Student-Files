## Lab 06 - OpenAI Local Functions

In this exercise you will configure SpringAI's ChatClient with a local function.  This function will be used to embellish the response from the API with external knowledge not known to the model.

An example is combining general knowlege about a given company with its current stock price.  The model is trained with general information, but does not have access to current data.  The following is an example of a combination of the two:

> The stock ticker symbol 'TSLA' belongs to Tesla, Inc. Tesla is a renowned electric vehicle and clean energy company. The current stock price for Tesla is $174.11 

---
**Part 1 - Setup the Project**

1. Open the _/student-files/lab06-functions-openai_ folder.  

    The project should be free of compiler errors at this point.  Address any issues you see before continuing.

1. Find the TODO instructions.

    * **Using VS Code:** Select "Edit" / "Find in Files..." and search for "TODO".
    * **Using IntelliJ:**  Select "Edit" / "Find" / "Find in Files..." and search for "TODO".  Use the "Open in Find Window" button to place the results in a tab for easy navigation.
    * **Using Eclipse:** Select "Window" / "Show View" / "Tasks".  Use the "Search" view to search for "TODO".  Find a filter icon on the top of this tab.  Check "Show all items", "TODOs".  For scope select "on elements in the selected projects".  The list of TODOs will appear in order!

    IMPORTANT: Work through the TODO instructions in order!   


---
**Part 2 - Establish an OpenAI Account**

1. **TODO-01**: If you have not already done so, setup an account with OpenAI.  The instructions are in the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)**. 



---
**Part 3 - Initial Configuration and Test**

2. Open the **pom.xml** file.

1. **TODO-02**: Observe that the dependency for OpenAI is already setup.  There is nothing you need to do here:

    ```
    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-openai-spring-boot-starter</artifactId>
    </dependency>
    ```
    - Warning: Do NOT use the dependency for _azure_ OpenAI in this lab. 

1.  Open `src/main/java/com/example/Application.java`.

1.  **TODO-03**: Run the application.

    * **VS Code**: Right-click, Run Java.  Or open the file and click the “Run” option hovering over the main() method.
    * **IntelliJ**: Right-click, select “Run ‘Application.main()’”. 
    * **Eclipse**: Right-click, Select Run As / Java Application.

    We expect the application to start, then stop, without errors.  If you have any errors related to tooling, be sure to address them now before proceeding.

1.  **TODO-04**: Open `src/main/resources/application.yml`. 
    * Set `spring.application.name` to "Lab06 OpenAI Functions" or something similar.
    * Set `spring.main.web-application-type` to none to run as a non-web application.  Spring AI applications can run as web applications, but these exercises avoid this distraction.
    * Set `spring.ai.retry.max-attempts` to 1 to fail fast to save time if you have errors.
    * Set `spring.ai.retry.on-client-errors` to false since there is typically no point in retrying a client (vs server) error.
    * Set `spring.ai.openai.chat.enabled` to true to enable the chat model.
    * Set `spring.ai.openai.chat.options.model` to "gpt-35-turbo" to use the GPT-3.5 model, or allow it to default.

    ```
    spring:
      application.name: Lab06 Local Functions
      main.web-application-type: none     # Do not start a web server.
      ai:
        retry:
          max-attempts: 1      # Maximum number of retry attempts.
          on-client-errors: false   # Do not retry for 4xx errors.
        openai:
          api-key: NEVER-PLACE-SECRET-KEY-IN-CONFIG-FILE
          chat.enabled: true          
    ```

1.  Save your work.  



---
**Part 4 - Create a Client Object**

8. Open `src/main/java/com.example.client.OpenAIClientImpl`.

1. **TODO-05:** Use an annotation to mark this class as a Spring bean.  

    ```
    @Component
    public class OpenAIClientImpl implements AIClient {
    ```
1. **TODO-06:** Within the class, create a constructor for this bean.
    * Inject a `ChatModel` object into the constructor.
    * Pass the model to the `ChatClient.builder` to build a `ChatClient` object.
    * Save the `ChatClient` object in the client field.

    ```
    public OpenAIClientImpl(ChatModel model) {
        client = ChatClient.builder(model).build();
    }
    ```

1. ***TODO-07:** Within the `callApi()` method, build a `ChatOptions` object using the `OpenAiChatOptions` builder.
    * Use the `withFunction()` method to set the "stockService" bean name.

    ```
		ChatOptions options = 
			OpenAiChatOptions.builder()
				.withFunction("stockService")
				.build();
    ```

1. **TODO-08:** Build a Prompt object using the input and the `ChatOptions` object:

    ```
	Prompt prompt = new Prompt(input, options);
    ```

1. **TODO-09:** Use the client object to call the API.
    * The `.prompt()` method can be used to set the prompt defined above.
    * The `.call()` method will make the call to the model.
    * The `.content()` method will return the content of the response.
    * Have the method return the content of the response.
	
    ```
    	return 
			client
				.prompt(prompt)
				.call()
				.content();
    ```

1. Organize your imports, save your work.


---
**Part 4 - Define the StockService**

The heart of the local function process will be the `StockService`.  This defines the local function which will be called by the client to complete the response returned from the model.  To make this work, it will require metadata to properly identify its form and purpose. 

15. Open `src/main/java/com.example.service.StockService`.

1. **TODO-11:** Annotate this class as a Spring bean using the `@Service` annotation.
    * Set its name to "stockService" to align with the callback function from the earlier step.
    * Use the `@Description` annotation to provide a helpful description for the OpenAI Client object.
    * Notice that the class implements Function, with very specific input and output types.

    ```
    @Service("stockService")
    @Description("Service to get stock information")	
    public class StockService 
        implements Function<
            com.example.service.StockService.Request, 
            com.example.service.StockService.Response> {
    ```

1.  **TODO-12:** Notice the pre-defined records for Request and Response.
    * These structures will be understood by the OpenAI client software.
    * Notice the apply() method, this will be invoked by the OpenAI client automatically when the response is returned from the server.
    * The code is not actually determining stock price or trading volume; it is simply returning fixed values (easier to test).
	
    ```
    public record Request(String symbol) {}
    public record Response(String symbol, Double price, Integer volume, String currency) {}

	public Response apply(Request request) {
		//	For now, just return a hard-coded response:
		return new Response(request.symbol(),price,volume, "USD");
	}    
    ```

---
**Part 5 - Create a `@Test` class**

Anything we code, we should test.  We will make a `@Test` class to ensure our Client object works as expected.

18. Open `src/test/java/com/example/client/OpenAIClientTests.java`.

1. **TODO-13:** Define this test class as a Spring Boot test.  

    ```
    @SpringBootTest
    public class OpenAIClientTests {
    ```

1. **TODO-14:** Use the `@Autowired` annotation to inject an instance of our AIClient.

    ```
    @Autowired AIClient client;
    ```

1. **TODO-15:** Define a `@Test` method to test the `callApi()` method of the client.
    * Pass in a string that describes the response you wish to generate, such as the provided "samplePrompt" String, or an equivalent.
    * Use AssertJ's `Assertions.assertThat()` method to ensure that the content is not null.
    * Use AssertJ's `Assertions.assertThat()` method to ensure that the content contains expected results.
    * Print the response string that is returned.

    ```
	@Test
	void testCallApi() {

        String response = client.callApi(samplePrompt);

		assertThat(response).isNotNull();
        assertThat(response).contains(sampleResults);

		//	Print the results
		System.out.println("The results of the call are: " + response);

    }
    ```

1. **TODO-16:** Organize imports and save all work.  Note that the `assertThat` functions are part of AssertJ.  You may need to manually add `import static org.assertj.core.api.Assertions.*;`.

1. Run this test, it should pass.  Notice the output contains specific data obtained from the StockService.  You should see a description of the output similar to this:

> The company summary is: NVIDIA Corporation (NVDA) is a technology company known for its graphics processing units (GPUs) and chipsets. The stock price for NVDA is $607.37 USD.

**Part 7 - Summary**
Our application now has an integration point with the OpenAI API.  Spring's `ChatClient` instructs the API that a local function exists to provide missing information.  The generated response is embellished by the client by calling the designated function. 