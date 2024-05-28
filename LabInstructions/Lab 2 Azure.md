## Lab 2 - Azure OpenAI

In this exercise you will create a simple Spring Boot application which can make calls to Azure OpenAI.  There is quite a bit of work to establish an Azure account, obtain permission to use Azure Open AI, create resources and deployments, etc.  Once we start writing our Spring Boot application, the hard part will behind us.  Let's jump in.

---
**Part 1 - Signup Process for Azure OpenAI**

NOTE:  The AI industry changes rapidly.  The instructions here were valid as of June 2024.  You may find the screens and terminology have updated since these instructions were recorded.

Microsoft Azure provides the means to host OpenAI models within an Azure account.  This allows easier access from within Azure-hosted workloads.  SpringAI's client will use slightly different parameters to connect to OpenAI models hosted in Azure.

1. **Setup an Azure account**:  Go to https://portal.azure.com/#home (If you already have an account, you can skip to the next steps.)

- The signup process typically asks your a few questions on what you plan to do with Azure.  This is mainly to customize your experience and it is not necessary to answer these with precision.  We used "Use AI and ML to add intelligent features to apps".
- You may have to enter a payment method if you do not qualify for a trial.  The labs in this course should cost less than $5.
- You may be taken on a short tour of the Azure portal.

2. **Signup for Azure AI Services**: From the Azure main console, find the search field on the top.  Type "Azure AI Services".

* At present, even if you have an Azure account, you must specifically enable the use of Azure OpenAI: https://customervoice.microsoft.com/Pages/ResponsePage.aspx?id=v4j5cvGGr0GRqy180BHbR7en2Ais5pxKtso_Pz4b1_xUNTZBNzRKNlVQSFhZMU9aV09EVzYxWFdORCQlQCN0PWcu .  This requires a work email address (not a personal email address) and a subscription.  
* It can take over 24 hours to receive a response.

3. **Create an Azure OpenAI _resource_**: Once you have an Azure account and Azure OpenAI is enabled, create an Azure OpenAI resource:

    1.  From the Azure main console, search for "Azure AI Services".
    1. From the Azure AI Services page, select "Azure OpenAI".
    1. Click "Create Azure OpenAI".
    1. Select any subscription, resource group, and region you like.
    1. Resource names must be unique.  I suggest creating one based on your full name plus a unique number.  
    1. Select the standard pricing tier.  If you have more than one option you may explore which one is the best deal for your situation.
    1. Click "Next"
    1. For network, select "All Networks..." for this exercise.  In a production situation we would usually restrict this to a private network on our Azure account.
    1. Next.
    1. You may leave the tags empty.  Next.
    1. Take a moment to review terms, and click Create.
    1. Wait a moment for resource creation to complete.  Click "Go to Resource" when it appears.

1. **Obtain endpoint and keys**:

    1. From the Azure main console, find the search field on the top.  Type "Azure AI Services".
    1. From the Azure AI Services page, select "Azure OpenAI".
    1. From the selection list, choose the resource you just created.
    1. From the resource details page, select "click here to view endpoints". 
    1. Click "Show Keys".  Record the values for key 1, key 2, and endpoint in a temporary file.  **CRITICAL: DO NOT STORE THE KEY IN A PUBLIC REPOSITORY** 

1. Set an environment variable named `SPRING_AI_AZURE_OPENAI_API_KEY` using this value.  On Windows you can run: 
```
setx SPRING_AI_AZURE_OPENAI_API_KEY "KEY-GOES-HERE"
```
On Linux or Mac you can run:
```
export SPRING_AI_AZURE_OPENAI_API_KEY="KEY-GOES-HERE"
```
- We can also set the endpoint (and deployment name) using environment variables, but they are less sensitive.  We will set these later within application.yml.

6. **Create a _Deployment_**: Open https://oai.azure.com/portal in a new browser tab.  Go to Management / Deployments / Create new deployment.
    1. Select a model to use.  _gpt-35-turbo_ has the lowest price at the time of this writing.
    1.  Choose the default version and standard deployment type.
    1.  Provide a name for the deployment.  We suggest using the same name as the "resource" defined earlier.  Record the name, you will need it later.
    1. Adjust advanced options if you like. Create. 

