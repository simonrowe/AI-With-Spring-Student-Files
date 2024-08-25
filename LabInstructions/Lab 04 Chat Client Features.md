## Lab 4 - ChatClient Features

In this exercise you will explore some of the features available in SpringAI's `ChatClient`.

Within the codebase you will find ordered *TODO* comments that describe what actions to take for each step.  By finding all files containing *TODO*, and working on the steps in numeric order, it is possible to complete this exercise without looking at the instructions.  If you do need guidance, you can always look at these instructions for full information.  Just be sure to perform the *TODO* steps in order.

Solution code is provided for you in a separate folder, so you can compare your work if needed.  For maximum benefit, try to complete the lab without looking at the solution.

Let's jump in.

---
**Part 1 - Setup the Project**

The instructions below are for VS Code. If you wish to use IntelliJ or Eclipse and need assistance, see the "IDE Tips" document.

1. Open the _/student-files/lab04-chat-client-features_ folder.  
    * Give the VSCode a moment to initialize its workspace, especially if this is the first time importing a Gradle/Maven project.
    * If you see a message about enabling null analysis for the project, you can select either enable or disable.
    * If you see a message about installing _Extension Pack for Java_, take the install option.
    * If you see a message _Do you trust the authors of the files in this folder_, check the checkbox and click the "trust" button.

    The project should be free of compiler errors at this point.  Address any issues you see before continuing.

1. Find the TODO instructions.

    * **Using VS Code:** Select "Edit" / "Find in Files..." and search for "TODO".
    * **Using IntelliJ:**  Select "Edit" / "Find" / "Find in Files..." and search for "TODO".  Use the "Open in Find Window" button to place the results in a tab for easy navigation.
    * **Using Eclipse:** Select "Window" / "Show View" / "Tasks".  Use the "Search" view to search for "TODO".  Find a filter icon on the top of this tab.  Check "Show all items", "TODOs".  For scope select "on elements in the selected projects".  The list of TODOs will appear in order!

    IMPORTANT: Work through the TODO instructions in order!   


---
**Part 2 - Establish Model Access**

3. Open `src/main/resources/application.yml`.

1. **TODO-01**: The lab project is already setup with dependencies for Bedrock, Azure, Ollama, and OpenAI.  However, you may need to adjust the settings based on your own accounts / environments.  Adjust the settings to correspond to the model(s) you plan on using:

    1. If you plan to use **Amazon Bedrock**:
        * Adjust the region setting if needed.  Use your previous lab settings for guidance.
        * Make sure you have followed the **Lab Setup** document for AWS / Amazon Bedrock.
    1. If you plan to use **Azure OpenAI**:
        * Adjust the endpoint, deployment-name, and model settings as needed.  Use your previous lab settings for guidance.
        * Make sure you have followed the **Lab Setup** document for Azure.
    1. If you plan to use **OpenAI**:
        * Adjust the model setting if needed.  Use your previous lab settings for guidance.
        * Make sure you have followed the **Lab Setup** document for OpenAI.
    1. If you plan to use **Ollama**:
        * Adjust the base-url and model settings if needed.  Use your previous lab settings for guidance.
        * Make sure you have followed the **Lab Setup** document for Ollama.
        * Make sure your Ollama Docker container is running.

1. Save your work.

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
**Part 4 - Using Entities with `ChatClient`**

Sometimes it can be useful to receive a strongly-typed Java object in response to a foundational model request.  Spring AI's `ChatClient` supports the definition of a requested Entity.

14. Return to `src/test/java/com.example.client.AIClientImplTests`.

1. **TODO-10:** Examine the test logic in the `testRetrieve()` method.  It retrieves data about a US State (or any state).
    * Notice that the prompt contains only the name of the state.
    * Run the test.  It should pass. 
 
1. Open `src/main/java/com.example.client.StateData`.

1.  **TODO-11:** Examine the `StateData` record; it contains only two simple fields.
    * Add whatever additional fields you might like to know about a given state.  Examples might include: area in square miles, population, famous for, state bird, state flower, state motto, etc.

    ```
    public record StateData(
        String stateName, 
        String capitalCity, 
        int areaInSquareMiles, 
        int population, 
        String famousFor
        ) {}
    ```
1. Return to `src/test/java/com.example.client.AIClientImplTests`.

1. **TODO-12:** Add additional assertions to this method to verify that the entity object is populated with the extra fields you established in the last step.  For example:

    ```
    assertThat(stateData.capitalCity()).isEqualTo("Lansing");
    assertThat(stateData.areaInSquareMiles()).isBetween(95000, 98000);
    assertThat(stateData.population()).isBetween(10000000, 12000000);
    assertThat(stateData.famousFor()).contains("Great Lakes");
    ```

1. Run the test again, it should still pass.
    * Note that FMs may provide inaccurate or changing values for attributes like populate, square miles, etc.

Note that the prompt did not request the additional data fields.  The foundational model inferred that these were needed based on the data structure passed in the prompt by Spring AI.

---
**Part 5 - (Optional) Use Other Models**

If you would like to try this lab code with other models, you only need to adjust two points.

1. Return to **TODO-01** in `application.yml` and adjust the settings for the model you wish to use.  Refer to the **Lab Setup** guide as needed.
1. Return to `AIClientImplTests`.  Adjust the `@ActiveProfiles` annotation to activate the profile matching the model you wish to use.

You may observe different behavior in different models.  Nothing guarantees that different models will behave in deterministic or consistent ways.

---
**Part 6 - Summary**

At this point, you have used some of the features available in the `ChatClient`.  Congratulations! 

