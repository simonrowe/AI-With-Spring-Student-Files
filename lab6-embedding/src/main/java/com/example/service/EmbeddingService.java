package com.example.service;

import java.util.List;

import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Profile("openai-embedding")
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    @Autowired
    public EmbeddingService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public String findClosestMatch(String query, List<String> products) {
        EmbeddingResponse response = null;

        //  Have the AI model turn our product descriptions into embeddings:
        response = embeddingModel.embedForResponse(products);
        List<Embedding> productEmbeddings = response.getResults();

        //  Have the AI model turn our query into an embedding:
        response = embeddingModel.embedForResponse(List.of(query));
        Embedding queryEmbedding = response.getResults().get(0);

        //  Find the product with the highest cosine similarity to the query:
        int mostSimilarIndex = -1;
        double mostSimilarScore = -1;
        for (int i = 0; i < productEmbeddings.size(); i++) {
            Embedding productEmbedding = productEmbeddings.get(i);
            double similarity = cosineSimilarity(queryEmbedding.getOutput(), productEmbedding.getOutput());
            if (similarity > mostSimilarScore) {
                mostSimilarScore = similarity;
                mostSimilarIndex = i;
            }
        }

        if(mostSimilarIndex < 0) {
            return "No similar product found";
        } else {
            return products.get(mostSimilarIndex);
        }
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
