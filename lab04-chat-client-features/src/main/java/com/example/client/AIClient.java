package com.example.client;


public interface AIClient {
    String conversationalChat(String input, int conversationId);
    StateData retrieve(String input); 

}