Note:  Azure OpenAI pricing is available at https://azure.microsoft.com/en-us/pricing/details/cognitive-services/openai-service/ .  Pricing is based on input and output tokens, and varies depending on the model chosen.  Tokens currently cost between $0.0005 and $0.06 per thousand input tokens, $0.002 and $0.12 per thousand output tokens.

---
**Part 2 - Generate the Project Structure**

5.  Use [https://start.spring.io](https://start.spring.io), create a new Spring Boot Project.
  - Use either Maven or Gradle - Groovy (if you have it installed).  All lab instructions are based on Maven.
  - Use the latest stable releases of Boot and Java.  These instructions were originally tested with Java 1.21, Boot 3.2.5.  The version can be changed after the project is generated.
  - Use JAR packaging.
  - For project metadata, adjust the values as you like.  These instructions will assume an artifact and name of “lab2-azure”.
  - Adjust the package to `com.example` for simplicity.  Feel free to re-arrange the package structure later, but the remaining instructions will assume the former.
  - Search for and select the `Azure OpenAI` dependency. (Do **NOT** select "OpenAI" for this lab!)
6. Generate.  Find the downloaded zip and expand it.  Copy the `lab2-azure` project to wherever you downloaded your labfiles to.
---
**Part 3 - Import into your IDE**

The project structure generated by Spring Initializr follows a Maven-type generic pattern and can be imported into various IDEs. You can use either VS Code (assuming the appropriate extensions are installed, see "Lab Setup"), IntelliJ, or Eclipse. 

The instructions below are for VS Code. If you wish to use IntilliJ or Eclipse and need assistance, see the "Lab Setup" document.

7. Open VS Code.

8. Open the project folder by using the File menu and select "Open Folder". Select the folder where you copied the files.
* Give VS Code a moment to initialize its workspace, especially if this is the first time importing a Gradle/Maven project.
* If you see a message about enabling null analysis for the project, you can select either enable or disable.
* If you see a message about installing _Extension Pack for Java_, take the install option.
* If you see a message _Do you trust the authors of the files in this folder_, check the checkbox and click the "trust" button..

9. ***VS Code should*** recognize the project as a Maven or Gradle project and automatically import it. If it does not and you are using Gradle, you may need to manually import it:

* Open the Command Palette (Ctrl+Shift+P or Cmd+Shift+P on macOS).
* Type "Gradle: Import Gradle Project" and select it.
* Navigate to the project directory and select the build.gradle file.
* Click "Open".

10. If you get an error such as “invalid source release ” you may need to lower the Java version specified in your build.gradle file. 

* Open build.gradle
* Find the section on java / sourceCompatibility.
* Lower the Java version number. For example, if 21 is too high, try 17.

The project should be free of compiler errors at this point.  Address any issues you see before continuing.

---
**Part 4 - Basic Configuration and Test**

At this point, let's take a moment to ensure that everything we have created so far is error free.

11.  Within your IDE, find the `src/main/resources/application.properties`.  Rename this file as `src/main/resources/application.yml`.
12.  Establish the following entries:
```
spring:
  application.name: Lab2 Azure
  main.web-application-type: none     # Do not start a web server.

  ai:
    azure:
      openai:
        endpoint: ENDPOINT-GOES-HERE
        chat.options.model: DEPLOYMENT-NAME-GOES-HERE
    retry:
      max-attempts: 1           # Maximum number of retry attempts.
      on-client-errors: false   # If false, throw a NonTransientAiException, and do not attempt retry for 4xx client error codes.
```
- Note: The retry* settings will override the `ChatClient`'s default settings.  You are likely to experience errors while you learn the API's usage, and we don't want you to experience unnecessary expenses.
13.  Save your work.  

14.  Find the main application class in `src/main/java/com/example`.  It is probably named `Lab2AzureApplication.java` if you have followed theses instructions (We usually rename ours to `Application.java`). Run the application.
* **VS Code**: Right-click, Run Java.  Or open the file and click the “Run” option hovering over the main() method.
* **IntelliJ**: Right-click, select “Run ‘Application.main()’”. 
* **Eclipse**: Right-click, Select Run As / Java Application.

* We expect the application to start, then stop, without errors.  If you have any errors related to tooling, be sure to address them now before proceeding.

---
**Part 5 - Try Spring AI's `ChatClient`**

At this point we should be able to try using the ChatClient to make API calls to Azure OpenAI.

15. Create a new  **client**  folder under `src/main/java/com/example`.
1. Within this package create a new Java file called `AzureClient.java`.
* The IDE should create an empty Java class definition for you.
17. Add the following annotations at the class level to make this object a Spring Bean and to only activate it when the "openai" profile is active:
```
@Component
@Profile("azure")
public class OpenAIClient {
``` 
- Note: the `@Profile` annotation we be useful later when we want our application to switch between OpenAI, Azure, Ollama, etc.
18. Add code to automatically provide a reference to the `ChatClient`.  Spring Boot automatically creates this when the Spring AI dependencies are on the classpath:
```
	@Autowired
	ChatClient chatClient;
```
19. Add a `callModel` method.  Define a String parameter for the prompt:  
```
    public String callModel(String prompt ) {

		ChatResponse response = chatClient.call(
			new Prompt(prompt,
            	AzureOpenAiChatOptions.builder().build()
				));

        return response.getResults().get(0).getOutput().getContent();
    }
````
- Use the `chatClient` to call the API.  Pass the given prompt as well as an `Options` object.  We can use this to define parameters such as model or temperature if we like, but here we are using a default.
- The `ChatResponse` is a bit complex.  For this simple example we expect it to contain a single `Generation` object containing an `AssistantMessage` object.  Within this object we will find a JSON object containing our desired response.
20. Supply any imports needed to make the code compile.
* **VS Code**: Type Alt-Shift-O.
* **IntelliJ**: Type Ctrl-Alt-O.
* **Eclipse**: Type Ctrl-Shift-O.
21. Save your work.


---
**Part 6 - Create a `@Test` class**

Anything we code, we should test.  We will make a `@Test` class to ensure our Client object works as expected.

22. Create a new  **client**  folder under `src/test/java/com/example`.  Within this package create a new Java file called `AzureClientTests.java`.

23. Alter the test class to include the `@SpringBootTest` annotation. Tell boot to run as a non-web application and activate the 'azure' profile like this:
```
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("azure")
public class AzureClientTests {
    //...
```	
24. Add code to automatically provide a reference to the `AzureClient`:
```
  @Autowired AzureClient client;
```
Add a `@Test` method to use the `client` to make an example API call:
```
	@Test
	void quickChat() {

        String response = 
            client.callModel(
                "Generate the names of the five great lakes.  Produce JSON output.");

		Assertions.assertThat(response).isNotNull();

		//	Print the results
		System.out.println("The results of the call are: " + response);
    }
```
25. Supply any imports needed to make you code compile.
* **VS Code**: Type Alt-Shift-O.
* **IntelliJ**: Type Ctrl-Alt-O.
* **Eclipse**: Type Ctrl-Shift-O.

26.  Save your work. Run the test.
* **VS Code**: In the "explorer" view on the left, Right-click on the class, Select "Run Tests".  Or, find the green triangle in the editor’s “gutter”. Click on this to run either an individual test method or all tests in the class.
* **IntelliJ**: Right-click on the class. Select Run OpenAIClientTests.
* **Eclipse**: Right-click on the class. Select Run As / Junit Test.

The test should run and produce a list of the five Great Lakes.  It is also very possible that you will encounter an error here, possibly due to setup, but also due to changes in Azure OpenAPI since the time of this writing.  Here are some troubleshooting tips.
* If you have a compilation issue, be sure you have organized imports.  Compare your code to the solution code.
* A common error is '"401","message":"Access denied due to invalid subscription key or wrong API endpoint..."'. Double-check that you have retrieved the correct values for _key_ and _endpoint_ from the Azure resource details page.

**Part 7 - Summary**
At this point, you have integrated with Azure's OpenAI hosted models from your own Spring Boot application.  If your were running a Spring Boot application on an Azure VM within a private network, this would be a natural combination to use rather than calling OpenAI directly.  Congratulations! 


