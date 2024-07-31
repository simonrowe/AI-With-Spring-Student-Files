package com.example.service;

import java.util.List;

import com.example.client.AIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProductDao;

//  TODO-09: Use a stereotype annotation to mark this service as a Spring bean.
@Service
public class ProductService {

    //  TODO-10: Autowire a ProductVectorDao instance and a AIClient instance.
    @Autowired ProductDao dao;
    @Autowired AIClient client;


    //  TODO-11: Define a public void save() method.
    //  Define a single parameter of type List<String> containing product descriptions.
    //  Call the dao's add() method with the List<String> to save the product descriptions.
    public void save(List<String> products) {
        dao.add(products);
    }

    //  TODO-12: Define a public String getProductRecommendationsText() method.
    //  Define a single parameter of type String containing a query.
    //  Call the dao's findClosestMatches() method with the query and 2 as the number of matches.
    //  Pass the result to the private buildPrompt() method which we will define next.
    //  Call the AIClient's getProductRecommendationsText() method with the resulting prompt.
    //  Return the resulting String.
    public String getProductRecommendationsText(String query) {
        List<String> results = dao.findClosestMatches(query,2);
        return client.getProductRecommendationsText(query, results);
    }

}
