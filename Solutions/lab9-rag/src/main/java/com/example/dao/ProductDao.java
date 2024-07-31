package com.example.dao;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    //  TODO-04: Autowire a VectorStore instance.
    @Autowired VectorStore vectorStore;

    //  TODO-05: Define a public void add() method.
    //  Define a single parameter of type List<String> containing product descriptions.
    //  Convert the List<String> into a List<Document>, where each product description String is used to create a Document.
    //  Call the vectorStore's add() method with the List<Document> to add the product descriptions to the VectorStore.
    public void add(List<String> products) {
        List<Document> documents = products.stream()
            .map(product -> new Document(product))
            .toList();
        vectorStore.add(documents);
    }

    //  TODO-06: Define a public List<String> findClosestMatches() method.
    //  Define two parameters: a String query and an int numberOfMatches.
    //  Use SearchRequest.query() to create a new SearchRequest with the input String.
    //  Use the withTopK() method to set the numberOfMatches.
    //  Call the vectorStore's similaritySearch() method with the SearchRequest to find the closest matches.
    //  Capture the result in a List<Document>.
    //  Convert the List<Document> into a List<String> and return.
    public List<String> findClosestMatches(String query,int numberOfMatches) {
        SearchRequest request = SearchRequest.query(query).withTopK(numberOfMatches);
        List<Document> results = vectorStore.similaritySearch(request);
        return results.stream()
            .map(doc -> doc.getContent())
            .toList();
    }

}
