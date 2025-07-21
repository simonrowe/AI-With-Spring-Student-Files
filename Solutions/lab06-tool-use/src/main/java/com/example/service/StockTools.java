package com.example.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

//	TODO-05: Annotate this class as a Spring bean using the @Service annotation.
//  This class represents specific tools that will be made available to the AI model.
//  Notice the function signatures, Record types, and the @Tool annotations;
//  Spring AI will share this metadata with the AI model,
//  allowing it to understand how to call these functions.
@Service
public class StockTools {

    private final StockService service;
    public StockTools(StockService service) {
        this.service = service;
    }

    public record Request(Integer quantity, Double price) {}
    public record Response(String symbol, Double price, Integer volume, String currency) {}

    @Tool(description = "Returns current details for a given stock ticker symbol")
    public Response getStockInfo(String symbol) {
       return new Response(
           symbol,
           service.getStockPrice(symbol),
           service.getStockVolume(symbol),
           "USD");
    }

    @Tool(description = "Calculates the total value of a portfolio of stocks")
    public Double calculatePortfolioValue(List<Request> stocks) {
        return service.calculatePortfolioValue(stocks);
    }
}
