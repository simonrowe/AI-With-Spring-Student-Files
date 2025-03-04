package com.example.dao;

//  TODO-03: Use a stereotype annotation to mark this DAO as a Spring bean.
//  Alter this class to implement ProductDao.


public class ProductDaoImpl {

    //  TODO-04: Autowire a VectorStore instance.


    //  TODO-05: Define the public void add() method.
    //  Define a single parameter of type List<String> containing product descriptions.
    //  Convert the List<String> into a List<Document>, where each product description String is used to create a Document.
    //  Call the vectorStore's add() method with the List<Document> to add the product descriptions to the VectorStore.


    //  TODO-06: Define the public List<String> findClosestMatches() method.
    //  Define two parameters: a String query and an int numberOfMatches.
    //  Use the SearchRequest.builder() method to create a new SearchRequest.
    //    Use the builder's query() method to set the input query String.
    //    Use the builder's topK() method to set the input numberOfMatches.
    //  Call the vectorStore's similaritySearch() method with the SearchRequest to find the closest matches.
    //  Capture the result in a List<Document>.
    //  Convert the List<Document> into a List<String> and return.


    //  TODO-07: Define the public String findClosestMatch() method.
    //  Define a single parameter of type String containing a query.
    //  Call the findClosestMatches() method with the query and 1 as the number of matches.


}
