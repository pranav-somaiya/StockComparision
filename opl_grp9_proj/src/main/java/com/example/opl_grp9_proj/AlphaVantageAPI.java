package com.example.opl_grp9_proj;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class AlphaVantageAPI {

    private static final String API_KEY = "MNV1WO8GNK85QT9F"; // Your API key here
    private static final String BASE_URL = "https://www.alphavantage.co/query?";

    // Method to fetch stock data
    public static List<StockData> fetchStockData(String symbol) throws IOException {
        String url = BASE_URL + "function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + API_KEY;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseData = response.body().string();

            // Use Jackson to parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseData);
            JsonNode timeSeries = jsonNode.get("Time Series (Daily)");

            List<StockData> stockDataList = new ArrayList<>();
            Iterator<String> fieldNames = timeSeries.fieldNames();

            // Iterate over the time series keys (dates) and extract the data
            while (fieldNames.hasNext()) {
                String date = fieldNames.next();
                JsonNode dayData = timeSeries.get(date);
                double closePrice = dayData.get("4. close").asDouble();

                stockDataList.add(new StockData(date, closePrice));
            }

            return stockDataList;
        }
    }
}
