package com.example.client;

import org.springframework.ai.bedrock.anthropic3.Anthropic3ChatOptions;
import org.springframework.ai.bedrock.anthropic3.BedrockAnthropic3ChatModel;
import org.springframework.ai.bedrock.anthropic3.api.Anthropic3ChatBedrockApi;
import org.springframework.ai.bedrock.titan.BedrockTitanChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("aws")
public class AwsClient {

    private ChatClient client;

    //  Inject the builder created by ChatClientAutoConfiguration.
    //  It is already configured with the TitanChatBedrockApi and TitanChatBedrockModel.
    //  Use it to build the client.
    public AwsClient(ChatClient.Builder builder) {
        this.client = builder.build();
    }

    public String callModel(String input ) {

        //  Prepare a prompt with input and default options:
        Prompt prompt = new Prompt(
            input,
            BedrockTitanChatOptions.builder().build()
        );

        return client.prompt(prompt).call().content();
    }

}
