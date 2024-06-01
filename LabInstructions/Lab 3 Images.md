## Lab 3 - Image Generation with OpenAI

In this exercise you will create a simple Spring Boot application which can use OpenAI to generate images.  We will need an account on OpenAI, then we will add code to a half-completed project structure.  Our code will create a client object for making image generation API calls, and as usual we will make sure we have test coverage.  Let's jump in.

---
**Part 1 - Signup Process for OpenAI**

If you have not already done so, setup an account with OpenAI.  The instructions are in the **Lab Setup** document.  When you are finished, you should have an environment variable established with your API key:

Windows you can run: 
```
setx SPRING_AI_OPENAI_API_KEY "YOUR-KEY-GOES-HERE"
```
On Linux or Mac you can run:
```
export SPRING_AI_OPENAI_API_KEY="YOUR-KEY-GOES-HERE"
```
* **SECURITY REMINDER:**  Do not store these keys in any file (such as application.properties or application.yml) that you may share publicly, such as on GitHub, BitBucket, Google Docs, etc.

---
**Part 2 - Setup the Project**

A half-completed skeleton project has been created for you to save you from going through the image generation steps.  The instructions below are for VS Code. If you wish to use IntilliJ or Eclipse and need assistance, see the "Lab Setup" document if you need help.

1. From within VS Code, use the File menu and select "Open Folder". Select the _/student-files/lab3-images-openai_ folder.  
    * See the **Lab Setup** document to address any issues when opening this project.

1. **TODO-01**: Open the **pom.xml** file.  Notice that the  `spring-ai-azure-openai-spring-boot-starter` dependency has already been added.

1. **TODO-02**: Open `src/main/resources/application.yml` and establish the following entries:
    ```
    spring:
      application.name: Lab3 Images OpenAI
      main.web-application-type: none     # Do not start a web server.

    ai:
      retry:
        max-attempts: 1           # Maximum number of retry attempts.
      openai:
        image:
          enabled: true
          options:
            n: 1                  # number of images to generate.
            response_format: URL  # or b64_json
            size: 1792x1024       # or 1024x1024, 1024x1792
            style: natural        # or vivid

    ```
    * SpringAI applications can run as web applications, but these exercises are built to avoid that extra step.
    * The retry* settings will override the `ChatClient`'s default settings.  You are likely to experience errors while you learn the API's usage, and we don't want you to experience unnecessary expenses.  
    * You can experiment with some of the image settings later.  Remember that larger, higher-quality images incur greater costs.  

1. **TODO-03** Open the main application class in `src/main/java/com/example/Application.java`.  Run the application. We expect the application to start, then stop, without errors.  If you have any errors related to tooling, be sure to address them now before proceeding.

---
**Part 3 - Build an Image Generation Client**

Now we can create a client object to make the image generation API calls. 

5. **TODO-04**: Open `src/main/java/com/example/client/OpenAIClient.java`. Establish the class as a Spring bean with the `@Component`annotation.  Make the bean a member of the **openai** profile:

    ```
    @Component
    @Profile("openai")
    public class OpenAIClient {
    ```

1. **TODO-05**: Define a private final field named "model" of type OpenAIImageModel.  Define a constructor which dependency injects this field:

    ```
    private final OpenAiImageModel model;

    public OpenAIClient(OpenAiImageModel model) {
        this.model = model;
    }
    ```

1. **TODO-06:** Within the `createImageUrl()` method, create a local variable reverencing a new ImagePrompt object.  Use the `request` parameter and `DEFAULT_OPTIONS` object when defining the prompt.  Later you can experiment by creating your own custom-defined `OpenAiImageOptions`.

    ```
    ImagePrompt prompt = new ImagePrompt(
        request,
        DEFAULT_OPTIONS
    );
    ```

1. **TODO-07:** Use the dependency injected `model` object to create an image.Define a local variable of type `ImageResponse`.  Populate it by calling `model.call()`, passing the prompt you've just created.

    ```
    ImageResponse response = model.call(prompt);
    ```

1. **TODO-08:** Find the `return null;` line at the bottom of the method and comment it out.  Find and return the URL String in the `ImageResponse` returned from the previous call.  The URL is nested within the `getResult()` and `getOutput()` methods.  

    ```
    return response.getResult().getOutput().getUrl();
    ```

1. Supply any imports needed to make the code compile.  Save your work.

---
**Part 4 - Create a `@Test` class**

Anything we code, we should test.  We will make a `@Test` class to ensure our Client object works as expected.

11. **TODO-09**: Open `src/test/java/com/example/client/OpenAIClientTests.java`. Use `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)` to define this test class as a Spring Boot test without a web environment.  Use the `@ActiveProfiles` annotation to set **openai** as the active profile.

    ```
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
    @ActiveProfiles("openai")
    public class OpenAIClientTests {
    ```

1. **TODO-10:** Use the @Autowired annotation to inject an instance of `OpenAIClient` into this test class.

    ```
    @Autowired OpenAIClient client;
    ```

1. **TODO-11:** Define a `@Test` method.  Have it call the `client.createImageUrl()` method of the client, passing in a string such as "Two golden retrievers playing tug-o-war in the snow." Use AssertJ's `Assertions.assertThat()` method to ensure that the returned URL is not blank.  Print the URL that is returned.

    ```
    @Test
    public void testCreateImageUrl() {
        String url = client.createImageUrl("Two golden retrievers playing tug-o-war in the snow.");
        Assertions.assertThat(url).isNotBlank();
        System.out.println("URL: " + url);
    }
    ```
1. Supply any imports needed to make the code compile.  Save your work. Run this test.  It should take a few moments to run, and it should pass.  Find the URL of the generated image and open it in a browser to see the result.

