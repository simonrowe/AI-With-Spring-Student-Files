## Lab 2 - AWS Bedrock

In this exercise you will create a simple Spring Boot application which can make calls to Amazon Bedrock.  Bedrock is a managed service which gives us access to many different text and image generation [models](https://docs.aws.amazon.com/bedrock/latest/userguide/model-ids.html#model-ids-arns).  The first thing we will need to do is create an AWS account (if we don't have one) and obtain AWS credentials (access key ID and secret key).  Once we start writing our Spring Boot application, the hard part will behind us.  Let's jump in.

---
**Part 1 - Obtain an AWS Account**

Note that the exact screen flow to perform these steps may vary over time as AWS modifies their user interface.

1. If you already have an AWS account for personal use - and AWS credentials - you can skip this section.  If you have access to an account through your employer, you _may_ be able to use it, but you could find that you have insufficient permission to use the services described in this lab.  We recommend having your own account under your complete control.

1. Go to https://aws.amazon.com/ and follow the process to create an AWS account.  There are many resources that can help guide you through this process such as [this video](https://www.youtube.com/watch?v=xi-JDeceLeI).
    - Record your 12-digit account number. You will need it later when signing into the console.  If you have an opportunity to create an account 'alias', we recommend it.
    - AWS Accounts require a payment method (credit card) be established for billing. The charges associated with this lab should be extremely small, even if you run the lab dozens of times experimenting with multiple models.  

3. The account you have just created gives you access to "root" permissions which cannot be limited.  AWS strongly recommends avoiding root credentials for day-to-day use.  Instead create an "IAM user" within this account.  [This video](https://www.youtube.com/watch?v=ubrE4xq9_9c) provides a good guide.   
    - Ignore recommendations to use Identity Center.  It provides a more proper way to manage identities in your AWS account, but it is tangental to our purposes here.
    - Give your user the `AmazonBedrockFullAccess` policy.  This follows the _grant least privilege_ security approach to allow only what is needed for this lab.
    - Be sure to "Download .csv file" containing the user's password.  You may need this later to sign into the console.
4. Select the new user from the user list and access the security credentials.  Create an access key.  Choose any use case, but ignore the recommended alternatives.  Be sure to download the resulting CSV file; this contains the Access Key ID and Secret access key "credentials" that we need.  
- If you loose this file don't worry; just repeat the steps to create a new key.
- On AWS, don't confuse Access Key ID / Secret access key with user name and password.  The former are "credentials" used for programatic access, the latter is only for human access to the web interface    

---
**Part 2 - Set Credentials**

Next, provide the access key ID and secret key to our software.  

AWS recommends (and we agree) that the best way to provide credentials to our code is IAM Roles and the [EC2 Instance Metadata service](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/configuring-instance-metadata-service.html), or the local `~/.aws/credentials file`.  However, to keep things simple and consistent with other labs, we will use environment variables.

4. Set the following environment variables using the values obtained from the CSV file.  On Windows you can run: 
    ```
    setx SPRING_AI_BEDROCK_AWS_ACCESS_KEY "YOUR_ACCESS_KEY"
    setx SPRING_AI_BEDROCK_AWS_SECRET_KEY "YOUR_SECRET_KEY"
    ```
    On Linux or Mac you can run:   
    ```
    export SPRING_AI_BEDROCK_AWS_ACCESS_KEY="YOUR_ACCESS_KEY"
    export SPRING_AI_BEDROCK_AWS_SECRET_KEY="YOUR_SECRET_KEY"
    ```

---
**Part 3 - Enable Bedrock Models**

When using Amazon Bedrock, you must _enable_ the models you wish to use. This is easily done via the AWS Management Console.

Note that the exact steps may vary over time as AWS modifies their user interface.

5. From the AWS Management Console main page, select the [region](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html#concepts-regions) you wish to use from the drop-down list in the upper right.  The solution code will use _us-west_2_, but feel free to choose another.  Be aware that some regions may require you to "opt-in" to use them, and may not provide the same Bedrock features.  (Have fun by selecting a region on the opposite side of the planet from you.)



1. In the search box on the top of the console, type **bedrock** and select the Amazon Bedrock service.
1. Open the left menu if it is closed (click the "hamburger" icon with three horizontal lines). From the menu select **Model access**.
1. Click **Modify model access**.
1. Select **Titan Text G1 - Express** and click **Next**.
- Note: you may enable other models if you like.  Generally there is no charge to enable a model, only acceptance of a license agreement.  The _Titan_ models from Amazon are relatively inexpensive.
10. Click **Submit**.  It may take a few moments for the model to become active.

Note:  [Amazon Bedrock pricing](https://aws.amazon.com/bedrock/pricing/) for chat/text is based on input and output tokens, and varies depending on the model chosen.  Tokens currently cost between $0.00015 and $0.02 per thousand input tokens, $0.00125 and $0.024 per thousand output tokens.  It is always a good idea to double-check the pricing page when using a cloud provider.


---
**Part 3 - Generate the Project Structure**

5.  Use [https://start.spring.io](https://start.spring.io), create a new Spring Boot Project.
  - Use either Maven or Gradle - Groovy (if you have it installed).  All lab instructions are based on Maven.
  - Use the latest stable releases of Boot and Java.  These instructions were originally tested with Java 1.21, Boot 3.2.5.  The version can be changed after the project is generated.
  - Use JAR packaging.
  - For project metadata, adjust the values as you like.  These instructions will assume an artifact and name of “lab2-aws”.
  - Adjust the package to `com.example` for simplicity.  Feel free to re-arrange the package structure later, but the remaining instructions will assume the former.
  - Search for and select the `Amazon Bedrock` dependency. 
6. Generate.  Find the downloaded zip and expand it.  Copy the `lab2-aws` project to wherever you downloaded your labfiles to.
---
**Part 4 - Import into your IDE**

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
**Part 5 - Basic Configuration and Test**

At this point, let's take a moment to ensure that everything we have created so far is error free.

11.  Within your IDE, find the `src/main/resources/application.properties`.  Rename this file as `src/main/resources/application.yml`.
12.  Establish the following entries:
```
spring:
  application.name: lab2-aws
  main.web-application-type: none     # Do not start a web server.

  ai:
    bedrock:
      aws.region: us-west-2
      titan:
        chat:
          enabled: true
    retry:
      max-attempts: 1      # Maximum number of retry attempts.
```
- Adjust the [region](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html#concepts-regions) code to match the region where you enabled the Bedrock model.
- The `titan.chat.enabled` setting tells Spring Boot to specifically autoconfigure objects supporting the _Titan_ model.  We can enable multiple models, but not _simultaneously_.
- Note: The retry settings will override the `ChatClient`'s default settings.  You are likely to experience errors while you learn the API's usage, and we don't want you to experience unnecessary expenses.
- We could store the access key ID and secret key values in this file, but this would be a security risk if we were to ever distribute this file.  Setting these values in environment variables is safer.

13.  Save your work.  

14.  Find the main application class in `src/main/java/com/example`.  It is probably named `Lab2AwsApplication.java` if you have followed theses instructions (We usually rename ours to `Application.java`). Run the application.

* **VS Code**: Right-click, Run Java.  Or open the file and click the “Run” option hovering over the main() method.
* **IntelliJ**: Right-click, select “Run ‘Application.main()’”. 
* **Eclipse**: Right-click, Select Run As / Java Application.

* We expect the application to start, then stop, without errors.  If you have any errors related to tooling, be sure to address them now before proceeding.

---
**Part 6 - Try Spring AI's `ChatClient`**

At this point we should be able to try using the ChatClient to make API calls to Amazon Bedrock and any of its hosted models.

15. Create a new  **client**  folder under `src/main/java/com/example`.
1. Within this package create a new Java file called `AwsClient.java`.
* The IDE should create an empty Java class definition for you.
17. Add the following annotations at the class level to make this object a Spring Bean and to only activate it when the "aws" profile is active:
```
@Component
@Profile("aws")
public class AwsClient {
``` 
- Note: the `@Profile` annotation will be useful later when we want our application to switch between OpenAI, Azure, Ollama, etc.
18. Add a private reference `ChatClient`, then provide a constructor which takes a `ChatClient.Builder`. This Builder will be automatically created by Spring Boot when it sees SpringAI on the classpath, and Spring will automatically inject it into the constructor on bean creation.  Provide code to build the client using the builder::
```
    private ChatClient client;

    public AwsClient(ChatClient.Builder builder) {
        this.client = builder.build();
    }
```
19. Add a `callModel` method.  Define a String parameter for the prompt:  
```
    public String callModel(String input ) {

        //  Prepare a prompt with input and default options:
        Prompt prompt = new Prompt(
            input,
            BedrockTitanChatOptions.builder().build()
        );

        return client.prompt(prompt).call().content();
    }
````
- This code creates a `Prompt` object with the given String input and an `Options` object customized for the _Titan_ model in use.  This object allows us to override default settings for temperature, topP, max tokens, etc.  We can also override these in `application.yml`.
- The `call().content()` is a way to obtain a simple String response rather than a more complex `ChatResponse` object.
20. Supply any imports needed to make the code compile.
* **VS Code**: Type Alt-Shift-O.
* **IntelliJ**: Type Ctrl-Alt-O.
* **Eclipse**: Type Ctrl-Shift-O.
21. Save your work.

---
**Part 6 - Create a `@Test` class**

Anything we code, we should test.  We will make a `@Test` class to ensure our Client object works as expected.

22. Create a new  **client**  folder under `src/test/java/com/example`.  Within this package create a new Java file called `AwsClientTests.java`.

23. Alter the test class to include the `@SpringBootTest` annotation. Tell boot to run as a non-web application and activate the 'aws' profile like this:
```
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("aws")
public class AwsClientTests {
    //...
```	
24. Add code to automatically provide a reference to the `AwsClient`:
```
  @Autowired AwsClient client;
```
Add a `@Test` method to use the `client` to make an example API call:
```
	@Test
	void quickChat() {

        String response = 
            client.callModel(
                "Generate the names of the five great lakes.  Produce only JSON output.");

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

The test should run and produce a list of the five Great Lakes.  It is also very possible that you will encounter an error here, possibly due to setup, but also due to changes in Amazon Bedrock since the time of this writing.  Here are some troubleshooting tips.
* If you have a compilation issue, be sure you have organized imports.  Compare your code to the solution code.
* Be sure your access key and secret key are established in environment variables.
* Be sure the region in your `application.yml` file is the same region where you enabled the Bedrock model.


**Part 7 - Summary**

At this point, you have integrated with one of Amazon Bedrock's hosted models from your own Spring Boot application.  If your were running a Spring Boot application on an AWS EC2 instance within a private network, this would be a natural combination to use rather than calling OpenAI or another hosted model directly.  Congratulations! 



