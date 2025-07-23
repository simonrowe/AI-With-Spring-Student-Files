package com.example.client;

import com.example.service.StockTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

//  TODO-06: Use @Component to mark this class as a Spring bean.

public class AIClientImpl implements AIClient {

	private StockTools tools;
    private ChatClient client;

    public AIClientImpl(ChatModel model, StockTools tools) {
		this.tools = tools;
		//  TODO-07: Pass the ChatModel to the ChatClient.builder to build a ChatClient object.
		//  Save the ChatClient object in the client field.


    }

    public String callApi(String input ) {

		//  TODO-08: Use the client object to call the API.
        //  The .prompt() method can be used to set the prompt using the input parameter.
		//  The .tools() method will inject the tools bean so they are available to the model.
        //  The .call() method will make the call to the model.
        //  The .content() method will return the content of the response.
        //  Have the method return the content of the response.
		return null; // replace this.
    }

}