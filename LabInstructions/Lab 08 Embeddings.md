## Lab 08 - Embeddings and Semantic Search

In this exercise you will explore the concept of Embeddings and Semantic Search.

Within the codebase you will find ordered *TODO* comments that describe what actions to take for each step.  By finding all files containing *TODO*, and working on the steps in numeric order, it is possible to complete this exercise without looking at the instructions.  If you do need guidance, you can always look at these instructions for full information.  Just be sure to perform the *TODO* steps in order.

Solution code is provided for you in a separate folder, so you can compare your work if needed.  For maximum benefit, try to complete the lab without looking at the solution.

Let's jump in.

---
**Part 1 - Setup the Project**

The instructions below are for VS Code. If you wish to use IntelliJ or Eclipse and need assistance, see the "IDE Tips" document.

1. Open the _/student-files/lab08-embeddings_ folder.  

    The project should be free of compiler errors at this point.  Address any issues you see before continuing.

1. Find the TODO instructions.

    * **Using VS Code:** Select "Edit" / "Find in Files..." and search for "TODO".
    * **Using IntelliJ:**  Select "Edit" / "Find" / "Find in Files..." and search for "TODO".  Use the "Open in Find Window" button to place the results in a tab for easy navigation.
    * **Using Eclipse:** Select "Window" / "Show View" / "Tasks".  Use the "Search" view to search for "TODO".  Find a filter icon on the top of this tab.  Check "Show all items", "TODOs".  For scope select "on elements in the selected projects".  The list of TODOs will appear in order!

    IMPORTANT: Work through the TODO instructions in order!   


---
**Part 2 - Setup, Configuration, Model Access**

1. Open `pom.xml`.

1. **TODO-01:** Observe that the starters for OpenAI, Ollama, and Bedrock are present.	
    * The same lab code will be used if you are using any of these models.  We will ensure that only one of these models will be active at runtime.
    * If using Bedrock, make sure you have enabled the "Cohere" embedding model.
    * You do not need to make any changes here.

1. Open `src/main/java/com.example.Application.java`.

1. **TODO-02**  Run this application as a Spring Boot application.  We expect the application to start ... and then stop without errors.  Address any issues / errors before proceeding.

1. Open `src/main/resources/application.yml`.

1. **TODO-03**: The lab project is already setup with dependencies for Bedrock, Ollama, and OpenAI.  However, you may need to adjust the settings based on your own accounts / environments.  Adjust the settings to correspond to the model(s) you plan on using:

    1. If you plan to use **Amazon Bedrock**:
        * Adjust the region setting if needed.  Use your previous lab settings for guidance.
        * Make sure you have followed the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)** for AWS / Amazon Bedrock.
    1. If you plan to use **OpenAI**:
        * Adjust the model setting if needed.  Use your previous lab settings for guidance.
        * Make sure you have followed the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)** for OpenAI.
    1. If you plan to use **Ollama**:
        * Make sure you have followed the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)** for Ollama.
        * Adjust the base-url if needed.  Use your previous lab settings for guidance.
        * Make sure your Ollama Docker container is running.
        * For this lab we will use the **mxbai-embed-large** model.  Pull it if you have not already done so.  You can experiment with other embedding models if you like.



1. Save your work.

---
**Part 3 - Complete the `EmbeddingService`**

6. Open `src/main/java/com.example.service.EmbeddingService`.

1. **TODO-04:** Use a stereotype annotation to mark this class as a Spring bean.

    ```
    @Service
    public class EmbeddingService {
    ```

1. **TODO-05:**  Within the class, define a private final field named "model" of type `EmbeddingModel`.  Define a constructor which dependency injects this field:

    ```
    private final EmbeddingModel model;
    public EmbeddingService(EmbeddingModel model) {
        this.model = model;
    }
    ```
1.  **TODO-06:** Within the `findClosestMatch()` method, use the embedding model to turn our product descriptions into Embeddings.
    * Pass the incoming products list to the model's `embed()` method.
    * Use the response to populate the productEmbeddings List.
    * Notice that each "embedding" is an array of `float` primatives.

    ```
    productEmbeddings = model.embed(products);
    ```

1. **TODO-07:** Use similar logic to turn the incoming `query` String into a single embedding.
    * Pass the query string to the model's embed method.
    * Use the response to populate the queryEmbedding Array.
    * Notice that the embedding is an array of float primatives.

    ```
    queryEmbedding = model.embed(query);
    ```

1.  **TODO-08:** Find the product description most relevant to the query by performing a semantic search:
    * Call the `findColosestMatch()` method with the correct arguments.
    * Store the returned integer in the mostSimilarIndex variable.
    * If the returned index < 0, return "No similar product found"
    * Otherwise, get the item in the products list matching this index.

    ```
        int mostSimilarIndex = -1;
        mostSimilarIndex = findColosestMatch(queryEmbedding, productEmbeddings);
        
        if(mostSimilarIndex < 0) {
            return "No similar product found";
        } else {
            return products.get(mostSimilarIndex);
        }
    ```

    Note that this is a fairly primative search, relying on the product descriptions to be provided and converted into embeddings on every call - inefficient for large numbers.
    
1. **TODO-09:** Examine the logic in the `findColosestMatch()` method.  
    * There is nothing you need to change.
    * This method compares the query embedding to each product embedding to find the most similarity.
    * The Cosine similarity algorithm measures the similarity between two vectors.
    * The index of the product with the highest similarity is returned.

1.  Organize your imports and save your work.



---
**Part 4 - `@Test` the semantic search**

Anything that we code, we should test.  Our test will provide a List of product descriptions and a customer query to the semantic search.

