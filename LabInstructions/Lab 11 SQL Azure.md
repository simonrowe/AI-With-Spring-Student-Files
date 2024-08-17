## Lab 11 - SQL Generation - Azure

One compelling generative AI use case is code generation.  The ability to generate SQL queries in response to user requests opens the door for rich possibilities.  

In this exercise you will complete an SQL generation application able to respond to natural language query requests.  We will use Azure-hosted OpenAI models to turn a user request into an SQL statement, and the SQL results into a text summary.

Within the project code, you will find comments containing **TODO** instructions.  These instructions match the order of instructions in this lab document.  If you find it easier, you can complete this lab by following the embedded **TODO** comments; just make sure you follow them in order.

Solution code is provided for you in a separate folder, so you can compare your work if needed.  For maximum benefit, try to complete the lab without looking at the solution.

Let's jump in.

---
**Part 1 - Setup the Project**

The instructions below are for VS Code. If you wish to use IntelliJ or Eclipse and need assistance, see the "IDE Tips" document.

1. Open the _/student-files/lab11-sql-azure_ project folder.  

    The project should be free of compiler errors at this point.  Address any issues you see before continuing.

1. Find the TODO instructions.  Work through the TODO instructions in order!   

---
**Part 1 - Setup Azure Deployment**

If you have not already done so, Establish an Azure account.  Follow the instructions in the **Lab Setup guide** and find the _Signup Process for Azure OpenAI_ section.  Walk through these instructions to establish an Azure Account, OpenAI _resource_, Endpoint, Keys, and Deployment. 

---
**Part 3 - Initial Configuration**

4. Open the **pom.xml** file.

5. **TODO-01**: Notice that the dependency for groupId `org.springframework.ai` artifactId `spring-ai-azure-openai-spring-boot-starter` is already present
    * You do not need to make any changes here.
```
<dependency>
	<groupId>org.springframework.ai</groupId>
	<artifactId>spring-ai-azure-openai-spring-boot-starter</artifactId>
</dependency>
```

6.  Open the `src/main/resources/application.yml` file.  

1.  **TODO-02**: Establish the following configuration entries:
    - Set `spring.application.name` to "Lab11 SQL Generation with Azure" or something similar.
    - SpringAI applications can run as part of a web application, but these exercises are built to avoid that extra step.
    - Adjust the retry settings to fail fast.  
    - A _client error_ indicates a problem with our request; there is typically no point in retrying such a request.
    - Set the `spring.ai.azure.openai.endpoint` to the value you established during Azure setup.
    - Set the `spring.ai.azure.openai.chat.enabled` to true to enable the chat model.
    - Set the `spring.ai.azure.openai.chat.options`.deployment-name to the value you established during setup.
    - Set the `spring.ai.azure.openai.chat.options.model` to "gpt-35-turbo" to use the GPT-3.5 model.


```  
spring:
  application.name: Lab11 SQL Generation with Azure
  main.web-application-type: none     # Do not start a web server.
  ai:
    retry:
      max-attempts: 1           # Maximum number of retry attempts.
      on-client-errors: false   # If false, throw a NonTransientAiException, and do not attempt retry for 4xx client error codes.
    azure:
      openai:
        api_key: NEVER-PLACE-SECRET-KEY-IN-CONFIG-FILE
        endpoint: ENDPOINT-GOES-HERE
        chat:
          enabled: true
          options:
            deployment-name: DEPLOYMENT-NAME-GOES-HERE
            model: gpt-35-turbo        
```

8.  Save your work. 

1.  Open `src/main/resources/schema.sql`.

1.  **TODO-03:** Notice the SQL schema file.
    * This file defines the structure of our database.
    * Spring Boot will create an in-memory database and populate it with this schema.  The `data.sql` file will provide test data.
    * This schema information will be provided to the model to assist with SQL statement generation.

---
**Part 4 - Build the `AIClient`**

Now we can build a component which will use the `ChatClient` to generate SQL and summarize the returned results.

8. **TODO-04**:  Open `src/main/java/com/example/client/AIClient.java`.  
    - Use a stereotype annotation to mark this class as a Spring bean.  

```
@Component
public class AIClient {
```

9. **TODO-05**: Dependency inject the `ChatModel` bean.
    * The `ChatModel` will be autoconfigured for us based on the dependencies and enablement settings above.

