## Lab 12 - SQL Generation 

One compelling generative AI use case is code generation.  The ability to generate SQL queries in response to user requests opens the door for rich possibilities.  

In this exercise you will complete an SQL generation application able to respond to natural language query requests.  We will use a foundational model to turn a user request into an SQL statement, and the SQL results into a text summary.

Within the project code, you will find comments containing **TODO** instructions.  These instructions match the order of instructions in this lab document.  If you find it easier, you can complete this lab by following the embedded **TODO** comments; just make sure you follow them in order.

Solution code is provided for you in a separate folder, so you can compare your work if needed.  For maximum benefit, try to complete the lab without looking at the solution.

Let's jump in.

---
**Part 1 - Setup the Project**

The instructions below are for VS Code. If you wish to use IntelliJ or Eclipse and need assistance, see the "IDE Tips" document.

1. Open the _/student-files/lab12-sql_ project folder.  

    The project should be free of compiler errors at this point.  Address any issues you see before continuing.

1. Find the TODO instructions.  Work through the TODO instructions in order!   

---
**Part 2 - Initial Configuration**

3. Open the **pom.xml** file.

1. **TODO-01**: Observe that the starters for OpenAI, Ollama, and Bedrock are present.	
    * The same lab code will be used if you are using any of these models.  We will ensure that only one of these models will be active at runtime.
    * If using Bedrock, make sure you have enabled the "Anthropic 3.5" _Claude 3.5 Sonnet_ model.
    * You do not need to make any changes here.

1.  Open the `src/main/resources/application.yml` file.  

1. **TODO-02:** You may need to adjust the settings based on your own accounts / environments, and the model(s) you plan on using:

    1. If you plan to use **Amazon Bedrock**:
        * Adjust the region setting if needed.  Use your previous lab settings for guidance.
        * Set spring.ai.model.chat to bedrock-converse to tell SpringAI which autoconfigure class to use.
        * Set spring.ai.bedrock.converse.chat.options.model to "anthropic.claude-3-5-sonnet-20240620-v1:0" or equivalent.  See the **[latest list](https://docs.aws.amazon.com/bedrock/latest/userguide/models-supported.html)**.
        * Make sure the Anthropic Claude 3.5 Sonnet model is enabled.  Refer to the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)** for AWS / Amazon Bedrock.
        
    1. If you plan to use **OpenAI**:
        * Set spring.ai.model.chat to openai to tell SpringAI which autoconfigure class to use.
        * Adjust the model if desired or take the default.  See the **[latest list](https://platform.openai.com/docs/models/embeddings)**.
        * Make sure you have followed the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)** for OpenAI.

        
    1. If you plan to use **Azure OpenAI**:
        * Set spring.ai.model.chat to azure-openai to tell SpringAI which autoconfigure class to use.
        * Set spring.ai.azure.openai.endpoint to the value you established during Azure setup.
        * Set spring.ai.azure.openai.chat.options.deployment-name to the value you establised during setup.
        * Set spring.ai.azure.openai.chat.options.model to "gpt-35-turbo", or whichever model you have enabled.
        * Make sure you have followed the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)** for Azure.


    1. If you plan to use **Ollama**:
        * Set spring.ai.model.chat to ollama to tell SpringAI which autoconfigure class to use.
        * Make sure you have followed the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)** for Ollama.
        * Adjust the base-url if needed.  Use your previous lab settings for guidance.
        * Adjust the spring.ai.ollama.chat.options.model if desired.  See the **[list of models](https://ollama.com/library?sort=popular)**
        * Make sure your Ollama Docker container is running.

1.  Save your work. 

1.  Open `src/main/resources/schema.sql`.

1.  **TODO-03:** Notice the SQL schema file.
    * This file defines the structure of our database.
    * Spring Boot will create an in-memory database and populate it with this schema.  The `data.sql` file will provide test data.
    * This schema information will be provided to the model to assist with SQL statement generation.

---
**Part 4 - Build the `AIClient`**

Now we can build a component which will use the `ChatClient` to generate SQL and summarize the returned results.

10. **TODO-04**:  Open `src/main/java/com/example/client/AIClient.java`.  
    - Use a stereotype annotation to mark this class as a Spring bean.  

    ```
    @Component
    public class AIClient {
    ```

1. **TODO-05**: Dependency inject the `ChatModel` bean.
    * The `ChatModel` will be autoconfigured for us based on the dependencies and enablement settings above.

    ```
        @Autowired ChatModel model;
    ```

---
**Part 5 - Build the `generateSql()` method.**

The first method to implement will need to generate an SQL statement based on user input and the database schema.

12. **TODO-06:** Observe the system message that will be provided to the model.
    * It provides direct instructions for the model to generate SQL queries.
    * It directs the model to place generated SQL within \<SQL> and \</SQL> tags.
    * The database schema will be provided within the message.
    * The schema file will be read by a private method and injected into this message.

    ```
    String systemMessage = 
    """
    You are an SQL generating web service.
    Responses must be valid, HyperSQL-compatible, executable SQL statements.  
    HyperSQL uses DATE_ADD ( xxxx, INTERVAL X DAY ) for date arithmetic, and CURRENT_DATE to get today's date.
    The SQL statement must be placed between <SQL> and </SQL> tags.
    Do not include any other superflous text in the response.
    Use the following database schema to generate SQL queries: %s
    """;        
    ```

1. **TODO-07:** Create a chatClient.
    *   Pass the model to the ChatClient.builder to build a ChatClient object.
    *   Use .defaultSystem() to set the system-level prompt to "fullSystemPrompt" defined above.

    ```
        ChatClient client =
            ChatClient.builder(model)
                .defaultSystem(fullSystemPrompt)
                .build();
    ```

1. **TODO-08:** Use the client object to call the foundational model.
    * The .prompt().user() method can be used to set the user-level prompt from the input parameter.
    * The .call() method will make the call to the model.
    * The .content() method will return the content of the response.
    * Capture the response in a String variable.
    * Pass the String response to the extractSql() method and return the results.
        * This method extracts the SQL statement found within the \<SQL> and \</SQL> tags. 

    ```
    String response =  
        client
            .prompt().user(input)
            .call()
            .content();

    return extractSql(response);
    ```

---
**Part 6 - Build the `summarize()` method.**

The second method will generate an executive summary for the user prompt based on the raw data returned from the database.

15. **TODO-09:** Within the `summary()` method, observe the system message.  It provides direct instructions for the model to produce executive summaries.

    ```
    String systemMessage =
        "You are a web service which specializes in executive summaries.";
    ```

1. **TODO-10: Create a chatClient.
    * Pass the model to the `ChatClient.builder` to build a `ChatClient` object.
    * Use .defaultSystem() to set the system-level prompt to "systemMessage" defined above.

    ```
    ChatClient client =
        ChatClient.builder(model)
            .defaultSystem(systemMessage)
                .build();
    ```

1. **TODO-11:** Use the `client` object to call the foundational model.
    * The .prompt().user() method can be used to set the "fullUserMessage" defined earlier.
        * This variable combines the user-provided query with the raw data supplied by the earlier query.
    * The .call() method will make the call to the model.
    * The .content() method will return the content of the response.
    * Return the response.

    ```
        String response = client
            .prompt().user(fullUserMessage)
            .call()
            .content();

        System.out.println(response);
        return response;
    ```

1. Organize your imports.  Save your work.

---
**Part 7 - Implement the `ProductService`.**

19. Open `src/main/java/com.example.service.ProductService.java`.

1. **TODO-12:** Implement the `productQuery()` method.  Begin by generating SQL based on the user-provided query String.
    * Call the `AIClient.generateSql()` method which you completed earlier.
    * Pass the user-provided query parameter to the `generateSql()` method.
    * Capture the return value in a String.

    ```
    String sql = aiClient.generateSql(userQuery);
    ```

1. **TODO-13:** Call the `ProductDao.adHocQuery()` to execute the SQL query.
    * Pass the SQL query generated from the last method.
    * Capture the results in a List<Map<String,Object>> variable.
        * Spring's `JdbcTemplate.queryForList()` method called within the `ProductDao` returns a List structure where each result set row is represented by a Map structure.

    ```
    List<Map<String,Object>> results = productDao.adHocQuery(sql);
    ```

1. **TODO-14:** Call the `AIClient` once more, this time to summarize the results.
    * Call the `summarize()` method you implemented earlier.
    * Pass 1) the original user-provided query parameter and 2) the results from the previous call, converted to String.
    * Return the results of the summarize() method.
    * Remove the temporary "return null;" statement. 
    ```
    return aiClient.summarize(userQuery, results.toString());
    //return null;
    ```