14. Open to `src/test/java/com.example.service.EmbeddingServiceTests`.





---
**Part 3 - Chat Conversation Memory**

To implement interactive chat behavior when working with a foundational model, the `ChatClient` will be responsible for managing the state of the conversation, and managing different identifiers for the separate ongoing conversations

4. Open `src/test/java/com.example.client.AIClientImplTests`.

1. **TODO-02**: Alter the `@ActiveProfile` value to match the backing chat model you plan to use.  This will cause Spring to activate only a single `ChatModel` matching your selection:
    * For Amazon Bedrock models,  use **aws**.
    * For Azure-hosted OpenAI,    use **azure**.
    * For standard OpenAI,        use **openai**.
    * For Ollama,                 use **ollama**.

1. **TODO-03:** Examine the logic in the `@Test` method.  It conducts two separate conversations.  One conversation discusses the Great Lakes.  The other discusses planets.  Our goal is to enable our logic to track multiple conversations without getting confused.
    * Remove the `@Disabled` annotation from the `@Test` method.  
    * Run the test.  We expect the test to **FAIL**.  
        * The test fails because foundational models are stateless.  By default they do not associate any request with any other request.  Therefore, the request for "Which one is the deepest?" is not understood in the context of the earlier question.
    * We will enhance our interactions with stateful behavior in the next step.
    
1. Open `src/main/java/com.example.client.AIClientImpl`

1. **TODO-04:** Within the `AIClientImpl` class, define a member variable of type `InMemoryChatMemory`.  Initialize it with a `new InMemoryChatMemory()`.
    * This will store the history of each conversation.  A production application should use a more persistent form of storage able to be shared between instances.

    ```
    InMemoryChatMemory memory = new InMemoryChatMemory(); 
    ```
1. **TODO-05:** Defined a member variable of type `MessageChatMemoryAdvisor`.  Initialize it with a new `MessageChatMemoryAdvisor`   injected with the `InMemoryChatMemory` instance defined above. 

    ```
    MessageChatMemoryAdvisor advisor = new MessageChatMemoryAdvisor(memory);
    ```

1. **TODO-06:**  Within the constructor, alter the creation of the `ChatClient`.  Use the `.defaultAdvisors()` method to add the `MessageChatMemoryAdvisor` defined earlier:

    ```
    public AIClientImpl(ChatModel model) {
        client = ChatClient
            .builder(model)
            .defaultAdvisors(advisor)        
            .build();
    }
    ```

1. **TODO-07:**  Within the `conversationalChat()` method, alter the usage of the chat client to use the `.advisors()` method to inject an `AvisorSpec` Consumer to control conversational state.
    * The `advisors()` method will accept a Lambda expression that implements the Consumer.
    * The parameter to the Consumer is an `AdvisorSpec`.
    * Use the `AdvisorSpec`'s `.param()` method to add a parameter identifying the conversation.
        * The parameter key should be the `conversationKey` defined above.
        * The parameter value should be the `conversationId` passed to this method.

    ```
    client
        .prompt()
        .user(input)
        .advisors(a -> a.param(conversationKey, conversationId))
        .call()
        .content();
    ```

1.  Organize your imports and save your work.

1.  **TODO-08:**  Return to the `@Test` method in `AIClientImplTests`.  Re-run the test.  It should now pass.

---
**Part 4 - `@Test` the Semantic Search**

Anything that we code, we should test.  Our test will provide a List of product descriptions and a customer query to the semantic search.

14. Open to `src/test/java/com.example.service.EmbeddingServiceTests`.

1. **TODO-10:**  Define this test class as a Spring Boot test.  Add an `@ActiveProfiles` annotation to match the backing chat model you plan to use.  This will cause Spring to activate only a single `EmbeddingModel` matching your selection:
    * For Amazon Bedrock models,  use **aws**.
    * For standard OpenAI,        use **openai**.
    * For Ollama,                 use **ollama**.

    ```
    @SpringBootTest
    @ActiveProfiles("openai")
    // @ActiveProfiles("aws")
    // @ActiveProfiles("ollama")
    public class EmbeddingServiceTests {
    ```

1. **TODO-11:** Use the `@Autowired` annotation to inject an instance of our `EmbeddingService`.
    ```
    @Autowired EmbeddingService svc;
    ```

1. **TODO-12:** Define a test method to call the `findClosestMatch()` method of the service.
    * Use the `Utilities` class to provide the query and products inputs.
    * Capture the result in a `String` variable.
    * User AssertJ to assertThat the result startsWith "Wireless Headphones".
    * Print the result to the console:
    
    ```
    @Test
    public void testFindClosestMatch() {
        String result = svc.findClosestMatch(Utilities.query, Utilities.products);
        assertThat(result).startsWith("Wireless Headphones:");
        System.out.println(result);
    }
    ```

1. **TODO-13:** Organize your imports and save your work.  Run this test.  It should pass.
    * The product description for wireless headphones should be displayed.


---
**Part 5 - OPTIONAL - Use Spring AI's internal embedding model.**

As it happens, creating embeddings to represent semantic meanings is not a task that requires a call to a large, externally-hosted web service.  Spring AI includes its own `TransformersEmbeddingModel` which can provide embeddings without making an external call.

19.  **TODO-14**  On the test class, alter the `@ActiveProfiles` annotation to enable the **internal** profile.
    * Open application.yml and view the configuration of the "internal" profile, it simply enables SpringAI's `TransformersEmbeddingModel` bean.

1. Run the test again.  It should still pass, but this time no external model was used.

---
**Part 6 - Summary**

At this point, you have experienced the process of converting Strings into Embeddings, and using these to perform a semantic search.  Congratulations! 

