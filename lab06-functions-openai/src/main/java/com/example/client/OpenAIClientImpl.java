package com.example.client;

import org.springframework.ai.chat.client.ChatClient;

//  TODO-05: Use @Component to mark this class as a Spring bean.
//  Use an annotation to assign it to the "openai" profile.

public class OpenAIClientImpl implements AIClient {

    private ChatClient client;

    //  TODO-06: Create a constructor for this bean.
    //  Inject a ChatModel object into the constructor.
    //  Pass the model to the ChatClient.builder to build a ChatClient object.
    //  Save the ChatClient object in the client field.


    public String callApi(String input ) {

		//  TODO-07: Build a ChatOptions object using the OpenAiChatOptions builder.
		//  Use the withFunction() method to set the "stockService" bean name.


		//  TODO-08: Build a Prompt object using the input and the ChatOptions object:
		

		//  TODO-09: Use the client object to call the API.
        //  The .prompt() method can be used to set the prompt defined above.
        //  The .call() method will make the call to the model.
        //  The .content() method will return the content of the response.
        //  Have the method return the content of the response.
        return null; // Replace this line.

    }

}