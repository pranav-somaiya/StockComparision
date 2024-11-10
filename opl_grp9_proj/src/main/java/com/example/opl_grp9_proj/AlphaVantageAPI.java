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

    private static final String API_KEY = "MNV1WO8GNK85QT9F";

    public StockData fetchStockData(String stockSymbol) {
        ArrayList<String> timeIntervalsList = new ArrayList<>();
        ArrayList<Double> closePricesList = new ArrayList<>();

        try {

            String urlString = String.format(
                    "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=15min&apikey=%s",
                    stockSymbol, API_KEY);


            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseObject = objectMapper.readTree(response.toString());


            JsonNode timeSeries = responseObject.path("Time Series (15min)");


            for (Iterator<String> it = timeSeries.fieldNames(); it.hasNext(); ) {
                String time = it.next();
                JsonNode stockData = timeSeries.get(time);
                double closePrice = stockData.path("4. close").asDouble();
                timeIntervalsList.add(time);
                closePricesList.add(closePrice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        String[] timeIntervals = timeIntervalsList.toArray(new String[0]);
        double[] closePrices = new double[closePricesList.size()];
        for (int i = 0; i < closePricesList.size(); i++) {
            closePrices[i] = closePricesList.get(i);
        }


        return new StockData(timeIntervals, closePrices);
    }
}
