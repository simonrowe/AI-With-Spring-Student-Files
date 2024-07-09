package com.example.client;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions;
import org.springframework.ai.bedrock.titan.BedrockTitanChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//  TODO-05: Use a stereotype annotation to mark this class as a Spring bean.
//  Use an annotation to assign it to the "azure" profile.

@Component
@Profile("azure")
public class AzureClient implements AIClient {

    private ChatClient client;

    //  TODO-06: Create a constructor for this bean.
    //  Inject a AzureOpenAiChatModel object into the constructor.
    //  Pass the model to the ChatClient.builder to build a ChatClient object.
    //  Save the ChatClient object in the client field.
    public AzureClient(AzureOpenAiChatModel model) {
        client = ChatClient.builder(model).build();
    }

    public String callApi(String input) {

        //  TODO-07: Define a new Prompt object using the user input.
        Prompt prompt = new Prompt(input);

        //  TODO-08: Use the client object to call the API.
        //  The .prompt() method can be used to set the prompt defined above.
        //  The .call() method will make the call to the model.
        //  The .content() method will return the content of the response.
        //  Have the method return the content of the response.
        return 
            client
                .prompt(prompt)
                .call()
                .content();
    }

}