package com.example.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//  TODO-02: Adjust the @ActiveProfile defined below to match the chat model you plan to use.
//  For Amazon Bedrock models,  use "aws".
//  For Azure-hosted OpenAI,    use "azure".
//  For standard OpenAI,        use "openai".
//  For Ollama,                 use "ollama".
@SpringBootTest
@ActiveProfiles("xxxxx")
public class AIClientImplTests {

    @Autowired AIClient client;

    //  TODO-03: Examine the test logic below.  It conducts two separate conversations.
    //  One conversation discusses the Great Lakes.  The other discusses planets.
    //  Remove the @Disabled annotation from the test method.  Run the test.
    //  We expect the test to FAIL.  Do you understand why?
    //  Move on to the next step to fix the error.

    @Test
    @Disabled
    void testConversationalChat() {

        int conversation1Id = 111;
        String conversation1Prompt1 = "List out the names of the Great Lakes.";
        String conversation1Prompt2 = "Which one is the deepest?";
        String[] conversation1Results1 = {"Huron", "Ontario", "Michigan", "Erie", "Superior"};
        String[] conversation1Results2 = {"Superior"};

        int conversation2Id = 222;
        String conversation2Prompt1 = "List out the planets in the solar system.";
        String conversation2Prompt2 = "Which one has the greatest mass?";
        String[] conversation2Results1 = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        String[] conversation2Results2 = {"Jupiter"};


        String response1 = client.conversationalChat(conversation1Prompt1, conversation1Id);
        System.out.println(response1);
        assertThat(response1).contains(conversation1Results1);

        String response2 = client.conversationalChat(conversation2Prompt1, conversation2Id);
        System.out.println(response2);
        assertThat(response2).contains(conversation2Results1);
       
        response1 = client.conversationalChat(conversation1Prompt2, conversation1Id);
        System.out.println(response1);
        assertThat(response1).contains(conversation1Results2);

        response2 = client.conversationalChat(conversation2Prompt2, conversation2Id);
        System.out.println(response2);
        assertThat(response2).contains(conversation2Results2);

    }

    //  TODO-10: Examine the test logic below.  It retrieves data about a US State (or any state).
    //  Run the test.  It should pass. 
    //  Move on to the next step to add to the information retrieved about each state. 

    @Test
    public void testRetrieve() {

        StateData stateData = client.retrieve("Michigan");
        assertThat(stateData.stateName()).isEqualTo("Michigan");
        assertThat(stateData.capitalCity()).isEqualTo("Lansing");

        //  TODO-12: Add additional assertions to this method to verify that 
        //  the entity object is populated with the extra fields you established in the last step.
        //  Run the test again.  It should pass. 

        
        
    }
}
