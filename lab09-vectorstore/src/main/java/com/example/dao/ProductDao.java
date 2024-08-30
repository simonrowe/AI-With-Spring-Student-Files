package com.example.dao;

//  TODO-03: Use a stereotype annotation to mark this DAO as a Spring bean.


public class ProductDao {

    //  TODO-04: Autowire a VectorStore instance.


    //  TODO-05: Define a public void add() method.
    //  Define a single parameter of type List<String> containing product descriptions.
    //  Convert the List<String> into a List<Document>, where each product description String is used to create a Document.
    //  Call the vectorStore's add() method with the List<Document> to add the product descriptions to the VectorStore.


    //  TODO-06: Define a public List<String> findClosestMatches() method.
    //  Define two parameters: a String query and an int numberOfMatches.
    //  Use SearchRequest.query() to create a new SearchRequest with the input String.
    //  Use the withTopK() method to set the numberOfMatches.
    //  Call the vectorStore's similaritySearch() method with the SearchRequest to find the closest matches.
    //  Capture the result in a List<Document>.
    //  Convert the List<Document> into a List<String> and return.



    //  TODO-07: Define a public String findClosestMatch() method.
    //  Define a single parameter of type String containing a query.
    //  Call the findClosestMatches() method with the query and 1 as the number of matches.


}