```
    @Autowired ChatModel model;
```

---
**Part 5 - Build the `generateSql()` method.**

The first method to implement will need to generate an SQL statement based on user input and the database schema.

10. **TODO-06:** Observe the system message that will be provided to the model.
    * It provides direct instructions for the model to generate SQL queries.
    * It directs the model to place generated SQL within \<SQL-START> and \<SQL-END> tags.
    * The database schema will be provided within the message.
    * The schema file will be read by a private method and injected into this message.

```
String systemMessage = 
"""
You are an SQL generating web service.
Responses must be valid, HyperSQL-compatible, executable SQL statements.  
The SQL statement must be placed between <SQL-START> and <SQL-END> tags.
Use the following database schema to generate SQL queries: %s
""";        
```

11. **TODO-07:** Create a chatClient.
    *   Pass the model to the ChatClient.builder to build a ChatClient object.
    *   Use .defaultSystem() to set the system-level prompt to "fullSystemPrompt" defined above.

```
        ChatClient client =
            ChatClient.builder(model)
                .defaultSystem(fullSystemPrompt)
                .build();
```

12. **TODO-08:** Use the client object to call the foundational model.
    * The .prompt().user() method can be used to set the user-level prompt from the input parameter.
    * The .call() method will make the call to the model.
    * The .content() method will return the content of the response.
    * Capture the response in a String variable.
    * Pass the String response to the extractSql() method and return the results.
        * This method extracts the SQL statement found within the \<SQL-START> and \<SQL-END> tags. 

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

13. **TODO-09:** Within the `summary()` method, observe the system message.  It provides direct instructions for the model to produce executive summaries.

```
    String systemMessage =
        "You are a web service which specializes in executive summaries.";
```

14. **TODO-10: Create a chatClient.
    * Pass the model to the `ChatClient.builder` to build a `ChatClient` object.
    * Use .defaultSystem() to set the system-level prompt to "systemMessage" defined above.

```
    ChatClient client =
        ChatClient.builder(model)
            .defaultSystem(String.format(systemMessage))
                .build();
```

15. **TODO-11:** Use the `client` object to call the foundational model.
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

16. Organize your imports.  Save your work.

---
**Part 7 - Implement the `ProductService`.**

17. Open `src/main/java/com.example.service.ProductService.java`.

18. **TODO-12:** Implement the `productQuery()` method.  Begin by generating SQL based on the user-provided query String.
    * Call the `AIClient.generateSql()` method which you completed earlier.
    * Pass the user-provided query parameter to the `generateSql()` method.
    * Capture the return value in a String.

```
    String sql = aiClient.generateSql(userQuery);
```

19. **TODO-13:** Call the `ProductDao.adHocQuery()` to execute the SQL query.
    * Pass the SQL query generated from the last method.
    * Capture the results in a List<Map<String,Object>> variable.
        * Spring's `JdbcTemplate.queryForList()` method called within the `ProductDao` returns a List structure where each result set row is represented by a Map structure.

```
    List<Map<String,Object>> results = productDao.adHocQuery(sql);
```

20. **TODO-14:** Call the `AIClient` once more, this time to summarize the results.
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

21. Open `src/test/java/com/example/service/ProductServiceTests.java`.  

22. **TODO-15** Add an annotation to define the class as a Spring Boot test.  Annotate the class with the `@ActiveProfiles` annotation to activate the **azure** profile.

```
@SpringBootTest
@ActiveProfiles("azure")
public class ProductServiceTests {
```

23. **TODO-16:** Use the `@Autowired` annotation to inject an instance of the `ProductService`.
```
    @Autowired ProductService productService;
```

24. **TODO-17:** Define a `productQueryTest()` `@Test` method to test the `productQuery()` method of the productService.
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

25. **TODO-18:** Organize all imports. Save all work.  Run the test.  It should pass.
    * Note: There is a good possibility that the test may fail.  The behavior of the foundational models is not deterministic. If an error occurs, experiment with the various prompts to try to get the expected results.

**Part 9 - Summary**

At this point, you have used generative AI to:
    1) generate SQL specific to a user query and database schema 2) summarize the results 
Congratulations!

The ability to utilize generative AI to generate SQL and summarize the results of a query is a compelling use case.  You can probably imagine other scenarios where generated code can be applied.  Just be aware that the generated code is not guaranteed to be accurate or safe in all cases.  

