package com.example.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

@Component
public class AIClientImpl implements AIClient {
    
    private ChatClient client;

    //  TODO-04: Alter the signature of the constructor below.
    //  Add an additional parameter named chatMemory of type ChatMemory.
    //  (ChatMemory abstraction maintains contextual awareness throughout a conversation.)

    public AIClientImpl(ChatModel model, ChatMemory chatMemory) {
        client = ChatClient
            .builder(model)
            .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
            .build();
    }


    public String conversationalChat(String input, int conversationId){
        return 
            client
                .prompt()
                .user(input)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }

    // TODO-08:  Return to the @Test method in AIClientImplTests.
    // Re-run the test.  It should now pass.
    

    public StateData retrieve(String input) {
        return
           client
                .prompt()
                .system("Provide information about the requested state. Return valid JSON only. Use plain numbers without underscores or separators for numeric values.")
                .user("Provide detailed information about the state: " + input)
                .call()
                .entity(StateData.class);
    }

 
}
