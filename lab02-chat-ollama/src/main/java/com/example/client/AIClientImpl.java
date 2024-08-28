package com.example.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

//  TODO-05: Use a stereotype annotation to mark this class as a Spring bean.


public class AIClientImpl implements AIClient {
    
    private ChatClient client;

    //  TODO-06: Create a constructor for this bean.
    //  Inject a ChatModel object into the constructor.
    //  Pass the model to the ChatClient.builder to build a ChatClient object.
    //  Save the ChatClient object in the client field.


    public String callApi(String input ) {

        //  TODO-07: Use the client object to call the API.
        //  .prompt() creates a prompt to pass to the Model.class
        //  .user() sets the "user" message. Pass the input String parameter.
        //  .call() invokes the model.  It returns a CallResponse.
        //  .content() is a simple means of extracting String content from the response. 
        //  Have the method return the content of the response.
        return null; // (replace this line)
    
            
    }

}
