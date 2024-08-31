package com.example.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

//  TODO-04: Use a stereotype annotation to mark this class as a Spring bean.
@Component
public class AIClient {

    //  TODO-05: Autowire the ChatModel bean.
    @Autowired ChatModel model;


    public String generateSql(String input) {

        //  TODO-06: Observe the system message below.
        //  It provides direct instructions for the model to generate SQL queries.
        //  Notice that the SQL statement is expected to be returned within <SQL> and </SQL> tags.
        //  Notice the database schema is provided within the message.
        String systemMessage = 
        """
        You are an SQL generating web service.
        Responses must be valid, HyperSQL-compatible, executable SQL statements.  
        HyperSQL uses DATE_ADD ( xxxx, INTERVAL X DAY ) for date arithmetic, and CURRENT_DATE to get today's date.
        The SQL statement must be placed between <SQL> and </SQL> tags.
        Do not include any other superflous text in the response.
        Use the following database schema to generate SQL queries: %s
        """;        

        String schema = readSchemaFile();
        String fullSystemPrompt = String.format(systemMessage, schema);

        //  TODO-07: Create a chatClient.
        //  Pass the model to the ChatClient.builder to build a ChatClient object.
        //  Use .defaultSystem() to set the system-level prompt to "fullSystemPrompt" defined above.
        ChatClient client =
            ChatClient.builder(model)
                .defaultSystem(fullSystemPrompt)
                .build();

        //  TODO-08: Use the client object to call the API.
        //  The .prompt().user() method can be used to set the user-level prompt from the input parameter.
        //  The .call() method will make the call to the model.
        //  The .content() method will return the content of the response.
        //  Capture the response in a String variable.
        //  Pass the String response to the extractSql() method and return the results.
        String response =  
            client
                .prompt().user(input)
                .call()
                .content();

        System.out.println(response);
        return extractSql(response);
    }

    public String summarize(String userMessage, String supportingData) {

        //  TODO-09: Observe the system message below.
        //  It provides direct instructions for the model to produce executive summaries.
        String systemMessage =
            "You are a web service which specializes in executive summaries.";

        //  TODO-10: Create a chatClient.
        //  Pass the model to the ChatClient.builder to build a ChatClient object.
        //  Use .defaultSystem() to set the system-level prompt to "systemMessage" defined above.
        ChatClient client =
            ChatClient.builder(model)
                .defaultSystem(String.format(systemMessage))
                    .build();

        String fullUserMessage = userMessage + ".  Supporting data:  " + supportingData;

        //  TODO-11: Use the client object to call the API.
        //  The .prompt().user() method can be used to set the "fullUserMessage" defined above.
        //  The .call() method will make the call to the model.
        //  The .content() method will return the content of the response.
        //  return the response.

        String response = client
            .prompt().user(fullUserMessage)
            .call()
            .content();

        System.out.println(response);
        return response;
    }


    private String extractSql(String response) {
        assert response.contains("<SQL>") && response.contains("</SQL>");

        // Extract the SQL statement from the response:
        return response.substring(
                response.indexOf("<SQL>") + "<SQL>".length(),
                response.indexOf("</SQL>"));
    }


    @Autowired ResourceLoader resourceLoader;

    //  Utility function for reading the DB schema file.
    private String readSchemaFile() {
        StringBuilder schema = new StringBuilder();
        try {
            Resource resource = resourceLoader.getResource("classpath:schema.sql");
            try (InputStream inputStream = resource.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    schema.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schema.toString();
    }
}
