package com.example.client;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@ActiveProfiles("openai")
@ActiveProfiles("aws")
public class StateTests {

    private ChatClient client;
    @Autowired OpenAiChatModel model;

    @Test
    void testCallback() {

        InMemoryChatMemory memory = new InMemoryChatMemory(); 

        client = ChatClient
            .builder(model)
            .defaultAdvisors(
                new MessageChatMemoryAdvisor (memory)
            )
            .build();

        String response = client.prompt()
            .user("List out the names of the Great Lakes.")
            .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, 111))
            .call()
            .content();
        
        System.out.println(response);
        // use assertJ to assert that the response contains the word "price" and "$":
        assertThat(response).contains("Superior", "Huron", "Michigan", "Erie", "Ontario");
   
        //  Execute another test to investigate whether the chat client is aware of state.
        response = client.prompt()
        .user("Which one is the biggest by surface area?")
        .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, 111))
        .call()
        .content();
        
        System.out.println(response);

        //////////////////
        //  SAME CLIENT, DIFFERENT CONVERSATION ID.
        response = client.prompt()
        .user("List out some of the best known lakes in Florida.")
        .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, 222))
        .call()
        .content();
    
        System.out.println(response);

        //  Execute another test to investigate whether the chat client is aware of state.
        response = client.prompt()
        .user("Which one is the biggest by surface area?")
        .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, 222))
        .call()
        .content();
        
        System.out.println(response);

    }
        
}
