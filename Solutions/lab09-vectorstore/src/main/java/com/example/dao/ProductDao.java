package com.example.dao;

import java.util.List;

public interface ProductDao {

    void add(List<String> products);
    List<String> findClosestMatches(String query,int numberOfMatches);
    String findClosestMatch(String query);

}
