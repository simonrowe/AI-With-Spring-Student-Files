package com.example.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//  TODO-05: Use @Component("openAIClientBean") to mark this class as a Spring bean.
//  (The name "openAIClientBean" is only needed to prevent naming ambiguitiy with Spring's "OpenAIClient" bean.)
//  Use an annotation to assign it to the "openai" profile.

@Component("openAIClientBean")
@Profile("openai")
public class OpenAIClient implements AIClient {

    private ChatClient client;

    //  TODO-06: Create a constructor for this bean.
    //  Inject a OpenAiChatModel object into the constructor.
    //  Pass the model to the ChatClient.builder to build a ChatClient object.
    //  Save the ChatClient object in the client field.
    public OpenAIClient(OpenAiChatModel model) {
        client = ChatClient.builder(model).build();
    }

    public String callApi(String input) {

        //  TODO-07: Use the client object to call the API.
        //  .prompt() creates a prompt to pass to the Model.class
        //  .user() sets the "user" message. Pass the input String parameter.
        //  .call() invokes the model.  It returns a CallResponse.
        //  .content() is a simple means of extracting String content from the response. 
        //  Have the method return the content of the response.
        return 
            client
                .prompt()
                .user(input)
                .call()
                .content();
    }

}