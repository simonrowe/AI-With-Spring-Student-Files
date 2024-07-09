package com.example.dao;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("redis-vector-store")
public class ProductVectorDao {

    @Autowired VectorStore vectorStore;

    //  Add the given list of Product description
    //  Strings to the (Redis) Vector Store:
    public void add(List<String> products) {

        List<Document> documents = products.stream()
            .map(product -> new Document(product))
            .toList();

        vectorStore.add(documents);
    }

    //  Find the closest matching Product descriptions
    public List<String> findClosestMatches(String query) {

        List<Document> results = vectorStore.similaritySearch(
            SearchRequest.query(query).withTopK(5)
        );

        return results.stream()
            .map(doc -> doc.getContent())
            .toList();
    }
}
