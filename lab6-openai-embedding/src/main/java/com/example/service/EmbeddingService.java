package com.example.service;

import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Profile("openai-embedding")
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    @Autowired
    public EmbeddingService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public Map lookup(String message) {
        if (message == null) message = "What are the different services that you offer?";

        EmbeddingResponse embeddingResponse = embeddingModel.embedForResponse(List.of(message));
        return Map.of("embedding", embeddingResponse);
    }

    public String findClosestMatch(String query, List<String> products) {
        EmbeddingResponse response = null;
        response = embeddingModel.embedForResponse(products);
        List<Embedding> productEmbeddings = response.getResults();
        response = embeddingModel.embedForResponse(List.of(query));
        Embedding queryEmbedding = response.getResults().get(0);

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

        return products.get(mostSimilarIndex);
    }


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

    static double cosine_similarity(Map<String, Double> v1, Map<String, Double> v2) {
        Set<String> both = new HashSet(v1.keySet());
        both.retainAll(v2.keySet());
        double sclar = 0, norm1 = 0, norm2 = 0;
        for (String k : both) sclar += v1.get(k) * v2.get(k);
        for (String k : v1.keySet()) norm1 += v1.get(k) * v1.get(k);
        for (String k : v2.keySet()) norm2 += v2.get(k) * v2.get(k);
        return sclar / Math.sqrt(norm1 * norm2);
    }
}
