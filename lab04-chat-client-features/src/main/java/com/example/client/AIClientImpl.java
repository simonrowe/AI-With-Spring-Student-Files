package com.example.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

@Component
public class AIClientImpl implements AIClient {
    
    private ChatClient client;

    //  TODO-04: Define a member variable of type InMemoryChatMemory.
    //  Initialize it with a new instance of InMemoryChatMemory.

    String conversationKey = AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

    //  TODO-05: Defined a member variable of type MessageChatMemoryAdvisor.
    //  Initialize it with a new instance of MessageChatMemoryAdvisor 
    //  injected with the InMemoryChatMemory instance defined above. 

    public AIClientImpl(ChatModel model) {
        client = ChatClient
            .builder(model)

            //  TODO-06: Alter this ChatClient.
            //  Use the .defaultAdvisors() method to add the 
            //  MessageChatMemoryAdvisor defined above.

            .build();
    }


    public String conversationalChat(String input, int conversationId){
        return 
            client
                .prompt()
                .user(input)

                // TODO-07:  Use the .advisors() method to inject an AvisorSpec Consumer to this call.
                // The advisors() method will accept a Lambda expression that implements the Consumer.
                // The parameter to the Consumer is an AdvisorSpec.
                // Use the AdvisorSpec's .param() method to add a parameter identifying the conversation.
                // The parameter key should be the conversationKey defined above.
                // The parameter value should be the conversationId passed to this method.

                .call()
                .content();
    }

    // TODO-08:  Return to the @Test method in AIClientImplTests.
    // Re-run the test.  It should now pass.
    

    public StateData retrieve(String input) {
        return
           client
                .prompt()
                .user(input)
                .call()
                .entity(StateData.class);
    }

 
}
