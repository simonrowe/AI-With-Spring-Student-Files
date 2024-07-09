package com.example.service;

import java.util.List;

import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//  TODO-04: Use a stereotype annotation to mark this class as a Spring bean.
//  Use an annotation to assign it to the "embedding" profile.
@Service
@Profile("embedding")
public class EmbeddingService {

    //  TODO-05: Define a private final field named "model" of type EmbeddingModel.
    //   Define a constructor which dependency injects this field:
    private final EmbeddingModel model;
    public EmbeddingService(EmbeddingModel model) {
        this.model = model;
    }

    public String findClosestMatch(String query, List<String> products) {

        EmbeddingResponse response = null;
        List<Embedding> productEmbeddings = null;
        Embedding queryEmbedding = null;


        //  TODO-06: Use the AI model to turn our product descriptions into Embeddings.
        //  Pass the incoming products list to the model's embedForResponse method.
        //  Use the response to populate the productEmbeddings List:
        response = model.embedForResponse(products);
        productEmbeddings = response.getResults();


        //  TODO-07: Use the AI model to turn our query into a single Embedding.
        //  Pass the query string to the model's embedForResponse method.
        //  Use the single result from the response to populate the queryEmbedding variable.
        response = model.embedForResponse(List.of(query));
        queryEmbedding = response.getResults().get(0);


        //  TODO-08 find the product description most relevant to the query.
        //  Call the findColosestMatch() method with the correct arguments.
        //  Store the returned integer in the mostSimilarIndex variable.
        //  If the returned index < 0, return "No similar product found"
        //  Otherwise, get the item in the products list matching this index.
        int mostSimilarIndex = -1;
        mostSimilarIndex = findColosestMatch(queryEmbedding, productEmbeddings);
        
        if(mostSimilarIndex < 0) {
            return "No similar product found";
        } else {
            return products.get(mostSimilarIndex);
        }
    }


    //  TODO-09: Examine the logic below, there is nothing you need to change.
    //  This method compares the query embedding to each product embedding to find the most similarity.
    //  The Cosine similarity algorithm measures the similarity between two vectors.
    //  The index of the product with the highest similarity is returned.
    public static int findColosestMatch(Embedding query, List<Embedding> products) {
        int mostSimilarIndex = -1;
        double mostSimilarScore = -1;
        for (int i = 0; i < products.size(); i++) {
            Embedding productEmbedding = products.get(i);
            double similarity = cosineSimilarity(query.getOutput(), productEmbedding.getOutput());
            if (similarity > mostSimilarScore) {
                mostSimilarScore = similarity;
                mostSimilarIndex = i;
            }
        }
        return mostSimilarIndex;
    }


    //  Calculate the cosine similarity between two vectors:
    //  This is a way to measure the similarity between two vectors of numbers.
    public static double cosineSimilarity(List<Double> vectorA, List<Double> vectorB) {
        if (vectorA.size() != vectorB.size()) {
            throw new IllegalArgumentException("Vectors must have the same length");
        }

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vectorA.size(); i++) {
            double a = vectorA.get(i);
            double b = vectorB.get(i);
            dotProduct += a * b;
            normA += a * a;
            normB += b * b;
        }

        // Handle cases where either norm is zero (to avoid division by zero)
        if (normA == 0 || normB == 0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }


}
