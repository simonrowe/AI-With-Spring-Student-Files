package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProductDao;

//  TODO-08: Use a stereotype annotation to mark this service as a Spring bean.
@Service
public class ProductService {

    //  TODO-09: Autowire a ProductDao instance.
    @Autowired ProductDao dao;


    //  TODO-10: Define a public void save() method.
    //  Define a single parameter of type List<String> containing product descriptions.
    //  Call the dao's add() method with the List<String> to save the product descriptions.
    public void save(List<String> products) {
        dao.add(products);
    }

    //  TODO-11: Define a public List<String> findClosestMatches() method.
    //  Define a single parameter of type String containing a query.
    //  Call the dao's findClosestMatches() method with the query and 5 as the number of matches.
    //  Return the resulting List<String>.
    public List<String> findClosestMatches(String query) {
        return dao.findClosestMatches(query,5);
    }

    //  TODO-12: Define a public String findClosestMatch() method.
    //  Define a single parameter of type String containing a query.
    //  Call the dao's findClosestMatch() method with the query.
    //  Return the resulting String.
    public String findClosestMatch(String query) {
        return dao.findClosestMatch(query);
    }

}
