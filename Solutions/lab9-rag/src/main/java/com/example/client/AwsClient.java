package com.example.client;

import org.springframework.ai.bedrock.titan.BedrockTitanChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Profile("aws")
public class AwsClient implements AIClient {

    private ChatClient client;

    //  TODO-10: Create a constructor for this bean.
    //  Inject a ChatModel object into the constructor.
    //  Pass the model to the ChatClient.builder to build a ChatClient object.
    //  Save the ChatClient object in the client field.
    public AwsClient(ChatModel model) {
        client = ChatClient.builder(model).build();
    }


    public String getProductRecommendationsText(String input, List<String> products ) {

        //  TODO-11: Call the AIClient.buildPrompt() method with the input and products parameters.
        //  Use the return value to set a Prompt variable.
        Prompt prompt = AIClient.buildPrompt(input,products);

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
