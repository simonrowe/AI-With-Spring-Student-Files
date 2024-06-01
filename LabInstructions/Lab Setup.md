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

STATIC IMPORTS