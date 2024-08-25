package com.example.client;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//  TODO-05: Use a stereotype annotation to mark this class as a Spring bean.
//  Use an annotation to assign it to the "azure" profile.
@Component
@Profile("azure")
public class AzureClient implements AIClient {

    private ChatClient client;

    //  TODO-06: Create a constructor for this bean.
    //  Inject a ChatModel object into the constructor.
    //  Pass the model to the ChatClient.builder to build a ChatClient object.
    //  Save the ChatClient object in the client field.
    public AzureClient(ChatModel model) {
        client = ChatClient.builder(model).build();
    }

    public String callApi(String input ) {

		//  TODO-07: Build a ChatOptions object using the AzureOpenAiChatOptions builder.
		//  Use the withFunction() method to set the "stockService" bean name.
		ChatOptions options = 
			AzureOpenAiChatOptions.builder()
				.withFunction("stockService")
				.build();

		//  TODO-08: Build a Prompt object using the input and the ChatOptions object:
		Prompt prompt = new Prompt(input, options);

		//  TODO-09: Use the client object to call the API.
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