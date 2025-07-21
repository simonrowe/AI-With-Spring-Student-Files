package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.example.client.AIClient;

//	TODO-04: Annotate this class as a Spring bean using the @Service annotation.
//  Briefly examine the logic; it contains hard-coded information on various stocks and their prices.
//	We will use this as an example of information that is not known to the AI model.

public class StockService {

	public record Stock(Double price, Integer volume) {}

	// Hard-coded table of Stock prices and volumes:
	private static final Map<String, Stock> stockTable = Map.of(
		"NVDA", new Stock(Math.round(Math.random()*800)/100.0, (int) (Math.random() * 999 + 1)),
		"TSLA", new Stock(Math.round(Math.random()*700)/100.0, (int) (Math.random() * 999 + 1)),
		"AAPL", new Stock(Math.round(Math.random()*600)/100.0, (int) (Math.random() * 999 + 1)),
		"AMZN", new Stock(Math.round(Math.random()*500)/100.0, (int) (Math.random() * 999 + 1))
	);

	public Double getStockPrice(String symbol) {
		//  Look up the stock price and volume in the hard-coded table:
		Stock stock = stockTable.get(symbol);
		if (stock == null) {
			throw new IllegalArgumentException("Unknown stock symbol: " + symbol);
		}
		return stock.price;
	}

	public Integer getStockVolume(String symbol) {
		//  Look up the stock price and volume in the hard-coded table:
		Stock stock = stockTable.get(symbol);
		if (stock == null) {
			throw new IllegalArgumentException("Unknown stock symbol: " + symbol);
		}
		return stock.volume;
	}

	public Double calculatePortfolioValue(List<StockTools.Request> stocks) {
		double totalValue = 0.0;
		for (StockTools.Request stock : stocks) {
			totalValue += stock.quantity() * stock.price();
		}
		return Math.round(totalValue * 100.0) / 100.0; // Round to two decimal places
	}

	public String calculatePortfolioValueFormatted(List<StockTools.Request> stocks) {
		double totalValue = this.calculatePortfolioValue(stocks);
		return String.format("$%,.2f", totalValue);
	}

}
 