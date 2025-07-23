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
        * Set the spring.ai.model.embedding to `bedrock-cohere`
        * Adjust the region setting if needed.  Use your previous lab settings for guidance.
        * Make sure you have followed the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)** for AWS / Amazon Bedrock.
    1. If you plan to use **OpenAI**:
        * Set the spring.ai.model.embedding to `openai`
        * Adjust the model setting if needed.  Use your previous lab settings for guidance.
        * Make sure you have followed the **[Lab Setup guide](https://github.com/kennyk65/AI-With-Spring-Student-Files/blob/main/LabInstructions/Lab%20Setup.md)** for OpenAI.
    1. If you plan to use **Ollama**:
        * Set the spring.ai.model.embedding to `ollama`
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

