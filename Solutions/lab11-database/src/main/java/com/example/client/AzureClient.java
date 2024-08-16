package com.example.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

//  TODO-05: Use a stereotype annotation to mark this class as a Spring bean.
//  Use an annotation to assign it to the "azure" profile.

@Component
@Profile("azure")
public class AzureClient implements AIClient {

    //  TODO-06: Autowire the AzureOpenAiChatModel bean.
    @Autowired AzureOpenAiChatModel model;

    public String generateSql(String input) {
        String systemPrompt = "You are an SQL generator.  Respond to the prompt with an SQL statement.  The responses must be 100 percent valid, executable SQL statements compatible with the HyperSQL database.  Double-check the SQL syntax to ensure it will be compatible with HyperSQL; do not use any SQL keywords or functions that are invalid on HyperSQL. The SQL statement must begin with SELECT and end with ;.  There must be no other words or characters before SELECT or after ;.  DO NOT include any words or characters before or after the SQL statement.  Use the following database schema to generate SQL queries: %s";
        String schema = readSchemaFile();
        String fullSystemPrompt = String.format(systemPrompt, schema);

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
        //  Print the response to the console and return it.
        String response =  
            client
                .prompt().user(input)
                .call()
                .content();

        System.out.println(response);                 
        return response;
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