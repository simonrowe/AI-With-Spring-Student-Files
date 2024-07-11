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

    public List<Map<String,Object>> adHocQuery(String query) {
        return 
            productDao.adHocQuery(
                aiClient.generateSql(query));
    }

}
