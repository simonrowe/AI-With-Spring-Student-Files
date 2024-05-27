package com.example.client;

public interface AIClient {
    public String callModel(String prompt );
    public String callModel(String prompt, String model );
}
