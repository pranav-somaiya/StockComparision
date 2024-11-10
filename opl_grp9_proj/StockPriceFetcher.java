package com.example.opl_grp9_proj;

import java.util.ArrayList;
import java.util.List;

public class StockPriceFetcher {

    private AlphaVantageAPI alphaVantageAPI = new AlphaVantageAPI();

    // Method to fetch real stock data for a given stock symbol from Alpha Vantage API
    public List<StockData> fetchStockData(String stockSymbol) {
        // Fetch stock data using AlphaVantageAPI (real-time data)
        List<StockData> stockDataList = new ArrayList<>();
        stockDataList.add(alphaVantageAPI.fetchStockData(stockSymbol)); // Real stock data for the given symbol
        return stockDataList;
    }

    // Method to simulate fetching stock data with time intervals (typically 15-minute intervals from 9:30 AM to 4:00 PM)
    public List<StockData> fetchSimulatedStockData() {
        // Example time intervals for a single trading day (9:30 AM to 4:00 PM, with 15-minute intervals)
        String[] timeIntervals = {
                "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30",
                "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45",
                "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00"
        };

        // Simulate closing prices for each time interval (using random numbers for testing)
        List<Double> closePricesList = new ArrayList<>();
        for (int i = 0; i < timeIntervals.length; i++) {
            closePricesList.add(Math.random() * 100); // Generate a random close price for each time interval
        }

        // Convert List<Double> to double[]
        double[] closePrices = new double[closePricesList.size()];
        for (int i = 0; i < closePricesList.size(); i++) {
            closePrices[i] = closePricesList.get(i);
        }

        // Create StockData object using the simulated time intervals and close prices
        List<StockData> stockDataList = new ArrayList<>();
        stockDataList.add(new StockData(timeIntervals, closePrices));

        return stockDataList;
    }
}
