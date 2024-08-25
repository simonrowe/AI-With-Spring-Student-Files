package com.example.client;

public record StateData(
    String stateName, 
    String capitalCity, 
    int areaInSquareMiles, 
    int population, 
    String famousFor
    ) {}
