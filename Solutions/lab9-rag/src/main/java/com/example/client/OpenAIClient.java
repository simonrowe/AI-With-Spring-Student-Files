package com.example.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("openAIClientBean")
@Profile("openai")
public class OpenAIClient implements AIClient {

    private ChatClient client;

    //  TODO-10: Create a constructor for this bean.
    //  Inject a ChatModel object into the constructor.
    //  Pass the model to the ChatClient.builder to build a ChatClient object.
    //  Save the ChatClient object in the client field.
    public OpenAIClient(ChatModel model) {
        client = ChatClient.builder(model).build();
    }

    public String getProductRecommendationsText(String input, List<String> products) {

        //  TODO-11: Call the AIClient.buildPrompt() method with the input and products parameters.
        //  Use the return value to instantiate a new Prompt() object.
        Prompt prompt = new Prompt( AIClient.buildPrompt(input,products) );

        //  TODO-12: Use the client object to call the API.
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