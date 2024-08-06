package com.example.client;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("openai")
public class EntityTests {
    
    record ActorFilms(String actor, List<String> movies) {}    
    record StateData(String capitalCity, int areaInSquareMiles, int population, String famousFor) {}    
        
    private ChatClient client;
    @Autowired OpenAiChatModel model;

    @Test
    void testEntity() {
        client = ChatClient.builder(model).build();
        ActorFilms actorFilms = client.prompt()
        .user("Generate the filmography for a random actor.")
        .call()
        .entity(ActorFilms.class);

        System.out.println("The actor is: " + actorFilms.actor());
        System.out.println("The films are: " + actorFilms.movies());
    
    }
    

    @Test
    void testEntity2() {
        client = ChatClient.builder(model).build();
        StateData stateData = client.prompt()
        .user("Provide information on one of the US 50 states.")
        .call()
        .entity(StateData.class);

        System.out.println(stateData);   
    }
        
}
    