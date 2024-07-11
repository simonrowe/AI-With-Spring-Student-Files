package com.example.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    @Autowired JdbcTemplate template;

    public List<Map<String,Object>> adHocQuery(String sql) {
        List<Map<String,Object>> results = template.queryForList(sql);
        System.out.println(results);                 
        return results;
    }

}
