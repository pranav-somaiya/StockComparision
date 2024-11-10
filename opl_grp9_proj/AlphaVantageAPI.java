package com.example.opl_grp9_proj;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class AlphaVantageAPI {

    private static final String API_KEY = "MNV1WO8GNK85QT9F";  // Replace with your actual API key

    // Fetch stock data for a given symbol
    public StockData fetchStockData(String stockSymbol) {
        ArrayList<String> timeIntervalsList = new ArrayList<>();
        ArrayList<Double> closePricesList = new ArrayList<>();

        try {
            // URL for Alpha Vantage API - fetches 15-minute intervals
            String urlString = String.format(
                    "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=15min&apikey=%s",
                    stockSymbol, API_KEY);

            // Open connection and fetch data
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Use Jackson's ObjectMapper to parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseObject = objectMapper.readTree(response.toString());

            // Get the Time Series data
            JsonNode timeSeries = responseObject.path("Time Series (15min)");

            // Extract time intervals and closing prices
            for (Iterator<String> it = timeSeries.fieldNames(); it.hasNext(); ) {
                String time = it.next();
                JsonNode stockData = timeSeries.get(time);
                double closePrice = stockData.path("4. close").asDouble();
                timeIntervalsList.add(time);  // Store the time interval
                closePricesList.add(closePrice);  // Store the closing price
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert lists to arrays
        String[] timeIntervals = timeIntervalsList.toArray(new String[0]);
        double[] closePrices = new double[closePricesList.size()];
        for (int i = 0; i < closePricesList.size(); i++) {
            closePrices[i] = closePricesList.get(i);
        }

        // Return a StockData object
        return new StockData(timeIntervals, closePrices);
    }
}
