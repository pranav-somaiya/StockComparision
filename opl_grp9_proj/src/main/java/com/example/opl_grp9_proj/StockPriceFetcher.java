package com.example.opl_grp9_proj;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockPriceFetcher {

    // Replace with your own Alpha Vantage API Key
    private static final String API_KEY = "YOUR_API_KEY";

    // Fetch stock price for the given stock symbol and interval
    public static double fetchStockPrice(String stockSymbol, String interval) {
        try {
            // Alpha Vantage API URL (Intraday Time Series data with 15-minute intervals)
            String urlString = String.format(
                    "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=%s&apikey=%s",
                    stockSymbol, interval, API_KEY);

            // Create a URL object
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.toString());

            // Check if the response contains the "Time Series" data
            if (jsonResponse.has("Time Series (15min)")) {
                JsonNode timeSeries = jsonResponse.get("Time Series (15min)");

                // Get the most recent stock data (the first time entry)
                String latestTime = timeSeries.fieldNames().next();
                JsonNode latestData = timeSeries.get(latestTime);

                // Extract the closing price from the response
                double closePrice = latestData.get("4. close").asDouble();
                return closePrice;
            } else {
                System.err.println("Error: Unable to retrieve stock data or invalid symbol.");
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;  // In case of error, return -1
        }
    }
}
