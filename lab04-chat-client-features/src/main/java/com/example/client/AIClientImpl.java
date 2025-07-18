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

    public AIClientImpl(ChatModel model

    ) {

        //  TODO-05: Define a variable named advisor of type MessageChatMemoryAdvisor.
        //  Initialize it by using the MessageChatMemoryAdvisor.builder() method.
        //  inject the builder() with the chatMemory parameter from the constructor.



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
                // The parameter key should be ChatMemory.CONVERSATION_ID.
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