---
**Part 8 - Create a `@Test` class**

Anything we code, we should test.  We will make a `@Test` class to ensure our Client object works as expected.

23. Open `src/test/java/com/example/service/ProductServiceTests.java`.  

1. **TODO-15** Add an annotation to define the class as a Spring Boot test.  Annotate the class with the `@ActiveProfiles` annotation to activate the **aws** profile.

    ```
    @SpringBootTest
    @ActiveProfiles("aws")
    public class ProductServiceTests {
    ```

1. **TODO-16:** Use the `@Autowired` annotation to inject an instance of the `ProductService`.
    ```
    @Autowired ProductService productService;
    ```

27. **TODO-17:** Define a `productQueryTest()` `@Test` method to test the `productQuery()` method of the productService.
    * A "samplePrompt" String has been provided as example test data to be provided to as the user-defined query.
    * Use AssertJ's `Assertions.assertThat()` method to ensure that the content is not null.
    * Use AssertJ's `Assertions.assertThat().contains()` method to ensure that the content contains some expected results.  Use the "sampleResults" array as an example.
    //  Print the response List that is returned.

    ```
    @Test
    void productQueryTest() {
        String response =
            productService.productQuery(samplePrompt);

        assertThat(response).isNotNull();

        //  Print the results
        System.out.println("Results: " + response);

        assertThat(response).contains(sampleResults);
    }
    ```

1. **TODO-18:** Organize all imports. Save all work.  Run the test.  It should pass.
    * Note: There is a good possibility that the test may fail.  The behavior of the foundational models is not deterministic. If an error occurs, experiment with the various prompts to try to get the expected results.

**Part 9 - Summary**

At this point, you have used generative AI to:
1. Generate SQL specific to a user query and database schema 
2. Summarize the results 

Congratulations!

The ability to utilize generative AI to generate SQL and summarize the results of a query is a compelling use case.  You can probably imagine other scenarios where generated code can be applied.  Just be aware that the generated code is not guaranteed to be accurate or safe in all cases.  

