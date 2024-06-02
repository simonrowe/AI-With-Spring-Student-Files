install extensions in vs code
'Test Runner for Java'

how to open / import projects into eclipse or intellij

organize imports

---
**Signup Process for OpenAI**

You will need an account on OpenAI to perform any of the labs specific to this technology

NOTE:  The AI industry changes rapidly.  The instructions here were valid as of June 2024.  You may find the screens and terminology have updated since these instructions were recorded.

1. Go to https://platform.openai.com/signup
Login or signup for an account.  We recommend the signup option using Google, Microsoft, Apple, etc. 
2. This will take you to an initial page welcoming you to the OpenAI Developer Platform.  Once here you can create API Keys at https://platform.openai.com/api-keys.
3. Create a new secret key.  Either type of secret key will work, but the “service account” option is more appropriate.  Make a service account ID such as "svc-account-one".  For the project, you should already see a default project associated with your default organization.
4. Create secret key.  COPY the generated key value. 
5. Set an environment variable named `SPRING_AI_OPENAI_API_KEY` using this value.  On Windows you can run: 
```
setx SPRING_AI_OPENAI_API_KEY "YOUR-KEY-GOES-HERE"
```
On Linux or Mac you can run:
```
export SPRING_AI_OPENAI_API_KEY="YOUR-KEY-GOES-HERE"
```
* **CRITICAL: DO NOT STORE THESE VALUES IN A PUBLIC REPOSITORY** While the spring.ai.openai.api-key value can be set in application.properties or application.yml, you run the risk of exposing your key should your commit your work to a public source repository.  For this reason, usage of the environment variable is recommened.
* If you loose this key, don't worry.  You can easily create a new one if needed.
* Note: You may have to establish a payment method if you have exhausted your free tier.  This can happen if you use ChatGPT frequently - like we do.  The labs in this course should cost less than $5.
* Restart your IDE to ensure that the value is picked up.


---
**Signup Process for Azure OpenAI**

If you wish to work on any of the Azure OpenAI labs, you will need to establish an Azure account, sign up for Azure AI services, and create a resource and a deployment.

Microsoft Azure provides the means to host OpenAI models within an Azure account.  This allows easier access from within Azure-hosted workloads.  

NOTE:  Cloud providers such as Azure change their procedures from time to time without warning.  The instructions here were valid as of June 2024.  You may find the screens and terminology have updated since these instructions were recorded.

1. **Setup an Azure account**:  Go to https://portal.azure.com/#home (If you already have an account, you can skip to the next steps on signing up for Azure AI services.)

    - The signup process typically asks your a few questions on what you plan to do with Azure.  This is mainly to customize your experience and it is not necessary to answer these with precision.  We used "Use AI and ML to add intelligent features to apps".
    - You may have to enter a payment method if you do not qualify for a trial.  The labs in this course should cost less than $5.
    - You may be taken on a short tour of the Azure portal.

1. **Signup for Azure AI Services**: From the Azure main console, find the search field on the top.  Type "Azure AI Services".

    * At present, even if you have an Azure account, you must specifically enable the use of Azure OpenAI: https://customervoice.microsoft.com/Pages/ResponsePage.aspx?id=v4j5cvGGr0GRqy180BHbR7en2Ais5pxKtso_Pz4b1_xUNTZBNzRKNlVQSFhZMU9aV09EVzYxWFdORCQlQCN0PWcu .  This requires a work email address (not a personal email address) and a subscription.  
    * It can take over 24 hours to receive a response.

1. **Create an Azure OpenAI _resource_**: Once you have an Azure account and Azure OpenAI is enabled, create an Azure OpenAI resource:

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
    We can also set the endpoint (and deployment name) using environment variables, but they are less sensitive.  We will set these later within application.yml.

1. **Create a _Deployment_**: Open https://oai.azure.com/portal in a new browser tab.  Go to Management / Deployments / Create new deployment.
    1. Select a model to use.  _gpt-35-turbo_ has the lowest price at the time of this writing.
    1.  Choose the default version and standard deployment type.
    1.  Provide a name for the deployment.  We suggest using the same name as the "resource" defined earlier.  Record the name, you will need it later.
    1. Adjust advanced options if you like. Create. 

Note:  [Azure OpenAI pricing](https://azure.microsoft.com/en-us/pricing/details/cognitive-services/openai-service/) for chat/text is based on input and output tokens, and varies depending on the model chosen.  Tokens currently cost between $0.0005 and $0.06 per thousand input tokens, $0.002 and $0.12 per thousand output tokens.  It is always a good idea to double-check the pricing page when using a cloud provider.





STATIC IMPORTS