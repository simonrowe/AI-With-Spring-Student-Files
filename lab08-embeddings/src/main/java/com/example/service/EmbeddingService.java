package com.example.service;

import java.util.List;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

//  TODO-04: Use a stereotype annotation to mark this class as a Spring bean.

public class EmbeddingService {

    //  TODO-05: Define a private final field named "model" of type EmbeddingModel.
    //   Define a constructor which dependency injects this field:


    public String findClosestMatch(String query, List<String> products) {

        List<float[]> productEmbeddings = null;
        float[] queryEmbedding = null;


        //  TODO-06: Use the AI model to turn our product descriptions into Embeddings.
        //  Pass the incoming products list to the model's embed method.
        //  Use the response to populate the productEmbeddings List:
    

        //  TODO-07: Use the AI model to turn our query into a single Embedding.
        //  Pass the query string to the model's embed method.
        //  Use the response to populate the queryEmbedding array.
    

        //  TODO-08 find the product description most relevant to the query.
        //  Call the findColosestMatch() method with the correct arguments.
        //  Store the returned integer in the mostSimilarIndex variable.
        //  If the returned index < 0, return "No similar product found"
        //  Otherwise, get the item in the products list matching this index.

        return null; //  Replace this line with your code.
    }


    //  TODO-09: Examine the logic below, there is nothing you need to change.
    //  This method compares the query embedding to each product embedding to find the most similarity.
    //  The Cosine similarity algorithm measures the similarity between two vectors.
    //  The index of the product with the highest similarity is returned.
    public static int findColosestMatch(float[] query, List<float[]> products) {
        int mostSimilarIndex = -1;
        double mostSimilarScore = -1;
        for (int i = 0; i < products.size(); i++) {
            float[] productEmbedding = products.get(i);
            double similarity = cosineSimilarity(query, productEmbedding);
            if (similarity > mostSimilarScore) {
                mostSimilarScore = similarity;
                mostSimilarIndex = i;
            }
        }
        return mostSimilarIndex;
    }


    //  Calculate the cosine similarity between two vectors:
    //  This is a way to measure the similarity between two vectors of numbers.
    public static double cosineSimilarity(float[] vectorA, float[] vectorB) {
        if (vectorA.length != vectorB.length) {
            throw new IllegalArgumentException("Vectors must have the same length");
        }

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vectorA.length; i++) {
            double a = vectorA[i];
            double b = vectorB[i];
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
