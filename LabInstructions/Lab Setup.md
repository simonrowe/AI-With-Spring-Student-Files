install extensions in vs code
'Test Runner for Java'

how to open / import projects into eclipse or intellij

organize imports


Quick Links:
- [Setup Process for Azure OpenAI](#setup-process-for-azure-openai)



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

    * **SECURITY WARNING:**  Do not store this key in any file (such as `application.properties` or `application.yml`) that you may share publicly, such as on GitHub, BitBucket, Google Docs, etc.  For the lab environment, we recommend use of environment variables.
    * If you loose this key, don't worry.  You can easily create a new one if needed.
    * Note: You may have to establish a payment method if you have exhausted your free tier.  This can happen if you use ChatGPT frequently - like we do.  The labs in this course should cost less than $5.
1. Restart your IDE after setting an environment variable this way.


---
### Setup Process for Azure OpenAI

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
    1. Click "Show Keys".  Record the values for key 1, key 2, and endpoint in a temporary file.  **SECURITY WARNING: DO NOT STORE THE KEY IN A PUBLIC REPOSITORY** 

1. Set an environment variable named `SPRING_AI_AZURE_OPENAI_API_KEY` using this value.  On Windows you can run: 
    ```
    setx SPRING_AI_AZURE_OPENAI_API_KEY "KEY-GOES-HERE"
    ```
    On Linux or Mac you can run:
    ```
    export SPRING_AI_AZURE_OPENAI_API_KEY="KEY-GOES-HERE"
    ```
    * **SECURITY WARNING:**  Do not store this key in any file (such as `application.properties` or `application.yml`) that you may share publicly, such as on GitHub, BitBucket, Google Docs, etc.  For the lab environment, we recommend use of environment variables.
    * We can also set the endpoint (and deployment name) using environment variables, but they are less sensitive.  We will set these later within `application.yml`.
1. Restart your IDE after setting an environment variable this way.


1. **Create a _Deployment_**: Open https://oai.azure.com/portal in a new browser tab.  Go to Management / Deployments / Create new deployment.
    1. Select a model to use.  _gpt-35-turbo_ has the lowest price at the time of this writing.
    1.  Choose the default version and standard deployment type.
    1.  Provide a name for the deployment.  We suggest using the same name as the "resource" defined earlier.  Record the name, you will need it later.
    1. Adjust advanced options if you like. Create. 

Note:  [Azure OpenAI pricing](https://azure.microsoft.com/en-us/pricing/details/cognitive-services/openai-service/) for chat/text is based on input and output tokens, and varies depending on the model chosen.  Tokens currently cost between $0.0005 and $0.06 per thousand input tokens, $0.002 and $0.12 per thousand output tokens.  It is always a good idea to double-check the pricing page when using a cloud provider.

---
**Signup Process for Amazon / Bedrock**

Note that the exact screen flow to perform these steps may vary over time as AWS modifies their user interface.

1. Signup for an AWS Account

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

1. Set Credentials

    Next, provide the access key ID and secret key to our software.  

    AWS recommends (and we agree) that the best way to provide credentials to our code is IAM Roles and the [EC2 Instance Metadata service](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/configuring-instance-metadata-service.html), or the local `~/.aws/credentials file`.  However, to keep things simple and consistent with other labs, we will use environment variables.

    1. Set the following environment variables using the values obtained from the CSV file.  On Windows you can run: 
        ```
        setx SPRING_AI_BEDROCK_AWS_ACCESS_KEY "YOUR_ACCESS_KEY"
        setx SPRING_AI_BEDROCK_AWS_SECRET_KEY "YOUR_SECRET_KEY"
        ```
        On Linux or Mac you can run:   
        ```
        export SPRING_AI_BEDROCK_AWS_ACCESS_KEY="YOUR_ACCESS_KEY"
        export SPRING_AI_BEDROCK_AWS_SECRET_KEY="YOUR_SECRET_KEY"
        ```

1. Enable Bedrock Models

    When using Amazon Bedrock, you must _enable_ the models you wish to use. This is easily done via the AWS Management Console.

    Note that the exact steps may vary over time as AWS modifies their user interface.

    5. From the AWS Management Console main page, select the [region](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html#concepts-regions) you wish to use from the drop-down list in the upper right.  The solution code will use _us-west_2_, but feel free to choose another.  Be aware that some regions may require you to "opt-in" to use them, and may not provide the same Bedrock features.  (Have fun by selecting a region on the opposite side of the planet from you.)
    1. In the search box on the top of the console, type **bedrock** and select the Amazon Bedrock service.
    1. Open the left menu if it is closed (click the "hamburger" icon with three horizontal lines). From the menu select **Model access**.
    1. Click **Modify model access**.
    1. For the labs presented in this course, we recommend you select and enable the following models:
    * **Amazon - Titan Text G1 - Express**
    * **Amazon - Titan Embeddings G1 - Text**
    * **Anthropic - Claude**
    - Note: you may enable other models if you like.  Generally there is little or no charge to enable a specific model, only acceptance of a license agreement.  The _Titan_ models from Amazon are relatively inexpensive.
    10. Click **Submit**.  It may take a few moments for the model to become active.

    Note:  [Amazon Bedrock pricing](https://aws.amazon.com/bedrock/pricing/) for chat/text is based on input and output tokens, and varies depending on the model chosen.  Tokens currently cost between $0.00015 and $0.02 per thousand input tokens, $0.00125 and $0.024 per thousand output tokens.  It is always a good idea to double-check the pricing page when using a cloud provider.


---
**Signup Process for Stability.AI**

If you wish to work on any of the Stability.AI labs, you will need to establish a [Stability.AI](https://platform.stability.ai) account.

NOTE:  Cloud providers such as Stability.AI change their procedures from time to time without warning.  The instructions here were valid as of June 2024.  You may find the screens and terminology have updated since these instructions were recorded.

1. **Setup a Stability.AI account**:  (If you already have an account, you can skip the next few steps).  Go to [Stability.AI](https://platform.stability.ai)
1. Click __login__.
1. Click __sign up__.
1. Establish an account with Google or provide your own username / password.
1. Once finished, you will have a working account.  However, once your free credits are exhausted your API calls will fail.  Consider buying credits now or just wait until failures occur.
1. Open your account information (upper right-hand corner) to [find your API keys](https://platform.stability.ai/account/keys).
1. Click the __copy__ icon next to your key. 
1. Set an environment variable named `SPRING_AI_STABILITYAI_API_KEY` using this value.  
    On Windows you can run: 
    ```
    setx SPRING_AI_STABILITYAI_API_KEY "KEY-GOES-HERE"
    ```
    On Linux or Mac you can run:
    ```
    export SPRING_AI_STABILITYAI_API_KEY="KEY-GOES-HERE"
    ```
    * **SECURITY WARNING:**  Do not store this key in any file (such as `application.properties` or `application.yml`) that you may share publicly, such as on GitHub, BitBucket, Google Docs, etc.  For the lab environment, we recommend use of environment variables.
1. Restart your IDE after setting an environment variable this way.

Stability.AI offers free credits for new accounts, but these may not be enough to cover the costs of labs in this course.  Be sure you are familiar with [Stability.AI pricing](https://platform.stability.ai/pricing) before working on any Stability.AI-based labs.


---
**Signup Process for ZhiPu AI**

If you wish to work on any of the ZhiPu AI labs, you will need to establish a [ZhiPu AI](https://open.bigmodel.cn/) account.

NOTE:  Cloud providers such as ZhiPu AI change their procedures from time to time without warning.  The instructions here were valid as of June 2024.  You may find the screens and terminology have updated since these instructions were recorded.

1. **Setup a ZhiPu AI account**:  (If you already have an account, you can skip the next few steps).  
1. Go to [ZhiPu AI / Login](https://open.bigmodel.cn/login)
1. Click __login__.
1. Enter a mobile phone number.  You may be asked to complete a small online puzzle by dragging a shape to a spot on an image where it fits.
1. Enter the code received on your phone.
1. You may be asked to verify your identity to receive extra tokens.  I found this took me to a Chinese-only language page, so avoid this unless your Mandarin is up to speed.  You can also attempt to use tools like Google translate here on the web page.
1. On the top right of the page, you will find an icon that looks like a key.  This will take you to a page listing your API Keys.
1. Use the copy icon to record this key.
1. Set an environment variable named `SPRING_AI_ZHIPU_AI_API_KEY` using this value.  
    On Windows you can run: 
    ```
    setx SPRING_AI_ZHIPUAI_API_KEY "KEY-GOES-HERE"
    ```
    On Linux or Mac you can run:
    ```
    export SPRING_AI_ZHIPUAI_API_KEY="KEY-GOES-HERE"
    ```
    * **SECURITY WARNING:**  Do not store this key in any file (such as `application.properties` or `application.yml`) that you may share publicly, such as on GitHub, BitBucket, Google Docs, etc.  For the lab environment, we recommend use of environment variables.
1. Restart your IDE after setting an environment variable this way.

ZhiPu AI offers a fairly generous free tier for new accounts: 25 million tokens upon registration, 5 million tokens more after completing identity verification.  1,000 tokens cost roughly $0.01 at the exchange rate / pricing in June 2024.  Note that these are tokens, not "credits" as are common to other vendors; a request of 100 words with a response of 1,000 words would consume approximately 1,400 tokens. 

For images, no free offer is available.  Images are roughly $0.03 each.  Since ZhiPu is Chinese-based, payments must be made through WeChat pay or Alipay.

POSTGRESQL DOCKER INSTALL

docker pull postgres

docker run --name local_postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

docker exec -it local_postgres psql -U postgres

-----
For PGVectorStore variant of postgres:
Warning: this is not an official image.

docker run --name local_pgvector -d -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres ankane/pgvector

docker exec -it local_pgvector psql -U postgres


Background information, but not the instructions I followed:  https://www.docker.com/blog/how-to-use-the-postgres-docker-official-image/











STATIC IMPORTS