package com.example.opl_grp9_proj;

import java.util.ArrayList;
import java.util.List;

public class StockPriceFetcher {

    private AlphaVantageAPI alphaVantageAPI = new AlphaVantageAPI();


    public List<StockData> fetchStockData(String stockSymbol) {

        List<StockData> stockDataList = new ArrayList<>();
        stockDataList.add(alphaVantageAPI.fetchStockData(stockSymbol));
        return stockDataList;
    }

    public List<StockData> fetchSimulatedStockData() {

        String[] timeIntervals = {
                "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30",
                "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45",
                "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00"
        };


        List<Double> closePricesList = new ArrayList<>();
        for (int i = 0; i < timeIntervals.length; i++) {
            closePricesList.add(Math.random() * 100);
        }


        double[] closePrices = new double[closePricesList.size()];
        for (int i = 0; i < closePricesList.size(); i++) {
            closePrices[i] = closePricesList.get(i);
        }


        List<StockData> stockDataList = new ArrayList<>();
        stockDataList.add(new StockData(timeIntervals, closePrices));

        return stockDataList;
    }
}
