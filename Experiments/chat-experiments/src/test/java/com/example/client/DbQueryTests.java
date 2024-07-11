package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("openai")
public class DbQueryTests {

    private ChatClient client;
    @Autowired OpenAiChatModel model;

    @Test
    void testQuery() {
        String systemPrompt = "You are an SQL generator.  Responses must be 100 percent valid, executable SQL statements.  You must not include any words or characters that are not part of an SQL statement.  Use the following database schema to generate SQL queries: %s";
        String schema = readSchemaFile();

        client = 
            ChatClient.builder(model)
                .defaultSystem(String.format(systemPrompt, schema))
                .build();

        String response =
            client
                .prompt().user("List the sales of the top 3 products by revenue during the last month.")
                .call()
                .content();
    
        System.out.println(response);                 

        List<Map<String,Object>> results = 
            template.queryForList(response);
    
        System.out.println(results);                 
    }

    @Autowired JdbcTemplate template;


    @Autowired
    ResourceLoader resourceLoader;

    private String readSchemaFile() {
        try {
            return new ClassPathResource("schema.sql").getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // StringBuilder schema = new StringBuilder();
        // try {
        //     Resource resource = resourceLoader.getResource("classpath:schema.sql");
        //     try (InputStream inputStream = resource.getInputStream();
        //          BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
        //         String line;
        //         while ((line = reader.readLine()) != null) {
        //             schema.append(line).append("\n");
        //         }
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // return schema.toString();
    }
}
