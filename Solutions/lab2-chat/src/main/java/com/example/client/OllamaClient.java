package com.example.client;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//  TODO-05: Use a stereotype annotation to mark this class as a Spring bean.
//  Use an annotation to assign it to the "ollama" profile.

@Component
@Profile("ollama")
public class OllamaClient implements AIClient {
    
    private ChatClient client;

    //  TODO-06: Create a constructor for this bean.
    //  Inject a ChatModel object into the constructor.
    //  Pass the model to the ChatClient.builder to build a ChatClient object.
    //  Save the ChatClient object in the client field.
    public OllamaClient(ChatModel model) {
        client = ChatClient.builder(model).build();
    }

    @Override
    public String callApi(String input) {

        // if (model==null) {
        //     model = "llama2";
        // }   

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
