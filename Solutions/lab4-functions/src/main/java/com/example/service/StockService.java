package com.example.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.example.client.AIClient;

//	TODO-11: Annotate this class as a Spring bean using the @Service annotation.
//  Set its name to "stockService" to align with the callback function from the earlier step.
//  Use the @Description annotation to provide a helpful description for the OpenAI Client object.

@Service("stockService")
@Description("Service to get stock information")	//	Helpful description for the OpenAI Client object.
public class StockService implements Function<com.example.service.StockService.Request, com.example.service.StockService.Response> {

	public static Double price = Math.round(Math.random()*800)/100.0;
	public static Integer volume = (int) (Math.random() * 999 + 1);


	//	TODO-12: Notice the pre-defined records for Request and Response.
	//  These structures will be understood by the OpenAI client software.
	//  Notice the apply() method, this will be invoked by the OpenAI client
	//	automatically when the response is returned from the server:
	
    public record Request(String symbol) {}
    public record Response(String symbol, Double price, Integer volume, String currency) {}

	public Response apply(Request request) {
		//	For now, just return a hard-coded response:
		return new Response(request.symbol(),price,volume, "USD");
	}    

    @Autowired AIClient client;
	
	public String getCompanySummary(String symbol) {
		return client.callApi(
			"Provide a description of the company with stock ticker symbol '%s'".formatted(symbol)
		);
	}

}
 