To complete the labs you will need to perform some minimum setup.  You should have a Java JDK installed and an IDE.  

Beyond this, your setup will depend on which labs you wish to complete.  You only need to follow the setup instructions for the labs you are interested in.  For example, if you want to do the OpenAI labs but not the Amazon/Bedrock labs, there is no need to complete the Amazon setup steps.

These lab instructions may drift over time as the underlying technologies change, especially instructions based on following user interfaces.  If you find the instructions don't match what you see, please send me a message to alert me to the change.

## Table of Contents:

- [Setup Process for Java JDK](#setup-process-for-java-jdk)
- [Setup Process for Visual Studio Code](#setup-process-for-visual-studio-code)
- [Setup Process for IntelliJ Community Edition](#setup-process-for-intellij-community-edition)
- [Setup Process for Eclipse](#setup-process-for-eclipse)
- [Setup Process for OpenAI](#setup-process-for-openai)
- [Setup Process for Azure OpenAI](#setup-process-for-azure-openai)
- [Setup Process for Amazon Bedrock](#setup-process-for-amazon-bedrock)
- [Setup Process for Stability.AI](#setup-process-for-stability-ai)
- [Setup Process for ZhiPu AI](#setup-process-for-zhipu-ai)
- [Setup Process for Ollama](#setup-process-for-ollama)
- [Setup Process for Docker](#setup-process-for-docker)
- [Setup Process for PostgreSQL Docker Container](#setup-process-for-postgresql-docker-container)
- [Setup Process for PGVector Docker Container](#setup-process-for-pgvector-docker-container)
- [Setup Process for Redis Docker Container](#setup-process-for-redis-docker-container)
---
### Setup Process for Java JDK

If you don't already have it installed, you will need a JDK installed on your local environment.  The labs were developed and tested with Java 21; an earlier Java version will not work, but a later version should.  The instructions below are for OpenJDK, but you can also use Temurin, Correto, or any other distribution if you like.

#### Windows:
1. **Download the JDK**: Go to the [OpenJDK Downloads page](https://jdk.java.net/) and download the latest version for Windows.
2. **Run the Installer**: Double-click the downloaded `.msi` or `.exe` file to start the installation.
3. **Follow Installation Wizard**: Follow the prompts in the installation wizard to complete the installation.
4. **Set Environment Variables**:
    - Open **System Properties** (Right-click on My Computer/This PC > Properties > Advanced system settings).
    - Click on **Environment Variables**.
    - Under **System variables**, click **New** and add:
      - Variable name: `JAVA_HOME`
      - Variable value: `C:\Program Files\Java\jdk-[your-version]`
    - Find the `Path` variable in the System variables list, select it, and click **Edit**. Add a new entry:
      - `C:\Program Files\Java\jdk-[your-version]\bin`
5. **Verify Installation**: Open Command Prompt and type `java -version` to confirm the installation.

#### Mac:
1. **Download the JDK**: Go to the [OpenJDK Downloads page](https://jdk.java.net/) and download the latest version for macOS.
2. **Run the Installer**: Open the downloaded `.dmg` file and follow the prompts to install the JDK.
3. **Set Environment Variables**:
    - Open Terminal.
    - Edit the `.bash_profile` or `.zshrc` file (depending on your shell) by adding:
      ```sh
      export JAVA_HOME=$(/usr/libexec/java_home)
      export PATH=$JAVA_HOME/bin:$PATH
      ```
    - Save the file and run `source ~/.bash_profile` or `source ~/.zshrc` to apply the changes.
4. **Verify Installation**: Open Terminal and type `java -version` to confirm the installation.

#### Official Links
- [OpenJDK Downloads](https://jdk.java.net/)
- [Oracle JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html)

For detailed instructions, visit the [official OpenJDK documentation](https://openjdk.java.net/install/).


---
### Setup Process for Visual Studio Code

If you'd like to do the labs in VSCode, follow these setup instructions.  If you already have VSCode and want to use it for Java, follow the guidance below on installing the correct Extension.

#### Windows:
1. **Download VSCode**: Go to the [Visual Studio Code download page](https://code.visualstudio.com/Download) and download the installer for Windows.
2. **Run the Installer**: Double-click the downloaded `.exe` file to start the installation.
3. **Follow Installation Wizard**: Follow the prompts in the installation wizard. Ensure to check the boxes for "Add to PATH" and other options as needed.
4. **Launch VSCode**: After installation, open VSCode from the Start menu or desktop shortcut.

#### macOS:
1. **Download VSCode**: Go to the [Visual Studio Code download page](https://code.visualstudio.com/Download) and download the `.dmg` file for macOS.
2. **Run the Installer**: Open the downloaded `.dmg` file and drag the VSCode icon to the Applications folder.
3. **Launch VSCode**: Open VSCode from the Applications folder or Launchpad.

#### Installing the "Extension Pack for Java™ by Microsoft"
1. **Open Extensions View**: Launch VSCode and click on the Extensions view icon on the Sidebar (or press `Ctrl+Shift+X`).
2. **Search for the Extension Pack**: In the Extensions view, type `"Extension Pack for Java™ by Microsoft"` in the search box.
3. **Install the Extension Pack**: Find the "Extension Pack for Java" by Microsoft and click the `Install` button.
4. **Verify Installation**: After installation, the extension pack will be listed under "Installed" in the Extensions view.

#### Installing the "Test Runner for Java"
- Follow the previous steps, but select `Test Runner for Java`.

#### Official Links
- [Visual Studio Code Download](https://code.visualstudio.com/Download)
- [Extension Pack for Java™ by Microsoft](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

For detailed instructions and additional information, visit the [official VSCode documentation](https://code.visualstudio.com/docs) and the [Java in VSCode documentation](https://code.visualstudio.com/docs/java/java-tutorial).


---
### Setup Process for IntelliJ Community Edition

Instructions to Install IntelliJ IDEA Community Edition.  If you follow these instructions, _be sure to download the **community edition* rather than the paid edition.  Both work, but one is free.

#### Windows:
1. **Download IntelliJ IDEA Community Edition**: Go to the [IntelliJ IDEA Downloads page](https://www.jetbrains.com/idea/download/) and ensure you select the **Community** edition under the "Windows" section.
2. **Run the Installer**: Double-click the downloaded `.exe` file to start the installation.
3. **Follow Installation Wizard**:
    - Select installation options as needed (e.g., add to PATH, create desktop shortcut).
    - Click "Next" and then "Install" to proceed with the installation.
4. **Launch IntelliJ IDEA**: After installation, open IntelliJ IDEA from the Start menu or desktop shortcut.
5. **Complete Initial Setup**: Follow the initial setup wizard to customize your IntelliJ IDEA environment.

#### macOS:
1. **Download IntelliJ IDEA Community Edition**: Go to the [IntelliJ IDEA Downloads page](https://www.jetbrains.com/idea/download/) and ensure you select the **Community** edition under the "macOS" section.
2. **Run the Installer**: Open the downloaded `.dmg` file and drag the IntelliJ IDEA icon to the Applications folder.
3. **Launch IntelliJ IDEA**: Open IntelliJ IDEA from the Applications folder or Launchpad.
4. **Complete Initial Setup**: Follow the initial setup wizard to customize your IntelliJ IDEA environment.

#### Official Links
- [IntelliJ IDEA Downloads](https://www.jetbrains.com/idea/download/)
- [IntelliJ IDEA Documentation](https://www.jetbrains.com/idea/documentation/)


---
### Setup Process for Eclipse

#### Windows:
1. **Download Eclipse**: Go to the [Eclipse Downloads page](https://www.eclipse.org/downloads/) and click on the "Download x86_64" link to get the installer for Windows.
2. **Run the Installer**: Double-click the downloaded `.exe` file to start the Eclipse Installer.
3. **Select Eclipse IDE for Java Developers**: In the installer, select "Eclipse IDE for Java Developers" (or the version that suits your needs).
4. **Choose Installation Folder**: Select the installation folder or leave it as the default, then click "Install".
5. **Accept Licenses**: Accept the license agreements to proceed with the installation.
6. **Launch Eclipse**: After installation, click the "Launch" button in the installer or open Eclipse from the Start menu or desktop shortcut.
7. **Select Workspace**: Upon first launch, you will be prompted to select a workspace directory. Choose a default workspace or specify a different location, then click "Launch".

#### macOS:
1. **Download Eclipse**: Go to the [Eclipse Downloads page](https://www.eclipse.org/downloads/) and click on the "Download x86_64" link to get the installer for macOS.
2. **Run the Installer**: Open the downloaded `.dmg` file and drag the Eclipse icon to the Applications folder.
3. **Select Eclipse IDE for Java Developers**: Open the Eclipse Installer from the Applications folder and select "Eclipse IDE for Java Developers" (or the version that suits your needs).
4. **Choose Installation Folder**: Select the installation folder or leave it as the default, then click "Install".
5. **Accept Licenses**: Accept the license agreements to proceed with the installation.
6. **Launch Eclipse**: After installation, open Eclipse from the Applications folder or Launchpad.
7. **Select Workspace**: Upon first launch, you will be prompted to select a workspace directory. Choose a default workspace or specify a different location, then click "Launch".

#### Official Links
- [Eclipse Downloads](https://www.eclipse.org/downloads/)
- [Eclipse Documentation](https://help.eclipse.org/latest/index.jsp)

For detailed instructions and additional information, visit the [Eclipse Documentation](https://help.eclipse.org/latest/index.jsp).


---
### Setup Process for OpenAI

You will need an account on OpenAI to perform any of the labs specific to this technology

NOTE:  The AI industry changes rapidly.  The instructions here were valid as of June 2024.  You may find the screens and terminology have updated since these instructions were recorded.

1. Go to https://platform.openai.com/signup
Login or signup for an account.  We recommend the signup option using Google, Microsoft, Apple, etc. 
2. This will take you to an initial page welcoming you to the OpenAI Developer Platform.  Once here you can create API Keys at https://platform.openai.com/api-keys.
3. Find the APIKeys page and Create a new secret key.  Either type of secret key will work, but the “service account” option is more appropriate.  Make a service account ID such as "svc-account-one".  For the project, you should already see a default project associated with your default organization, so there is no reason to specify a value.
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
    1. Resource names must be unique.  I suggest creating one based on your full name plus a unique number, like the year.  
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
    1.  Provide a name for the deployment.  We suggest using the same name as the model you've just selected (there is generally a 1 to 1 relationship with model and deployment).  Record the name, you will need it later.
    1. Adjust advanced options if you like. Create. 

Note:  [Azure OpenAI pricing](https://azure.microsoft.com/en-us/pricing/details/cognitive-services/openai-service/) for chat/text is based on input and output tokens, and varies depending on the model chosen.  Tokens currently cost between $0.0005 and $0.06 per thousand input tokens, $0.002 and $0.12 per thousand output tokens.  It is always a good idea to double-check the pricing page when using a cloud provider.

---
### Setup Process for Amazon Bedrock

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
### Setup Process for Stability AI

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
### Setup Process for ZhiPu AI

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

---
### Setup Process for Ollama


Ollama is a locally hosted Large Language Model.  Official installation instructions can be found at https://ollama.com/.  

One easy way to run Ollama is via Docker container.  Follow the instructions below to install Docker and make sure it is running.  Running `docker ps` should result in no error.

Run the following Docker commands:

```
docker pull ollama/ollama
docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
docker exec -it ollama ollama run llama2
```

* The first command downloads the official ollama image from Dockerhub.
* The second runs ollama in the background, setting up a folder on your home directory called ".ollama" for storage.  It listens for traffic on port 11434, which is exactly what the SpringAI model expects by default.
* The third command instructs ollama to download the llama2 model.  Once this is done the container will respond to requests targeting the given model. 

Warning - these models are LARGE; the llama2 model will be a 4GB download.  Llama3 is closer to 40!

Once the model is downloaded and running, you can start and stop the container using `docker start` and `docker stop` commands.  If you want to try a different model, you'll need to run the `docker exec ...` command specifying the model you want.

If Docker does not work for any reason, you can also download local software for Windows, Mac, and Linux, see https://github.com/ollama/ollama/blob/main/README.md.  Once this is done, you can run a command like: `ollama run llama2`

EMBEDDINGS:  When you get to the lab on embeddings, if you wish to use Ollama, you'll need to download and install the "mistral" model first.
I found again that the docker container isn't doing anything.  ollama runs in the background as a service.
run this: 

```
ollama pull mistral
ollama run mistral
```






---
### Setup Process for Docker

For labs that require it, you will need Docker installed and running.  Reference the following instructions.

#### Windows:
1. **Download Docker Desktop**: Go to [Docker Desktop for Windows](https://desktop.docker.com/win/stable/Docker%20Desktop%20Installer.exe).
2. **Run the Installer**: Double-click the downloaded installer to start the installation.
3. **Follow Installation Wizard**: Follow the prompts in the installation wizard.
4. **Start Docker**: After installation, Docker Desktop should start automatically. If not, search for "Docker Desktop" in the Start menu and open it.
5. **Enable WSL 2**: During setup, enable WSL 2 if prompted for better performance.

#### Mac:
1. **Download Docker Desktop**: Go to [Docker Desktop for Mac](https://desktop.docker.com/mac/stable/Docker.dmg).
2. **Run the Installer**: Double-click the downloaded `.dmg` file to open the installer.
3. **Drag to Applications**: Drag the Docker icon to the Applications folder.
4. **Start Docker**: Open Docker from the Applications folder or Launchpad.
5. **Follow Setup Instructions**: Complete the setup as prompted by Docker Desktop.

#### Official Links
- [Docker Desktop for Windows](https://desktop.docker.com/win/stable/Docker%20Desktop%20Installer.exe)
- [Docker Desktop for Mac](https://desktop.docker.com/mac/stable/Docker.dmg)

For detailed instructions, visit the [official Docker documentation](https://docs.docker.com/get-docker/).


---
### Setup Process for PostgreSQL Docker Container

Run these commands from the command line:

```
docker pull postgres
docker run --name local_postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

docker exec -it local_postgres psql -U postgres
```

---
### Setup Process for PGVector Docker Container

The labs featuring Vector Store have optional steps for running on an actual Vector Store rather than the `SimpleVectorStore`. To complete these steps using PostgreSQL's PGVectorStore, follow the steps here.  PGVectorStore is a variant of postgres specialized for Vector storage. 

WARNING:  This is not an official image.

```
# Note: Signin may be required:
docker pull ankane/pgvector

docker run --name local_pgvector -d -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres ankane/pgvector

docker exec -it local_pgvector psql -U postgres
```

Background information, but not the instructions I followed:  https://www.docker.com/blog/how-to-use-the-postgres-docker-official-image/


---
### Setup Process for Redis Docker Container

The labs featuring Vector Store have optional steps for running on an actual Vector Store rather than the `SimpleVectorStore`. To complete these steps using Redis as a vector store, run Redis as a Docker container.

install Redis by running on Docker:

```
docker pull redis/redis-stack:latest
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
```

Note:  If you like, you can set a password on startup like this:
```
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 -e REDIS_ARGS="--requirepass mypassword" redis/redis-stack:latest
```

