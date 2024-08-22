package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.AIClient;
import com.example.dao.ProductDao;

@Service
public class ProductService {

    @Autowired AIClient aiClient;
    @Autowired ProductDao productDao;

    public String productQuery(String userQuery) {

        //  TODO-12: Call the AIClient to generate the SQL query.
        //  Pass the query parameter to the generateSql() method:
        String sql = aiClient.generateSql(userQuery);

        //  TODO-13: Call the ProductDao to execute the SQL query.
        //  Pass the SQL query generated above.
        //  Capture the results in a List<Map<String,Object>> variable.
        List<Map<String,Object>> results = productDao.adHocQuery(sql);

        //  TODO-14: Remove the "return null;" statement.
        //  Call the AIClient to summarize the results.
        //  Pass 1) the original user query parameter and 2) the results from the previous call, converted to String.
        //  Return the results of the summarize() method.
        return aiClient.summarize(userQuery, results.toString());
        //return null;
    }

}
