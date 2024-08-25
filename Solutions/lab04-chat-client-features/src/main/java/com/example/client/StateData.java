package com.example.client;

//  TODO-11:  Add additional fields to the StateData record.
//  Add whatever fields you might like to know about a given state.
//  Examples might include:  
//  area in square miles, population, 
//  famous for, state bird, state flower, state motto, etc.

public record StateData(
    String stateName, 
    String capitalCity, 
    int areaInSquareMiles, 
    int population, 
    String famousFor
    ) {}
