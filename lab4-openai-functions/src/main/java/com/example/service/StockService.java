package com.example.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.example.client.AIClient;

@Service("stockService")
@Description("Service to get stock information")	//	Helpful description for the OpenAI Client object.
public class StockService implements Function<com.example.service.StockService.Request, com.example.service.StockService.Response> {

    public record Request(String symbol) {}
    public record Response(String symbol, Double price, String currency) {}

	public Response apply(Request request) {
		//	For now, just return a hard-coded response:
		double price = Math.random()*800;
		return new Response(request.symbol(),price, "USD");
	}    

    @Autowired AIClient client;
	
	public String getCompanySummary(String symbol) {
		return client.callModel(
			"Provide a description of the company with stock ticker symbol '%s'".formatted(symbol)
		);
	}

}
 