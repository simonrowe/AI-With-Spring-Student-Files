package com.example.client;

import java.util.List;


public interface AIClient {

    void save(List<String> products);
    String getProductRecommendations(String query);

//    public static Prompt buildPrompt(String query, List<String> recomendations) {
//
//        //   Implement the prompt String below.
//        //  The prompt should instruct the model to respond to the initial customer query given the product recommendations.
//        //  Begin with a CONTEXT - tell the model that it is a helpful product expert that provides recommendations.
//        //  Include an INSTRUCTION - tell the model to respond to the following customer request.
//        //  The INPUTS will be injected into the INITIAL_PROMPT and RECOMMENDATIONS placeholders from the given method parameters.
//        //  The OUTPUT should instruct the model to be brief, but provide details on each product, inluding URL.
//        String template =
//        """
//        You are a helpful product expert that provides product recommendations to customers.
//        Create a response to the following customer request:
//        "{INITIAL_PROMPT}".
//        Products that match the customers request are:
//        {RECOMMENDATIONS}
//        Your response should be brief, but should provide details on each matching product, including URL.
//        """;
//
//        Prompt prompt =
//            new PromptTemplate(template)
//                .create(Map.of(
//                    "INITIAL_PROMPT", query,
//                    "RECOMMENDATIONS", recomendations)
//                );
//        System.out.println(prompt.getContents());
//        return prompt;
//    }

}
